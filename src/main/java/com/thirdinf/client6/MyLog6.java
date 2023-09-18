package com.thirdinf.client6;

import org.apache.log4j.Logger;

public class MyLog6 {
   private static final Logger logger = Logger.getLogger(MyLog6.class);

   static void showCode(String code) {
      if (code.equalsIgnoreCase("200")) {
         logger.info("## 参数正常!! =>" + code);
      } else if (code.equalsIgnoreCase("201")) {
         logger.info("## 授权码不正确!! =>" + code);
      } else if (code.equalsIgnoreCase("202")) {
         logger.info("## 站点信息不存在!! =>" + code);
      } else if (code.equalsIgnoreCase("203")) {
         logger.info("## 无可充装的条码号!! =>" + code);
      } else {
         logger.info("## 服务器返回未知状态码!! =>" + code);
      }

   }

   static void showCode2(String code) {
      showCode(code);
   }
}