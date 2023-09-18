package com.thirdinf.client15;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.entity.Tbldata;
import com.thirdinf.MyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyJob15 implements MyInf15, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob15.class);
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

   public boolean sendData(Tbldata d, Tblclient client) {
      boolean bOk = true;
      String appKey = client.getP1();
      String appSecret = client.getP2();
      MyRequestData15 request = new MyRequestData15();
      MyRequestDataEx15 requestEx = new MyRequestDataEx15();
      request.setCommandID("280");
      requestEx.setCommandID("280");
      request.setDataIdentity(client.getP3());
      requestEx.setDataIdentity(client.getP3());
      Data2 data = new Data2();
      Integer address = d.getAddresscode() / 2;
      data.setFill_scales_num(address.toString());
      data.setQr_code(d.getQrcode());
      float f1 = (float)d.getGrossweight() / 100.0F;
      data.setWeight(Float.toString(f1));
      data.setReal_bottle_weight(Float.toString(f1));
      float f2 = (float)d.getEndweight() / 100.0F;
      data.setFill_total_weight(Float.toString(f2));
      Integer jz = d.getEndweight() - d.getGrossweight();
      float f3 = (float)jz / 100.0F;
      data.setFill_weight(Float.toString(f3));
      data.setFill_worker_num(d.getOperatorid().toString());
      data.setFill_worker_name(d.getOperatorid().toString());
      data.setComment("");
      long n = System.currentTimeMillis() / 1000L;
      data.setFill_end(Long.toString(n));
      n -= (long)d.getUsedtime();
      data.setFill_start(Long.toString(n));

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
         List<Data2> dataList = new ArrayList();
         dataList.add(data);
         request.setData(dataList);
         String dataJson = objectMapper.writeValueAsString(request);
         String s0 = dataJson + appKey;
         String s1 = DigestUtils.md5Hex(s0);
         String sign = (new StringBuffer(DigestUtils.md5Hex(s1 + appSecret).toUpperCase())).reverse().toString();
         requestEx.setData(dataList);
         requestEx.setSign(sign);
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         String strJson = objectMapper.writeValueAsString(requestEx);
         logger.info("-> post request data: " + strJson);
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/json");
         HttpEntity<Object> entity = new HttpEntity(strJson, headers);
         String jsonString = (String)rest.postForObject(client.getPosturl(), entity, String.class, new Object[0]);
         logger.info("-> recv response data: " + jsonString);
         MyResponseData15 myResponseData = (MyResponseData15)objectMapper.readValue(jsonString, MyResponseData15.class);
         logger.info("-> parse response data: " + myResponseData.toString());
      } catch (Exception var29) {
         logger.info(var29.getMessage());
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

   public void onApplicationEvent(MyEvent myEvent) {
      logger.info("Client_Id = " + Client_Id + "-* 收到广播消息事件");
      Tbldata t = myEvent.getT();
      addData(t);
   }
}
