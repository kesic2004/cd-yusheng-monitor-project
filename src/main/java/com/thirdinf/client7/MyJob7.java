package com.thirdinf.client7;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.entity.Tbldata;
import com.model.xk.service.XkStationmapService;
import com.thirdinf.MyEvent;
import com.tools.RandomGUID;
import com.tools.XTool;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class MyJob7 implements MyInf7, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob7.class);
   private static String ApiToken = "";
   private static int KeepAlive = 0;
   @Autowired
   TblclientService tblclientService;
   @Autowired
   DeviceService deviceService;
   @Autowired
   private              XkStationmapService xkStationmapService;
   private static final List<Tbldata>       lDATA = new ArrayList();

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
            boolean bTo = false;
            Tbldata d = popData();
            if (d != null) {
               logger.info("@@ 广播处理消息: " + d.toString());
               boolean bOk = this.checkClient(Client_Id, d.getAddresscode(), d.getProvincecode(), d.getCitycode(), d.getStationcode());
               String stationCodeJbl = d.getProvincecode().toString() + d.getCitycode().toString() + d.getStationcode().toString();
               UserManager user = this.xkStationmapService.findUserCodeAndUserPassByJblCode(stationCodeJbl);
               if (bOk && user != null) {
                  while(true) {
                     boolean bSend = false;
                     logger.info("-> 获取 ApiToken !");
                     bSend = this.fetchApiToken(client, user.getUserCode(), user.getUserPass());
                     if (bSend && KeepAlive > 5) {
                        this.sendData(d, client);
                        bTo = true;
                        break;
                     }

                     ApiToken = "";
                     KeepAlive = 0;
                     this.mySleep(5000);
                  }
               }
            }

            if (bTo) {
               this.mySleep(300);
            } else {
               this.mySleep(100);
            }
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

   public boolean fetchApiToken(Tblclient client, String userCode, String UserPass) {
      boolean bOk = false;

      try {
         String fetchTokenUrl = client.getP3();
         fetchTokenUrl = fetchTokenUrl + "/" + userCode + "/" + UserPass + "/";
         RestTemplate rest = new RestTemplate();
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         String jsonString = (String)rest.getForObject(fetchTokenUrl, String.class, new Object[0]);
         logger.info("-> recv [fetchApiToken] response apiToken data: " + jsonString);
         TokenResp tokenResp = (TokenResp)objectMapper.readValue(jsonString, TokenResp.class);
         logger.info("-> parse [fetchApiToken] response apiToken data: " + tokenResp.toString());
         ApiToken = tokenResp.getToken();
         KeepAlive = tokenResp.getKeepAlive();
         bOk = true;
      } catch (Exception var10) {
         var10.printStackTrace();
      }

      return bOk;
   }

   public boolean validApiToken(Tblclient client) {
      boolean bOk = false;

      try {
         String validTokenUrl = client.getP3();
         validTokenUrl = validTokenUrl + "/" + ApiToken + "/";
         RestTemplate rest = new RestTemplate();
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         String jsonString = (String)rest.getForObject(validTokenUrl, String.class, new Object[0]);
         logger.info("-> recv [validApiToken] response apiToken data: " + jsonString);
         TokenResp tokenResp = (TokenResp)objectMapper.readValue(jsonString, TokenResp.class);
         logger.info("-> parse [validApiToken] response apiToken data: " + tokenResp.toString());
         KeepAlive = tokenResp.getKeepAlive();
         bOk = true;
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return bOk;
   }

   public boolean sendData(Tbldata d, Tblclient client) {
      boolean bOk = true;
      MyRequestData7 request = new MyRequestData7();
      RandomGUID myGUID = new RandomGUID(true);
      request.setId(myGUID.toString());
      request.setOperate_code(d.getOperatorid().toString());
      request.setBar_code(d.getQrcode());
      Integer address = d.getAddresscode() / 2;
      request.setBalance(address.toString());
      DecimalFormat df = new DecimalFormat("#.00");
      double d1 = (double)d.getGrossweight() / 100.0D;
      double d2 = (double)d.getEndweight() / 100.0D;
      double d3 = (double)(d.getEndweight() - d.getGrossweight()) / 100.0D;
      request.setStart_weight(df.format(d1));
      request.setEnd_weight(df.format(d2));
      request.setFill_weight(df.format(d3));
      int iType = this.getFill_Type(d.getGxcode2());
      Integer intPz = 0;
      if (iType == 0) {
         logger.info("@@-> 小型罐");
         request.setC_type(5);
      } else if (iType == 1) {
         logger.info("@@-> 中型罐");
         request.setC_type(15);
      } else if (iType == 2) {
         logger.info("@@-> 大型罐");
         request.setC_type(50);
      } else {
         logger.info("@@-> 未知型罐");
         request.setC_type(5);
      }

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sStartDatetime = sdf.format(new Date());
      request.setEnd_date(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sEndDatetime = sdf2.format(endDate);
      String sEndDatetime2 = sdf.format(endDate);
      request.setBegin_date(sEndDatetime);
      request.setOpt_date(sEndDatetime2);

      try {
         String postUrl = client.getPosturl();
         postUrl = postUrl + "/" + ApiToken + "/";
         logger.info("-> post url: " + postUrl);
         MyRequestData7[] r = new MyRequestData7[]{request};
         ObjectMapper objectMapper = new ObjectMapper();
         String strJson = objectMapper.writeValueAsString(r);
         logger.info("-> post request data: " + strJson);
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/x-www-form-urlencoded");
         MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap();
         postParameters.add("data", strJson);
         HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(postParameters, headers);
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         Map result = (Map)rest.postForObject(postUrl, httpEntity, Map.class, new Object[0]);
         logger.info("-> recv response data: " + result.toString());
         String denyList = result.get("denyList").toString();
         logger.info("-> denyList: " + denyList);
         String reason;
         if (result.get("id") != null) {
            reason = result.get("id").toString();
            logger.info("-> id: " + reason);
         }

         if (result.get("error") != null) {
            reason = result.get("error").toString();
            logger.info("-> error: " + reason);
         }

         if (result.get("reason") != null) {
            reason = result.get("reason").toString();
            logger.info("-> reason: " + reason);
         }
      } catch (Exception var35) {
         logger.info(var35.getMessage());
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
