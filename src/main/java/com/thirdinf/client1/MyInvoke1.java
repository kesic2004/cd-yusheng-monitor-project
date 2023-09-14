package com.thirdinf.client1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

public class MyInvoke1 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke1.class);

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      String label_no = map.get("qrcode").toString();
      Integer machine_no = XTool.byte2Integer((Byte)map.get("address"));
      MyCheckRequestData1 requestData = new MyCheckRequestData1();
      requestData.setLabel_no(label_no);
      requestData.setMachine_no(machine_no.toString());
      requestData.setFillflag("2");

      try {
         RestTemplate rest = new RestTemplate();
         ObjectMapper objectMapper = new ObjectMapper();
         String strJson = objectMapper.writeValueAsString(requestData);
         logger.info("-> post check data: " + strJson);
         jsonString = (String)rest.postForObject(requestUrl, strJson, String.class, new Object[0]);
         logger.info("-> recv check result: " + jsonString);
      } catch (Exception var10) {
         var10.printStackTrace();
      }

      return jsonString;
   }

   MyCheckRequestData1 createTestData() {
      String label_no = "999000000031";
      Integer machine_no = 2;
      String strMachineNo = "0" + machine_no.toString();
      String fillflag = "2";
      MyCheckRequestData1 requestData = new MyCheckRequestData1();
      requestData.setLabel_no(label_no);
      requestData.setMachine_no(strMachineNo);
      requestData.setFillflag(fillflag);
      return requestData;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData1 myResponseData = (MyCheckResponseData1)objectMapper.readValue(jsonString, MyCheckResponseData1.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         MyLog1.showStatus(myResponseData.getStatus());
         if (myResponseData.getStatus() == 200) {
            if (myResponseData.getResult().getStatus().equalsIgnoreCase("00")) {
               logger.info("## 气瓶正常 >> " + myResponseData.getResult().getStatus());
               rState = XTool.byte2Integer((byte)-86);
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("01")) {
               logger.info("## 无该气瓶信息 >> " + myResponseData.getResult().getStatus());
               rState = XTool.byte2Integer((byte)2);
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("02")) {
               logger.info("## 气瓶检测信息不合格 >> " + myResponseData.getResult().getStatus());
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("03")) {
               logger.info("## 气瓶未启用 >> " + myResponseData.getResult().getStatus());
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("04")) {
               logger.info("## 气瓶过期未检 >> " + myResponseData.getResult().getStatus());
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("05")) {
               logger.info("## 气瓶已经报废 >> " + myResponseData.getResult().getStatus());
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("06")) {
               logger.info("## 气瓶已经注销 >> " + myResponseData.getResult().getStatus());
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("07")) {
               logger.info("## 气瓶未建档 >> " + myResponseData.getResult().getStatus());
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("08")) {
               logger.info("## 非自有产权气瓶（非联营单位，不可充装）>> " + myResponseData.getResult().getStatus());
            } else if (myResponseData.getResult().getStatus().equalsIgnoreCase("09")) {
               logger.info("## 非自有产权气瓶（联营单位，可以充装）>> " + myResponseData.getResult().getStatus());
               rState = XTool.byte2Integer((byte)-86);
            } else {
               logger.info("## 未知状态码!! >> " + myResponseData.getResult().getStatus());
            }
         }
      } catch (Exception var5) {
         logger.info("## json 格式解析出错!!");
         var5.printStackTrace();
      }

      return rState;
   }

   public String getParam1() {
      return null;
   }
}
