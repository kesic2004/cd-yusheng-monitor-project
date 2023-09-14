package com.myserver;

import com.model.device.service.DeviceService;
import com.model.license.service.LicenseService;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.service.TbldataService;
import com.model.tbldevicelog.service.TbldevicelogService;
import com.model.user.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class DataHandler extends ChannelInboundHandlerAdapter {
   private static final Logger logger = Logger.getLogger(DataHandler.class);
   private AtomicInteger connectNum;
   private TbldataService tbldataService;
   private TbldevicelogService tbldevicelogService;
   private UserService userService;
   private DeviceService deviceService;
   private TblclientService tblclientService;
   private ApplicationContext applicationContext;
   private LicenseService licenseService;

   public DataHandler(AtomicInteger connectNum, TbldataService tbldataService, TbldevicelogService tbldevicelogService, UserService userService, DeviceService deviceService, TblclientService tblclientService, LicenseService licenseService, ApplicationContext applicationContext) {
      this.connectNum = connectNum;
      this.tbldataService = tbldataService;
      this.tbldevicelogService = tbldevicelogService;
      this.userService = userService;
      this.deviceService = deviceService;
      this.tblclientService = tblclientService;
      this.licenseService = licenseService;
      this.applicationContext = applicationContext;
   }

   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      BizTool.PrintTime("+++[channelActive]当前时间：");
      BizTool.PrintRemoteHost("+++[channelActive] ", ctx);
      logger.info("-> 初始化设备状态 !!");
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      BizTool.PrintTime("---[channelInactive]当前时间：");
      BizTool.PrintRemoteHost("---[channelInactive] ", ctx);
   }

   public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
      super.channelRegistered(ctx);
      BizTool.PrintTime("+++[channelRegistered]当前时间：");
      BizTool.PrintRemoteHost("+++[channelRegistered] ", ctx);
      logger.info("++@@@ current connected(当前连接数) :" + this.connectNum.incrementAndGet());
   }

   public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
      super.channelUnregistered(ctx);
      BizTool.PrintTime("---[channelUnregistered]当前时间：");
      BizTool.PrintRemoteHost("---[channelUnregistered] ", ctx);
      logger.info("--@@@ current connected(当前连接数) : " + this.connectNum.decrementAndGet());
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      try {
         LinkedHashMap<String, Object> map = (LinkedHashMap)msg;
         if (map != null) {
            BizTool.PrintTime("当前时间：");
            BizTool.dumpResult(map);
            DbOperator.saveDataToClient(map, this.applicationContext);
         }
      } finally {
         ReferenceCountUtil.release(msg);
      }

   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      logger.info("...............数据接收-完毕...............");
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      BizTool.PrintTime("@@@[业务处理异常]当前时间：");
      BizTool.PrintRemoteHost("@@@[业务处理异常] ", ctx);
      logger.info("...............业务处理异常...............");
      logger.info(cause.toString());
      ctx.close();
   }

   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
      IdleStateEvent event = (IdleStateEvent)evt;
      String eventType = null;
      switch(event.state()) {
      case READER_IDLE:
         eventType = "读空闲";
         break;
      case WRITER_IDLE:
         eventType = "写空闲";
         break;
      case ALL_IDLE:
         eventType = "读写空闲";
      }

   }
}
