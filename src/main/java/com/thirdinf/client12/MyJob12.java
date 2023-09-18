package com.thirdinf.client12;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.fj.entity.FjDw;
import com.model.fj.entity.FjWorker;
import com.model.fj.service.FjDwService;
import com.model.fj.service.FjWorkerService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.entity.Tbldata;
import com.model.tblvalues.entity.Tblvalues;
import com.model.tblvalues.service.TblvaluesService;
import com.thirdinf.MyEvent;
import com.tools.XTool;
import java.net.URI;
import java.text.DecimalFormat;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MyJob12 implements MyInf12, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob12.class);
   @Autowired
   TblclientService tblclientService;
   @Autowired
   DeviceService deviceService;
   @Autowired
   private FjDwService fjDwService;
   @Autowired
   TblvaluesService tblvaluesService;
   @Autowired
   FjWorkerService fjWorkerService;
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
                  String stationNo = d.getProvincecode().toString() + d.getCitycode().toString() + d.getStationcode().toString();
                  FjDw fjDw = this.fjDwService.findDwByJblCode(stationNo);
                  if (fjDw != null) {
                     client.setP1(fjDw.getToken());
                     this.sendData(d, client);
                  } else {
                     logger.info("@@站点号" + stationNo + "查询为空！！");
                  }
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

   public boolean sendData(Tbldata d, Tblclient client) {
      boolean bOk = true;
      double temperature = 0.0D;
      double press = 0.0D;
      String stationNo = d.getProvincecode().toString() + d.getCitycode().toString() + d.getStationcode().toString();
      Tblvalues tblvalues = this.tblvaluesService.findValues(stationNo);
      if (tblvalues != null) {
         try {
            if (tblvalues.getTemperature() != null && !tblvalues.getTemperature().equalsIgnoreCase("")) {
               temperature = Double.parseDouble(tblvalues.getTemperature());
            }
         } catch (Exception var42) {
            var42.printStackTrace();
         }

         try {
            if (tblvalues.getPressure() != null && !tblvalues.getPressure().equalsIgnoreCase("")) {
               press = Double.parseDouble(tblvalues.getPressure());
            }
         } catch (Exception var41) {
            var41.printStackTrace();
         }
      }

      MyRequestData12 request = new MyRequestData12();
      List<Items> Items = new ArrayList();
      Items item = new Items();
      Man man = new Man();
      FjWorker fjWorker = this.fjWorkerService.findWorker(stationNo, d.getOperatorid());
      if (fjWorker != null) {
         man.setId(d.getOperatorid());
         man.setName(fjWorker.getOperatorname());
      } else {
         man.setId(d.getOperatorid());
         man.setName("operator");
      }

      item.setBarcode(d.getQrcode());
      Integer address = d.getAddresscode() / 2;
      item.setMachine(address);
      item.setTemperature(temperature);
      item.setPressure(press);
      item.setCustomerNo(d.getClientid().toString());
      item.setThirdId(d.getId());
      new DecimalFormat("#.00");
      double d1 = (double)d.getGrossweight() / 100.0D;
      double d2 = (double)d.getEndweight() / 100.0D;
      double d3 = (double)(d.getEndweight() - d.getGrossweight()) / 100.0D;
      item.setIdleWeight(d1);
      item.setGrossWeight(d2);
      item.setNetWeight(d3);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
      String sStartDatetime = sdf.format(new Date());
      item.setEndDate(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
      String sEndDatetime = sdf2.format(endDate);
      String sEndDatetime2 = sdf.format(endDate);
      item.setBeginDate(sEndDatetime);
      Items.add(item);
      request.setItems(Items);
      request.setMan(man);
      request.setTraceDate(sEndDatetime2);

      try {
         String postUrl = client.getPosturl();
         logger.info("-> post url: " + postUrl);
         ObjectMapper objectMapper = new ObjectMapper();
         String strJson = objectMapper.writeValueAsString(request);
         logger.info("-> post request data: " + strJson);
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/json");
         HttpEntity<Object> requestEntity = new HttpEntity((Object)null, headers);
         postUrl = postUrl + "?data=" + strJson + "&token=" + client.getP1();
         logger.info("=>> post url: " + postUrl);
         URI uri = UriComponentsBuilder.fromUriString(postUrl).build().encode().toUri();
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         ResponseEntity<String> exchange = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
         logger.info("-> parse response data: " + (String)exchange.getBody());
         MyResponseData12 myResponseData12 = (MyResponseData12)objectMapper.readValue((String)exchange.getBody(), MyResponseData12.class);
         logger.info("-> parse response data: " + myResponseData12.toString());
         if (myResponseData12.getState() == 0) {
            logger.info("-> data send ok!!!");
         } else {
            logger.info("-> data send failed!!!");
         }
      } catch (Exception var40) {
         logger.info(var40.getMessage());
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
