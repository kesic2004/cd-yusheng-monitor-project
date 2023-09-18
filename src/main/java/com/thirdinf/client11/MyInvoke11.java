package com.thirdinf.client11;

import cn.hutool.core.util.XmlUtil;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class MyInvoke11 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke11.class);
   private String record_voucher;

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer number = XTool.byte2Integer((Byte)map.get("address"));
      number = number / 2;
      String qrcode = map.get("qrcode").toString();
      MyCheckRequestData11 requestData = new MyCheckRequestData11();
      String stationCode = province.toString() + city.toString() + station.toString();
      requestData.setStation_num(Integer.parseInt(stationCode));
      requestData.setQrcode(qrcode);
      HttpHeaders headers = new HttpHeaders();
      MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
      headers.setContentType(type);
      StringBuffer soapRequestData = new StringBuffer("");
      soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">");
      soapRequestData.append("<soapenv:Header/>");
      soapRequestData.append("<soapenv:Body>");
      soapRequestData.append("<tem:GetGasState>");
      soapRequestData.append("<tem:TMH>");
      soapRequestData.append(requestData.getQrcode());
      soapRequestData.append("</tem:TMH>");
      soapRequestData.append("<tem:ZDBH>");
      soapRequestData.append(requestData.getStation_num());
      soapRequestData.append("</tem:ZDBH>");
      soapRequestData.append("</tem:GetGasState>");
      soapRequestData.append("</soapenv:Body>");
      soapRequestData.append("</soapenv:Envelope>");
      HttpEntity<String> request = new HttpEntity(soapRequestData + "", headers);
      String resultStr = "";
      String str = "";
      new HashMap();

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         resultStr = (String)rest.postForObject(requestUrl, request, String.class, new Object[0]);
         logger.info("-----------Response content-----------: " + resultStr);
         String responseXml = XmlUtil.unescape(resultStr);
         Map<String, Object> resultMap = XmlUtil.xmlToMap(responseXml);
         logger.info("-----------Response content-----------: " + resultMap);
         Object m1 = (Map)resultMap.get("soap:Body");
         if (m1 != null) {
            Object m2 = ((Map)m1).get("GetGasStateResponse");
            if (m2 != null) {
               Object m3 = ((Map)m2).get("GetGasStateResult");
               if (m3 != null) {
                  str = (String)m3;
               }
            }
         }
      } catch (Exception var25) {
         logger.info(var25.getMessage());
      }

      logger.info("-----------@@result=> " + str);
      return str;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         if (jsonString.equalsIgnoreCase("0")) {
            logger.info("## 气瓶可充 >>!! code: " + jsonString);
            rState = XTool.byte2Integer((byte)-86);
            this.setRecord_voucher("");
         } else {
            logger.info("## 气瓶不可充 >>!! code: " + jsonString);
         }
      } catch (Exception var4) {
         logger.info("## json 格式解析出错!!");
         var4.printStackTrace();
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
