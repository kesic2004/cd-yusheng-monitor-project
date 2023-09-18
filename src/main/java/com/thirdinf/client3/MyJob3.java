package com.thirdinf.client3;

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
public class MyJob3 implements MyInf3, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob3.class);
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
      MyRequestData3 request = new MyRequestData3();
      request.setQrcode(d.getQrcode());
      Integer address = d.getAddresscode() / 2;
      request.setNumber(address.toString());
      request.setRecord_voucher(d.getParam1());
      int iType = this.getFill_Type(d.getGxcode2());
      if (iType == 0) {
         logger.info("@@-> 小型罐");
         request.setGx("0");
      } else if (iType == 1) {
         logger.info("@@-> 中型罐");
         request.setGx("1");
      } else if (iType == 2) {
         logger.info("@@-> 大型罐");
         request.setGx("2");
      } else {
         logger.info("@@-> 未知型罐");
         request.setGx("-1");
      }

      Integer iMode = this.getFill_Way(d.getGxcode2());
      if (iMode == 85) {
         request.setFs("0");
      } else if (iMode == 170) {
         request.setFs("1");
      } else {
         logger.info("@@-> 未知灌装方式");
         request.setFs("-1");
      }

      request.setPz(d.getGrossweight().toString());
      request.setZz(d.getEndweight().toString());
      request.setSz(d.getSettingweight().toString());
      Integer jz = d.getEndweight() - d.getGrossweight();
      request.setJz(jz.toString());
      request.setPeop_num(d.getOperatorid().toString());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sStartDatetime = sdf.format(new Date());
      request.setEnd_time(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      String sEndDatetime = sdf.format(endDate);
      request.setStart_time(sEndDatetime);
      int cmd = d.getCommand();
      if (164 == cmd) {
         request.setStatus("4");
      } else if (165 == cmd) {
         request.setStatus("5");
      } else if (166 == cmd) {
         request.setStatus("6");
      } else if (167 == cmd) {
         request.setStatus("7");
      } else if (168 == cmd) {
         request.setStatus("8");
      } else {
         request.setStatus("-1");
      }

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
         MyResponseData3 myResponseData = (MyResponseData3)objectMapper.readValue(jsonString, MyResponseData3.class);
         MyLog3.showCode2(myResponseData.getCode());
         logger.info("-> parse response data: " + myResponseData.toString());
      } catch (Exception var23) {
         logger.info(var23.getMessage());
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

   MyRequestData3 crteateTestData() {
      MyRequestData3 request = new MyRequestData3();
      request.setQrcode("32000949");
      request.setNumber("1");
      request.setRecord_voucher("12883643935366881301");
      request.setSz("1");
      request.setPz("2");
      request.setZz("3");
      request.setFs("fs");
      request.setPeop_num("1");
      request.setEnd_time("2021-03-01 11:13:12");
      request.setStart_time("2021-03-01 11:11:11");
      return request;
   }

   public void onApplicationEvent(MyEvent myEvent) {
      logger.info("Client_Id = " + Client_Id + "-* 收到广播消息事件");
      Tbldata t = myEvent.getT();
      addData(t);
   }
}
