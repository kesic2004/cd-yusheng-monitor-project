package com.thirdinf.client12;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.fj.entity.FjBottle;
import com.model.fj.entity.FjDw;
import com.model.fj.service.FjBottleService;
import com.model.fj.service.FjDwService;
import com.thirdinf.BaseInvokeInf;
import com.tools.XTool;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;

public class MyInvoke12 implements BaseInvokeInf {
   private static final Logger logger = Logger.getLogger(MyInvoke12.class);
   private FjBottleService fjBottleService;
   private FjDwService fjDwService;

   public MyInvoke12(FjBottleService fjBottleService, FjDwService fjDwService) {
      this.fjBottleService = fjBottleService;
      this.fjDwService = fjDwService;
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
         FjDw fjDw = this.fjDwService.findDwByJblCode(stationCodeJbl);
         MyCheckResponseData12 resp = new MyCheckResponseData12();
         resp.setStatus("false");
         if (fjDw == null) {
            resp.setCode("@@站点 不存在 ！！！ (佳贝尔站点号: " + stationCodeJbl + " 映射为空)");
         } else {
            FjBottle fjBottle = this.fjBottleService.findBottle(fjDw.getId(), qrcode);
            if (fjBottle == null) {
               resp.setCode("气瓶 不存在");
            } else {
               Date discardDate = this.convertToDate(fjBottle.getDiscarddate());
               Date nextCheckDate = this.convertToDate(fjBottle.getNextcheckdate());
               if (discardDate.before(new Date())) {
                  resp.setCode("报废日期 已经过期！！=> " + fjBottle.getDiscarddate());
               } else if (nextCheckDate.before(new Date())) {
                  resp.setCode("下检日期 已经过期！！ => " + fjBottle.getNextcheckdate());
               } else if (fjBottle.getState() != 0) {
                  resp.setCode("气瓶 " + fjBottle.getState());
               } else if (fjBottle.getState() == 0) {
                  resp.setStatus("true");
               }
            }
         }

         ObjectMapper objectMapper = new ObjectMapper();
         jsonString = objectMapper.writeValueAsString(resp);
         logger.info("-> recv check result: " + jsonString);
      } catch (Exception var15) {
         logger.info(var15.getMessage());
      }

      return jsonString;
   }

   Date convertToDate(String strDate) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date date = null;

      try {
         date = sdf.parse(strDate);
      } catch (ParseException var5) {
         var5.printStackTrace();
      }

      return date;
   }

   public Integer parseData(String jsonString) {
      Integer rState = 85;

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData12 myResponseData = (MyCheckResponseData12)objectMapper.readValue(jsonString, MyCheckResponseData12.class);
         logger.info("-> parse check result data: " + myResponseData.toString());
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
}
