package com.thirdinf.client4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.https.HttpsClientRequestFactory;
import com.model.tbltoken.entity.Tbltoken;
import com.model.tbltoken.service.TbltokenService;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

public class MyInvoke4 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke4.class);
   private String token;
   private       String          stationCode;
   private final TbltokenService tbltokenService;

   public MyInvoke4(TbltokenService tbltokenService) {
      this.tbltokenService = tbltokenService;
   }

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      if (!this.getLoginParams(map)) {
         return jsonString;
      } else {
         MyCheckRequestData4 requestData = new MyCheckRequestData4();
         requestData.setStationCode(this.getStationCode());
         String qrcode = map.get("qrcode").toString();
         requestData.setToken(this.getToken());
         requestData.setBarcode(qrcode);
         long t = System.currentTimeMillis() / 1000L;
         requestData.setTimestamp(Long.toString(t));
         logger.info("-> post check data: " + requestData.toString());

         try {
            HttpsClientRequestFactory httpRequestFactory = new HttpsClientRequestFactory();
            httpRequestFactory.setConnectTimeout(5000);
            httpRequestFactory.setReadTimeout(10000);
            RestTemplate rest = new RestTemplate(httpRequestFactory);
            LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap();
            params.add("stationCode", requestData.getStationCode());
            params.add("token", requestData.getToken());
            params.add("barcode", requestData.getBarcode());
            params.add("timestamp", requestData.getTimestamp());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");
            HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity(params, headers);
            ResponseEntity<String> result = rest.postForEntity(requestUrl, request, String.class, new Object[0]);
            jsonString = (String)result.getBody();
            logger.info("-> recv check result: " + jsonString);
         } catch (Exception var14) {
            logger.info(var14.getMessage());
         }

         return jsonString;
      }
   }

   public boolean getLoginParams(LinkedHashMap<String, Object> map) {
      boolean bOk = true;
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      String UserCode = map.get("p1").toString();
      String UserPass = map.get("p2").toString();
      String DeviceCode = province.toString() + city.toString() + station.toString();
      Tbltoken tbltoken = this.tbltokenService.findTokenByDevicecode(DeviceCode);
      if (tbltoken != null) {
         Date fromDate = tbltoken.getRecordtime();
         Date toDate = new Date();
         long diff = toDate.getTime() - fromDate.getTime();
         int hours = (int)(diff / 3600000L);
         if (hours < 12) {
            this.setToken(tbltoken.getToken());
            this.setStationCode(tbltoken.getStationcode());
            return true;
         }

         this.tbltokenService.delTokenByDevicecode(DeviceCode);
      }

      String jsonString = "";
      MyLoginRequest4 requestData = new MyLoginRequest4();
      requestData.setDeviceCode(DeviceCode);
      requestData.setUserCode(UserCode);
      requestData.setUserPass(UserPass);
      String requestUrl = map.get("p3").toString();
      logger.info("-> post login data: " + requestData.toString());

      try {
         RestTemplate rest = new RestTemplate(new HttpsClientRequestFactory());
         LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap();
         params.add("UserCode", requestData.getUserCode());
         params.add("UserPass", requestData.getUserPass());
         params.add("DeviceCode", requestData.getDeviceCode());
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/x-www-form-urlencoded");
         HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity(params, headers);
         ResponseEntity<String> result = rest.postForEntity(requestUrl, request, String.class, new Object[0]);
         jsonString = (String)result.getBody();
         logger.info("-> recv login result: " + jsonString);
         ObjectMapper objectMapper = new ObjectMapper();
         MyLoginResponse4 myResponseData = (MyLoginResponse4)objectMapper.readValue(jsonString, MyLoginResponse4.class);
         if (myResponseData.getState().equalsIgnoreCase("success")) {
            this.setToken(myResponseData.getToken());
            this.setStationCode(myResponseData.getStationCode());
            Tbltoken token = new Tbltoken();
            token.setDevicecode(DeviceCode);
            token.setStationcode(myResponseData.getStationCode());
            token.setToken(myResponseData.getToken());
            token.setRecordtime(new Date());
            this.tbltokenService.saveOrUpdate(token);
         } else {
            bOk = false;
         }
      } catch (Exception var21) {
         var21.printStackTrace();
         bOk = false;
      }

      return bOk;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData4 myResponseData = (MyCheckResponseData4)objectMapper.readValue(jsonString, MyCheckResponseData4.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         logger.info("## 状态码 >> " + myResponseData.getState());
         if (myResponseData.getState().equalsIgnoreCase("success")) {
            logger.info("## 气瓶可充 >> ");
            rState = XTool.byte2Integer((byte)-86);
         } else {
            logger.info("## 气瓶不可充 >> " + myResponseData.getMessage());
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return rState;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getStationCode() {
      return this.stationCode;
   }

   public void setStationCode(String stationCode) {
      this.stationCode = stationCode;
   }

   public String getParam1() {
      return this.getToken();
   }

   public String getParam2() {
      return this.getStationCode();
   }
}
