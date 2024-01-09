package com.thirdinf.client7;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.xk.entity.XkCylinderrec;
import com.model.xk.service.XkCylinderrecService;
import com.model.xk.service.XkStationmapService;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;

public class MyInvoke7 implements BaseInvokeInf {
   private static final Logger               logger = Logger.getLogger(MyInvoke7.class);
   private final        XkCylinderrecService xkCylinderrecService;
   private final        XkStationmapService  xkStationmapService;

   public MyInvoke7(XkCylinderrecService xkCylinderrecService, XkStationmapService xkStationmapService) {
      this.xkCylinderrecService = xkCylinderrecService;
      this.xkStationmapService = xkStationmapService;
   }

   public String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map) {
      String jsonString = "";
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer number = XTool.byte2Integer((Byte)map.get("address"));
      number = number / 2;
      String stationCodeJbl = province.toString() + city.toString() + station.toString();
      String qrcode = map.get("qrcode").toString();

      try {
         String stationCode = this.xkStationmapService.findStationCodeByJblCode(stationCodeJbl);
         MyCheckResponseData7 resp = new MyCheckResponseData7();
         resp.setStatus("false");
         if (stationCode != null && !stationCode.equalsIgnoreCase("")) {
            XkCylinderrec xkCylinderrec = this.xkCylinderrecService.findcClinderRec(qrcode);
            if (xkCylinderrec == null) {
               resp.setDetail("气瓶 不存在");
               logger.info("@@-> 气瓶 不存在: " + qrcode);
            } else {
               resp.setDetail("气瓶 " + xkCylinderrec.getStatus());
               logger.info("@@-> 气瓶 状态: " + xkCylinderrec.getStatus());
               if (xkCylinderrec.getStatus().equalsIgnoreCase("正常")) {
                  resp.setStatus("true");
                  logger.info("@@-> 气瓶 可充: " + qrcode);
               } else {
                  logger.info("@@-> 气瓶 不可充: " + qrcode);
               }
            }
         } else {
            resp.setDetail("@@站点 不存在 ！！！ (佳贝尔站点号: " + stationCodeJbl + " 映射为空)");
            logger.info("@@-> 站点 不存在 ！！！ (佳贝尔站点号: " + stationCodeJbl + " 映射为空)");
         }

         ObjectMapper objectMapper = new ObjectMapper();
         jsonString = objectMapper.writeValueAsString(resp);
         logger.info("-> recv check result: " + jsonString);
      } catch (Exception var13) {
         logger.info(var13.getMessage());
      }

      return jsonString;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData7 myResponseData = (MyCheckResponseData7)objectMapper.readValue(jsonString, MyCheckResponseData7.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         if (myResponseData.getStatus().equalsIgnoreCase("true")) {
            rState = XTool.byte2Integer((byte)-86);
         } else {
            logger.info("## 气瓶不可充 >> " + myResponseData.getDetail());
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
}
