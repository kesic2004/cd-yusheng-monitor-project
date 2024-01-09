package com.thirdinf.client4;

import com.https.HttpsClientRequestFactory;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.entity.Tbldata;
import com.thirdinf.MyEvent;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class MyJob4 implements MyInf4, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob4.class);
   @Autowired
   TblclientService tblclientService;
   @Autowired
   DeviceService deviceService;
   private static final List<Tbldata> lDATA = new ArrayList();

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

   public boolean sendData(Tbldata d, Tblclient client) {
      boolean bOk = true;
      MyRequestData4 requestData = new MyRequestData4();
      requestData.setBarcode(d.getQrcode());
      Integer address = d.getAddresscode() / 2;
      requestData.setBalance(address);
      requestData.setToken(d.getParam1());
      requestData.setStationCode(d.getParam2());
      requestData.setTemperature(0);
      requestData.setOpt_press(0);
      requestData.setStart_weight((double)d.getGrossweight() / 100.0D);
      requestData.setEnd_weight((double)d.getEndweight() / 100.0D);
      Integer jz = d.getEndweight() - d.getGrossweight();
      requestData.setFill_weight((double)jz / 100.0D);
      requestData.setOperate_code(d.getOperatorid().toString());
      long t = System.currentTimeMillis() / 1000L;
      requestData.setTimestamp(Long.toString(t));
      SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String uploadDatetime = sdf0.format(new Date());
      requestData.setUpload_date(uploadDatetime);
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sStartDatetime = sdf1.format(new Date());
      requestData.setEnd_date(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      String sEndDatetime = sdf1.format(endDate);
      requestData.setBegin_date(sEndDatetime);

      try {
         HttpsClientRequestFactory httpRequestFactory = new HttpsClientRequestFactory();
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap();
         params.add("token", requestData.getToken());
         params.add("stationCode", requestData.getStationCode());
         params.add("temperature", requestData.getTemperature());
         params.add("balance", requestData.getBalance());
         params.add("barcode", requestData.getBarcode());
         params.add("fill_weight", requestData.getFill_weight());
         params.add("upload_date", requestData.getUpload_date());
         params.add("begin_date", requestData.getBegin_date());
         params.add("end_date", requestData.getEnd_date());
         params.add("opt_press", requestData.getOpt_press());
         params.add("start_weight", requestData.getStart_weight());
         params.add("end_weight", requestData.getEnd_weight());
         params.add("operate_code", requestData.getOperate_code());
         params.add("timestamp", requestData.getTimestamp());
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/x-www-form-urlencoded");
         HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity(params, headers);
         ResponseEntity<String> result = rest.postForEntity(client.getPosturl(), request, String.class, new Object[0]);
         String jsonString = (String)result.getBody();
         logger.info("-> post request data: " + requestData.toString());
         logger.info("-> parse response data: " + jsonString);
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

   MyRequestData4 crteateTestData() {
      MyRequestData4 request = new MyRequestData4();
      request.setStationCode("ceshi01");
      request.setBarcode("32000949");
      request.setBalance(1);
      request.setToken("12883643935366881301");
      request.setTemperature(0);
      request.setOpt_press(0);
      request.setStart_weight(1.0D);
      request.setEnd_weight(5.0D);
      request.setFill_weight(4.0D);
      request.setOperate_code("1");
      long t = System.currentTimeMillis() / 1000L;
      request.setTimestamp(Long.toString(t));
      SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String uploadDatetime = sdf0.format(new Date());
      request.setUpload_date(uploadDatetime);
      request.setEnd_date("2021-03-10 11:13:12");
      request.setBegin_date("2021-03-10 11:11:11");
      return request;
   }

   public void onApplicationEvent(MyEvent myEvent) {
      logger.info("Client_Id = " + Client_Id + "-* 收到广播消息事件");
      Tbldata t = myEvent.getT();
      addData(t);
   }
}
