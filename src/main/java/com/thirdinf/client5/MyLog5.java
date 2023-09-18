package com.thirdinf.client5;

import org.apache.log4j.Logger;

public class MyLog5 {
   private static final Logger logger = Logger.getLogger(MyLog5.class);

   static void showCode(String code) {
      if (code.equalsIgnoreCase("200")) {
         logger.info("## 参数正常!! =>" + code);
      } else if (code.equalsIgnoreCase("201")) {
         logger.info("## 授权码不正确!! =>" + code);
      } else if (code.equalsIgnoreCase("202")) {
         logger.info("## 站点信息不存在!! =>" + code);
      } else if (code.equalsIgnoreCase("203")) {
         logger.info("## 站点信息有误!! =>" + code);
      } else if (code.equalsIgnoreCase("204")) {
         logger.info("## 条码编号不存在!! =>" + code);
      } else if (code.equalsIgnoreCase("205")) {
         logger.info("## 条码编号有误!! =>" + code);
      } else if (code.equalsIgnoreCase("206")) {
         logger.info("## 超期未检!! =>" + code);
      } else if (code.equalsIgnoreCase("207")) {
         logger.info("## 下检日期有误!! =>" + code);
      } else if (code.equalsIgnoreCase("208")) {
         logger.info("## 未进行充前检查!! =>" + code);
      } else if (code.equalsIgnoreCase("209")) {
         logger.info("## 充前检查不合格!! =>" + code);
      } else if (code.equalsIgnoreCase("210")) {
         logger.info("## 无效的充装凭证!! =>" + code);
      } else if (code.equalsIgnoreCase("211")) {
         logger.info("## 开始充装时间格式不正确：yyyy-MM-dd HH:mm:ss!! =>" + code);
      } else if (code.equalsIgnoreCase("212")) {
         logger.info("## 结束充装时间格式不正确：yyyy-MM-dd HH:mm:ss!! =>" + code);
      } else if (code.equalsIgnoreCase("213")) {
         logger.info("## 系统内部错误，请联系管理员处理!! =>" + code);
      } else if (code.equalsIgnoreCase("214")) {
         logger.info("## 未获取到充装人信息，请联系管理员处理!! =>" + code);
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
