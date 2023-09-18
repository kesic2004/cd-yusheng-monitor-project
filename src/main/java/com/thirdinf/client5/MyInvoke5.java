package com.thirdinf.client5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.https.HttpsClientRequestFactory;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class MyInvoke5 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke5.class);
   private String record_voucher;

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer number = XTool.byte2Integer((Byte)map.get("address"));
      number = number / 2;
      String qrcode = map.get("qrcode").toString();
      MyCheckRequestData5 requestData = new MyCheckRequestData5();
      requestData.setStation_num(province.toString() + city.toString() + station.toString());
      requestData.setNumber(number.toString());
      requestData.setQrcode(qrcode);
      requestData.setSq_code("JBR");

      try {
         HttpsClientRequestFactory httpRequestFactory = new HttpsClientRequestFactory();
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         ObjectMapper objectMapper = new ObjectMapper();
         String strJson = objectMapper.writeValueAsString(requestData);
         logger.info("-> post check data: " + strJson);
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/json");
         HttpEntity<Object> entity = new HttpEntity(strJson, headers);
         jsonString = (String)rest.postForObject(requestUrl, entity, String.class, new Object[0]);
         logger.info("-> recv check result: " + jsonString);
      } catch (Exception var16) {
         logger.info(var16.getMessage());
      }

      return jsonString;
   }

   MyCheckRequestData5 createTestData(MyCheckRequestData5 requestData, LinkedHashMap<String, Object> map) {
      String qrcode = "3823804349";
      requestData.setQrcode(qrcode);
      return requestData;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData5 myResponseData = (MyCheckResponseData5)objectMapper.readValue(jsonString, MyCheckResponseData5.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         MyLog5.showCode(myResponseData.getCode());
         if (myResponseData.getStatus().equalsIgnoreCase("true")) {
            logger.info("## 气瓶可充 >> " + myResponseData.getRecord_voucher());
            rState = XTool.byte2Integer((byte)-86);
            this.setRecord_voucher(myResponseData.getRecord_voucher());
         } else {
            logger.info("## 气瓶不可充 >> " + myResponseData.getCode());
         }
      } catch (Exception var5) {
         logger.info("## json 格式解析出错!!");
         var5.printStackTrace();
      }

      return rState;
   }

   public String getParam1() {
      return this.getRecord_voucher();
   }

   public String getParam2() {
      return "";
   }

   public String getRecord_voucher() {
      return this.record_voucher;
   }

   public void setRecord_voucher(String record_voucher) {
      this.record_voucher = record_voucher;
   }
}
