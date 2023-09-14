package com.thirdinf.client1;

import org.apache.log4j.Logger;

public class MyLog1 {
   private static final Logger logger = Logger.getLogger(MyLog1.class);

   static void showStatus(int status) {
      if (200 == status) {
         logger.info("## 服务器已经接受并处理请求!! =>" + status);
      } else if (201 == status) {
         logger.info("## 服务器已经接受请求，正在创建缓存文件!! =>" + status);
      } else if (202 == status) {
         logger.info("## 服务器已经接受请求，尚未处理!! =>" + status);
      } else if (203 == status) {
         logger.info("## 服务器已经接受请求，响应数据来自缓存文件!! =>" + status);
      } else if (204 == status) {
         logger.info("## 服务器已经接受请求，没有需要返回的数据!! =>" + status);
      } else if (206 == status) {
         logger.info("## 服务器已经接受并处理部分请求，响应数据来自缓存文件!! =>" + status);
      } else if (400 == status) {
         logger.info("## 服务器拒绝请求，不能理解请求的语法!! =>" + status);
      } else if (401 == status) {
         logger.info("## 服务器拒绝请求，需要重新进行身份认证!! =>" + status);
      } else if (403 == status) {
         logger.info("## 服务器拒绝请求，客户端没有权限访问!! =>" + status);
      } else {
         logger.info("## 服务器返回未知状态码!! =>" + status);
      }

   }
}
