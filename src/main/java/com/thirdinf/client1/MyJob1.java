package com.thirdinf.client1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.entity.Tbldata;
import com.thirdinf.MyEvent;
import com.tools.XTool;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyJob1 implements MyInf1, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob1.class);
   @Autowired
   TblclientService tblclientService;
   @Autowired
   DeviceService deviceService;
   private static List<Tbldata> lDATA = new ArrayList();

   @PostConstruct
   public void Ini() throws Exception {
      (new Thread(this)).start();
   }

   public static synchronized void addData(Tbldata t) {
      lDATA.add(t);
   }

   public static synchronized Tbldata popData() {
      Tbldata t = null;
      if (lDATA.size() > 0) {
         t = (Tbldata)lDATA.remove(0);
      }

      return t;
   }

   public void run() {
   }

   public boolean checkClient(Integer clientid, Integer addresscode, Integer province, Integer city, Integer station) {
      boolean bOk = false;
      Device device = this.deviceService.findDeviceByClientIdAndAddress(clientid, addresscode, province, city, station);
      if (device == null) {
         logger.info("@@ 消息检查: 不传送 !!");
      } else {
         logger.info("@@ 消息检查: 传送!!");
         bOk = true;
      }

      return bOk;
   }

   public String getFill_Type(byte b) {
      byte ret4 = XTool.byteToBit(b, 4);
      byte ret5 = XTool.byteToBit(b, 5);
      byte ret6 = XTool.byteToBit(b, 6);
      int i4 = XTool.byte2Integer(ret4);
      int i5 = XTool.byte2Integer(ret5);
      int i6 = XTool.byte2Integer(ret6);
      int iType = 1 * i4 + 2 * i5 + 4 * i6;
      if (iType == 0) {
         return "LPG-05";
      } else if (iType == 1) {
         return "LPG-15";
      } else {
         return iType == 2 ? "LPG-50" : "";
      }
   }

   public Integer getFill_Way(byte b) {
      byte ret7 = XTool.byteToBit(b, 7);
      int i = XTool.byte2Integer(ret7);
      if (i == 0) {
         return 85;
      } else {
         return i == 1 ? 170 : 0;
      }
   }

   public boolean sendData(Tbldata d, Tblclient client) {
      boolean bOk = true;
      MyRequestData1 request = new MyRequestData1();
      request.setLabel_no(d.getQrcode());
      request.setMachine_no(d.getAddresscode().toString());
      request.setNet_weight(11);
      request.setTare(d.getGrossweight());
      request.setSet_weight(d.getSettingweight());
      request.setEnd_weight(d.getEndweight());
      request.setEnd_status(d.getCommand());
      request.setFilling_type(this.getFill_Type(d.getGxcode2()));
      request.setFilling_way(this.getFill_Way(d.getGxcode2()));
      request.setUser_no(d.getOperatorid().toString());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sStartDatetime = sdf.format(d.getCurtime());
      request.setEnd_date(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(d.getCurtime());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      String sEndDatetime = sdf.format(endDate);
      request.setBegin_date(sEndDatetime);

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         ObjectMapper objectMapper = new ObjectMapper();
         String strJson = objectMapper.writeValueAsString(request);
         logger.info("-> post request data: " + strJson);
         String jsonString = (String)rest.postForObject(client.getPosturl(), strJson, String.class, new Object[0]);
         logger.info("-> recv response data: " + jsonString);
         MyResponseData1 myResponseData = (MyResponseData1)objectMapper.readValue(jsonString, MyResponseData1.class);
         MyLog1.showStatus(myResponseData.getStatus());
         logger.info("-> parse response data: " + myResponseData.toString());
      } catch (Exception var16) {
         logger.info(var16.getMessage());
      }

      return bOk;
   }

   void mySleep(int millis) {
      try {
         Thread.sleep((long)millis);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

   }

   MyRequestData1 crteateTestData() {
      MyRequestData1 request = new MyRequestData1();
      request.setLabel_no("999000000031");
      request.setMachine_no("01");
      request.setNet_weight(15);
      request.setTare(75);
      request.setSet_weight(15);
      request.setEnd_weight(14);
      request.setEnd_status(0);
      request.setFilling_type("LPG-05");
      request.setFilling_way(85);
      request.setUser_no("10002");
      request.setEnd_date("2020-05-26 14:40:21");
      request.setBegin_date("2020-05-26 14:36:21");
      return request;
   }

   public void onApplicationEvent(MyEvent myEvent) {
      logger.info("Client_Id = " + Client_Id + "-* 收到广播消息事件");
      Tbldata t = myEvent.getT();
      addData(t);
   }
}
