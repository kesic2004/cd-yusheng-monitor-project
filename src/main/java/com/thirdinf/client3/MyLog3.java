package com.thirdinf.client3;

import org.apache.log4j.Logger;

public class MyLog3 {
   private static final Logger logger = Logger.getLogger(MyLog3.class);

   static void showCode(String code) {
      if (code.equalsIgnoreCase("200")) {
         logger.info("## 参数正常!! =>" + code);
      } else if (code.equalsIgnoreCase("201")) {
         logger.info("## 授权码不正确!! =>" + code);
      } else if (code.equalsIgnoreCase("202")) {
         logger.info("## 站点信息不存在!! =>" + code);
      } else if (code.equalsIgnoreCase("203")) {
         logger.info("## 无可充装的条码号!! =>" + code);
      } else if (code.equalsIgnoreCase("204")) {
         logger.info("## 记录已经上传过了，不用再传!! =>" + code);
      } else if (code.equalsIgnoreCase("400")) {
         logger.info("## 参数错误!! =>" + code);
      } else {
         logger.info("## 服务器返回未知状态码!! =>" + code);
      }

   }

   static void showCode2(String code) {
      showCode(code);
   }
}
