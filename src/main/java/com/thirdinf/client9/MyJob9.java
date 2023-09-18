package com.thirdinf.client9;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyJob9 implements MyInf9, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob9.class);
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
      Tblclient client = (Tblclient)this.tblclientService.getById(Client_Id);
      if (client != null && client.getState() != 0) {
         logger.info("-> start job ok !! => " + client.getId());
         logger.info("-------------------------------------------------");
         logger.info(client.toString());
         logger.info("-------------------------------------------------");

         while(true) {
            Tbldata d = popData();
            if (d != null) {
               logger.info("@@ 广播处理消息: " + d.toString());
               boolean bOk = this.checkClient(Client_Id, d.getAddresscode(), d.getProvincecode(), d.getCitycode(), d.getStationcode());
               if (bOk && client.getPosturl() != null && !client.getPosturl().equalsIgnoreCase("")) {
                  this.sendData(d, client);
               }
            }

            this.mySleep(100);
         }
      }

      logger.info("-> start job!! =>  error!!! stop job!!! => Client_Id: " + Client_Id);
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

   public String getFill_Typename(byte b) {
      int iType = this.getFill_Type(b);
      if (iType == 0) {
         return "LPG-05";
      } else if (iType == 1) {
         return "LPG-15";
      } else {
         return iType == 2 ? "LPG-50" : "";
      }
   }

   public int getFill_Type(byte b) {
      byte ret4 = XTool.byteToBit(b, 4);
      byte ret5 = XTool.byteToBit(b, 5);
      byte ret6 = XTool.byteToBit(b, 6);
      int i4 = XTool.byte2Integer(ret4);
      int i5 = XTool.byte2Integer(ret5);
      int i6 = XTool.byte2Integer(ret6);
      int iType = 1 * i4 + 2 * i5 + 4 * i6;
      return iType;
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
      MyRequestData9 request = new MyRequestData9();
      request.setStation_num(d.getProvincecode().toString() + d.getCitycode().toString() + d.getStationcode().toString());
      Integer address = d.getAddresscode() / 2;
      request.setNumber(address.toString());
      request.setRecord_voucher(d.getParam1());
      Integer iMode = this.getFill_Way(d.getGxcode2());
      request.setFs(iMode.toString());
      request.setPz(d.getGrossweight().toString());
      request.setZz(d.getEndweight().toString());
      request.setSz(d.getSettingweight().toString());
      Integer jz = d.getEndweight() - d.getGrossweight();
      request.setJz(jz.toString());
      request.setWorker(d.getOperatorid().toString());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sStartDatetime = sdf.format(new Date());
      request.setEnd_time(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      String sEndDatetime = sdf.format(endDate);
      request.setStart_time(sEndDatetime);

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         ObjectMapper objectMapper = new ObjectMapper();
         String strJson = objectMapper.writeValueAsString(request);
         logger.info("-> post request data: " + strJson);
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/json");
         HttpEntity<Object> entity = new HttpEntity(strJson, headers);
         String jsonString = (String)rest.postForObject(client.getPosturl(), entity, String.class, new Object[0]);
         logger.info("-> recv response data: " + jsonString);
         MyResponseData9 myResponseData = (MyResponseData9)objectMapper.readValue(jsonString, MyResponseData9.class);
         MyLog9.showCode2(myResponseData.getCode());
         logger.info("-> parse response data: " + myResponseData.toString());
      } catch (Exception var21) {
         logger.info(var21.getMessage());
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

   MyRequestData9 crteateTestData() {
      MyRequestData9 request = new MyRequestData9();
      request.setStation_num("1");
      request.setNumber("1");
      request.setRecord_voucher("1d3201a133be410ba8351b1100d5f674");
      request.setJz("0.1");
      request.setPz("0.2");
      request.setZz("0.3");
      request.setFs("fs");
      request.setWorker("1");
      request.setEnd_time("2020-06-01 11:13:12");
      request.setStart_time("2020-06-01 11:11:11");
      return request;
   }

   public void onApplicationEvent(MyEvent myEvent) {
      logger.info("Client_Id = " + Client_Id + "-* 收到广播消息事件");
      Tbldata t = myEvent.getT();
      addData(t);
   }
}
