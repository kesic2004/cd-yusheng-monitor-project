package com.thirdinf.client9;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class MyInvoke9 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke9.class);
   private String record_voucher;

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer number = XTool.byte2Integer((Byte)map.get("address"));
      number = number / 2;
      String qrcode = map.get("qrcode").toString();
      MyCheckRequestData9 requestData = new MyCheckRequestData9();
      requestData.setStation_num(province.toString() + city.toString() + station.toString());
      requestData.setQrcode(qrcode);
      requestData.setNumber(number.toString());
      requestData.setSq_code("JBR");

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
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

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData9 myResponseData = (MyCheckResponseData9)objectMapper.readValue(jsonString, MyCheckResponseData9.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         MyLog9.showCode(myResponseData.getCode());
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
