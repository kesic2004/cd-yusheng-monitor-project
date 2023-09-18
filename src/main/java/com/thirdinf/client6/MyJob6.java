package com.thirdinf.client6;

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
public class MyJob6 implements MyInf6, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob6.class);
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
               if (bOk) {
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
      MyRequestData6 request = new MyRequestData6();
      request.setQrcode(d.getQrcode());
      request.setSq_code("JBR");
      Integer address = d.getAddresscode() / 2;
      request.setChenNum(address.toString());
      request.setCodeWeight(d.getGrossweight().toString());
      request.setFillAfterWeight(d.getEndweight().toString());
      Integer jz = d.getEndweight() - d.getGrossweight();
      request.setNetWeight(jz.toString());
      request.setWorkcard(d.getOperatorid().toString());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sStartDatetime = sdf.format(new Date());
      request.setEndTime(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      String sEndDatetime = sdf.format(endDate);
      request.setStartTime(sEndDatetime);
      int cmd = d.getCommand();
      if (164 == cmd) {
         request.setFillStatus("0");
      } else if (165 == cmd) {
         request.setFillStatus("0");
      } else if (166 == cmd) {
         request.setFillStatus("0");
      } else if (167 == cmd) {
         request.setFillStatus("0");
      } else if (168 == cmd) {
         request.setFillStatus("0");
      } else {
         request.setFillStatus("-1");
      }

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         ObjectMapper objectMapper = new ObjectMapper();
         String postUrl = client.getPosturl();
         postUrl = postUrl + "?qrcode=" + request.getQrcode();
         postUrl = postUrl + "&sq_code=" + request.getSq_code();
         postUrl = postUrl + "&chenNum=" + request.getChenNum();
         postUrl = postUrl + "&startTime=" + request.getStartTime();
         postUrl = postUrl + "&endTime=" + request.getEndTime();
         postUrl = postUrl + "&netWeight=" + request.getNetWeight();
         postUrl = postUrl + "&codeWeight=" + request.getCodeWeight();
         postUrl = postUrl + "&fillAfterWeight=" + request.getFillAfterWeight();
         postUrl = postUrl + "&workcard=" + request.getWorkcard();
         postUrl = postUrl + "&fillStatus=" + request.getFillStatus();
         String jsonString = (String)rest.postForObject(postUrl, (Object)null, String.class, new Object[0]);
         logger.info("-> recv response data: " + jsonString);
         MyResponseData6 myResponseData = (MyResponseData6)objectMapper.readValue(jsonString, MyResponseData6.class);
         MyLog6.showCode2(myResponseData.getCode());
         logger.info("-> parse response data: " + myResponseData.toString());
      } catch (Exception var19) {
         logger.info(var19.getMessage());
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

   MyRequestData6 crteateTestData() {
      MyRequestData6 request = new MyRequestData6();
      request.setQrcode("32000949");
      request.setEndTime("2021-03-01 11:13:12");
      request.setStartTime("2021-03-01 11:11:11");
      return request;
   }

   public void onApplicationEvent(MyEvent myEvent) {
      logger.info("Client_Id = " + Client_Id + "-* 收到广播消息事件");
      Tbldata t = myEvent.getT();
      addData(t);
   }
}
