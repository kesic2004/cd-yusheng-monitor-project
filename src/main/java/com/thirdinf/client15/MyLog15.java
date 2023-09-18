package com.thirdinf.client15;

import org.apache.log4j.Logger;

public class MyLog15 {
   private static final Logger logger = Logger.getLogger(MyLog15.class);

   static void showCode(String code) {
      if (code.equalsIgnoreCase("0000")) {
         logger.info("## 上传成功!! =>" + code);
      } else if (code.equalsIgnoreCase("0001")) {
         logger.info("## 缺少参数!! =>" + code);
      } else if (code.equalsIgnoreCase("0002")) {
         logger.info("## 请求过于频繁!! =>" + code);
      } else if (code.equalsIgnoreCase("0003")) {
         logger.info("## 系统签名验证不通过!! =>" + code);
      } else if (code.equalsIgnoreCase("0004")) {
         logger.info("## 非法请求！ 数据未签名!! =>" + code);
      } else if (code.equalsIgnoreCase("0005")) {
         logger.info("## 非法请求！ 无效的 CommandID。!! =>" + code);
      } else if (code.equalsIgnoreCase("0006")) {
         logger.info("## 很抱歉， 此功能正在维护中， 暂时无法提供使用， 谢谢！!! =>" + code);
      } else if (code.equalsIgnoreCase("0007")) {
         logger.info("## 非法请求！ 无参数的请求。 =>" + code);
      } else if (code.equalsIgnoreCase("0008")) {
         logger.info("## 请使用 POST 请求 API!! =>" + code);
      } else if (code.equalsIgnoreCase("0009")) {
         logger.info("## 账户已禁用， 请联系管理员!! =>" + code);
      } else if (code.equalsIgnoreCase("0010")) {
         logger.info("## 账户不存在， 请联系管理员!! =>" + code);
      } else {
         logger.info("## 服务器返回未知状态码!! =>" + code);
      }

   }

   static void showCode2(String code) {
      showCode(code);
   }
}
