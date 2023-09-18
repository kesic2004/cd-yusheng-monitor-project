package com.thirdinf.client6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class MyInvoke6 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke6.class);
   private String record_voucher;

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer number = XTool.byte2Integer((Byte)map.get("address"));
      number = number / 2;
      String qrcode = map.get("qrcode").toString();
      MyCheckRequestData6 requestData = new MyCheckRequestData6();
      requestData.setSq_code("JBR");
      requestData.setStation_num(province.toString() + city.toString() + station.toString());
      requestData.setQrcode(qrcode);

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         requestUrl = requestUrl + "?station_num=" + requestData.getStation_num();
         requestUrl = requestUrl + "&qrcode=" + requestData.getQrcode();
         requestUrl = requestUrl + "&sq_code=" + requestData.getSq_code();
         jsonString = (String)rest.postForObject(requestUrl, (Object)null, String.class, new Object[0]);
         logger.info("-> recv check result: " + jsonString);
      } catch (Exception var12) {
         logger.info(var12.getMessage());
      }

      return jsonString;
   }

   MyCheckRequestData6 createTestData() {
      Integer station = 1;
      Integer number = 1;
      String qrcode = "TMBHCS001";
      MyCheckRequestData6 requestData = new MyCheckRequestData6();
      requestData.setStation_num(station.toString());
      requestData.setQrcode(qrcode);
      requestData.setSq_code("JBR");
      return requestData;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData6 myResponseData = (MyCheckResponseData6)objectMapper.readValue(jsonString, MyCheckResponseData6.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         MyLog6.showCode(myResponseData.getCode());
         if (myResponseData.getStatus().equalsIgnoreCase("true")) {
            rState = XTool.byte2Integer((byte)-86);
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
      return "";
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
