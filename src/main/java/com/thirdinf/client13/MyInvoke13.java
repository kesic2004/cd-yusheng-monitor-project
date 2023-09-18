package com.thirdinf.client13;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.LinkedHashMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class MyInvoke13 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke13.class);
   private String record_voucher;

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      String qrcode = map.get("qrcode").toString();
      MyCheckRequestData13 requestData = new MyCheckRequestData13();
      MyCheckRequestDataEx13 requestDataEx = new MyCheckRequestDataEx13();
      Data data = new Data();
      data.setQrcode(qrcode);
      requestData.setDataIdentity(map.get("p3").toString());
      requestDataEx.setDataIdentity(map.get("p3").toString());
      requestData.setCommandID("279");
      requestDataEx.setCommandID("279");
      requestData.setData(data);
      requestDataEx.setData(data);
      String appKey = map.get("p1").toString();
      String appSecret = map.get("p2").toString();

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         String dataJson = objectMapper.writeValueAsString(requestData);
         String s0 = dataJson + appKey;
         String s1 = DigestUtils.md5Hex(s0);
         String sign = (new StringBuffer(DigestUtils.md5Hex(s1 + appSecret).toUpperCase())).reverse().toString();
         requestDataEx.setSign(sign);
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         String strJson = objectMapper.writeValueAsString(requestDataEx);
         logger.info("-> post check data: " + strJson);
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/json");
         HttpEntity<Object> entity = new HttpEntity(strJson, headers);
         jsonString = (String)rest.postForObject(requestUrl, entity, String.class, new Object[0]);
         logger.info("-> recv check result: " + jsonString);
      } catch (Exception var20) {
         logger.info(var20.getMessage());
      }

      return jsonString;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
         MyCheckResponseData13 myResponseData = (MyCheckResponseData13)objectMapper.readValue(jsonString, MyCheckResponseData13.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         MyLog13.showCode(myResponseData.getRetCode());
         if (myResponseData.getRetCode().equalsIgnoreCase("0000") && myResponseData.getBody().getErrCode().equalsIgnoreCase("0000")) {
            logger.info("## 气瓶可充 >> ");
            rState = XTool.byte2Integer((byte)-86);
            this.setRecord_voucher("");
         } else {
            logger.info("## 气瓶不可充 >> " + myResponseData.getRetCode() + " - " + myResponseData.getRetMsg());
            if (myResponseData.getBody() != null) {
               logger.info("## 气瓶不可充 >> " + myResponseData.getBody().getErrCode() + " - " + myResponseData.getBody().getErrMsg());
            }
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
