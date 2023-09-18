package com.myserver;

import com.model.currentuser.entity.Currentuser;
import com.model.currentuser.service.CurrentuserService;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.fj.service.FjBottleService;
import com.model.fj.service.FjDwService;
import com.model.license.entity.License;
import com.model.license.service.LicenseService;
import com.model.special.entity.Special;
import com.model.special.service.SpecialService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.service.TbldataService;
import com.model.tbldevicelog.service.TbldevicelogService;
import com.model.tbltoken.service.TbltokenService;
import com.model.user.entity.UserEx;
import com.model.user.service.UserService;
import com.model.xk.service.XkCylinderrecService;
import com.model.xk.service.XkStationmapService;
import com.thirdinf.BaseInvokeInf;
import com.thirdinf.client10.MyInf10;
import com.thirdinf.client10.MyInvoke10;
import com.thirdinf.client11.MyInf11;
import com.thirdinf.client11.MyInvoke11;
import com.thirdinf.client12.MyInf12;
import com.thirdinf.client12.MyInvoke12;
import com.thirdinf.client13.MyInf13;
import com.thirdinf.client13.MyInvoke13;
import com.thirdinf.client14.MyInf14;
import com.thirdinf.client14.MyInvoke14;
import com.thirdinf.client15.MyInf15;
import com.thirdinf.client15.MyInvoke15;
import com.thirdinf.client16.MyInf16;
import com.thirdinf.client16.MyInvoke16;
import com.thirdinf.client2.MyInf2;
import com.thirdinf.client2.MyInvoke2;
import com.thirdinf.client3.MyInf3;
import com.thirdinf.client3.MyInvoke3;
import com.thirdinf.client4.MyInf4;
import com.thirdinf.client4.MyInvoke4;
import com.thirdinf.client5.MyInf5;
import com.thirdinf.client5.MyInvoke5;
import com.thirdinf.client6.MyInf6;
import com.thirdinf.client6.MyInvoke6;
import com.thirdinf.client7.MyInf7;
import com.thirdinf.client7.MyInvoke7;
import com.thirdinf.client8.MyInf8;
import com.thirdinf.client8.MyInvoke8;
import com.thirdinf.client9.MyInf9;
import com.thirdinf.client9.MyInvoke9;
import com.tools.BASE64;
import com.tools.XTool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.DatatypeConverter;
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
   private CurrentuserService currentuserService;
   private LicenseService licenseService;
   private XkCylinderrecService xkCylinderrecService;
   private SpecialService specialService;
   private XkStationmapService xkStationmapService;
   private FjBottleService fjBottleService;
   private FjDwService fjDwService;
   private TbltokenService tbltokenService;

   public DataHandler(AtomicInteger connectNum, TbldataService tbldataService, TbldevicelogService tbldevicelogService, UserService userService, DeviceService deviceService, TblclientService tblclientService, CurrentuserService currentuserService, LicenseService licenseService, XkCylinderrecService xkCylinderrecService, SpecialService specialService, XkStationmapService xkStationmapService, ApplicationContext applicationContext, FjBottleService fjBottleService, FjDwService fjDwService, TbltokenService tbltokenService) {
      this.connectNum = connectNum;
      this.tbldataService = tbldataService;
      this.tbldevicelogService = tbldevicelogService;
      this.userService = userService;
      this.deviceService = deviceService;
      this.tblclientService = tblclientService;
      this.currentuserService = currentuserService;
      this.licenseService = licenseService;
      this.xkCylinderrecService = xkCylinderrecService;
      this.applicationContext = applicationContext;
      this.specialService = specialService;
      this.xkStationmapService = xkStationmapService;
      this.fjBottleService = fjBottleService;
      this.fjDwService = fjDwService;
      this.tbltokenService = tbltokenService;
   }

   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      BizTool.PrintTime("+++[channelActive]当前时间：");
      BizTool.PrintRemoteHost("+++[channelActive] ", ctx);
      DeviceStateContext.setLoginParams(ctx, 0, "", (byte)0);
      DeviceStateContext.setParam1(ctx, "");
      DeviceStateContext.setParam2(ctx, "");
      logger.info("-> 初始化设备状态 !!");
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      BizTool.PrintTime("---[channelInactive]当前时间：");
      BizTool.PrintRemoteHost("---[channelInactive] ", ctx);
      DeviceStateContext.setLoginParams(ctx, 0, "", (byte)0);
      DeviceStateContext.setParam1(ctx, "");
      DeviceStateContext.setParam2(ctx, "");
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
         BizTool.PrintTime("当前时间：");
         BizTool.dumpResult(map);
         if (map.get("cmd") == null) {
            BizTool.PrintTime("???[接收命令字为空]当前时间：");
            BizTool.PrintRemoteHost("???[接收命令字为空] ", ctx);
            logger.info("@@-> cmd is null!!");
            ctx.close();
            return;
         }

         byte cmd = (Byte)map.get("cmd");
         String strJson;
         String qrCode;
         String voucher;
         if (-32 == cmd) {
            BizTool.PrintTime("###[接收二维码登录认证]当前时间：");
            BizTool.PrintRemoteHost("###[接收二维码登录认证] ", ctx);
            logger.info("@@-> 接收扫描枪获得数据!!");
            boolean bCRC_Ok = BizTool.checkInboundCRC_Ex(map);
            if (bCRC_Ok) {
               Device device = DbOperator.findDevice(this.deviceService, map);
               if (device == null) {
                  logger.info("-> 设备未注册!!");
                  ctx.close();
                  return;
               }

               if (device.getState() == 0) {
                  logger.info("-> 设备  Stop using!!");
                  ctx.close();
                  return;
               }

               if (device.getQrtype() == 1) {
                  byte[] buf = (byte[])((byte[])map.get("RawQrcode"));
                  qrCode = DatatypeConverter.printHexBinary(buf);
                  map.put("qrcode", qrCode);
               }

               Integer rState = 85;
               Special special = DbOperator.findSpecial(this.specialService, map);
               voucher = (String)map.get("qrcode");
               if (special != null && special.getBarcode().equalsIgnoreCase(voucher)) {
                  rState = 170;
               } else {
                  Tblclient client = DbOperator.findClient(this.tblclientService, device.getClientid());
                  if (client == null) {
                     logger.info("-> 客户二维码进行本地验证  !!");
                     UserEx userEx = DbOperator.findUserJoinStation(this.userService, map);
                     rState = this.verifyUser(userEx);
                  } else {
                     logger.info("-> 客户二维码进行接口调用验证  !!");
                     BaseInvokeInf baseInf = this.getInvokeObject(client, map);
                     if (baseInf != null) {
                        strJson = baseInf.checkQrAndAddress(client.getCheckurl(), map);
                        if (!strJson.equalsIgnoreCase("")) {
                           rState = baseInf.parseData(strJson);
                        }

                        this.setVoucher(ctx, client, baseInf, rState);
                     } else {
                        logger.info("-> 接口调用未定义!! clientid :" + client.getId());
                     }
                  }
               }

               DbOperator.delCurrentuser(this.currentuserService, map);
               if (rState == 170) {
                  DbOperator.saveCurrentUser(this.currentuserService, map);
                  DeviceStateContext.setLoginParams(ctx, 1, (String)map.get("qrcode"), (Byte)map.get("address"));
                  logger.info("-> 设备验证 [ok] ->可充!!");
               } else {
                  DeviceStateContext.setLoginParams(ctx, 0, "", (byte)0);
                  DeviceStateContext.setParam1(ctx, "");
                  DeviceStateContext.setParam2(ctx, "");
                  logger.info("-> 设备验证 [failed] ->不可充!!!");
               }

               DbOperator.saveLoginInfo(this.tbldevicelogService, map, rState);
               byte loginState = (byte)rState.intValue();
               this.sendbackLoginStateToClient(ctx, map, loginState);
            } else {
               logger.info("@@@ 二维码登录CRC 验证失败 !!!");
               this.sendbackRetryToClient(ctx, map);
            }
         } else {
            if (-83 == cmd) {
               BizTool.PrintTime("@@@[接收下位机发送的结束]当前时间：");
               BizTool.PrintRemoteHost("@@@[接收下位机发送的结束] ", ctx);
               DeviceStateContext.setLoginParams(ctx, 0, "", (byte)0);
               DeviceStateContext.setParam1(ctx, "");
               DeviceStateContext.setParam2(ctx, "");
               logger.info("-> 本次通讯结束!!!");
               ctx.close();
               return;
            }

            if (-92 != cmd && -91 != cmd && -90 != cmd && -89 != cmd && -88 != cmd) {
               BizTool.PrintTime("***[接收未知命令]当前时间：");
               BizTool.PrintRemoteHost("***[接收未知命令] ", ctx);
               logger.info("@@-> cmd is Unknown!!");
               ctx.close();
            } else {
               BizTool.PrintTime("$$$[接收灌装<正常结束状态>下的数据]当前时间：");
               BizTool.PrintRemoteHost("$$$[接收灌装<正常结束状态>下的数据] ", ctx);
               String qrCodeFromPkg = DbOperator.getQrcode(map);
               logger.info("@@@@@@@@@@@-> 从报文解析出的二维码： " + qrCodeFromPkg);
               boolean identityCheck = false;
               if (-91 == cmd) {
                  logger.info("-> 非正常断电 重新进行身份验证");
                  identityCheck = true;
               } else if (DeviceStateContext.getDevState(ctx) != 1) {
                  logger.info("-> 非正常断网 重新进行身份验证");
                  identityCheck = true;
               } else {
                  logger.info("-> 不需要重新进行身份验证");
               }

               if (identityCheck) {
                  Currentuser currentuser = DbOperator.findCurrentuser(this.currentuserService, map);
                  if (currentuser != null) {
                     map.put("qrcode", currentuser.getQrcode());
                     Device device = DbOperator.findDevice(this.deviceService, map);
                     if (device == null) {
                        logger.info("-> 非正常断电: 设备未注册!!");
                        ctx.close();
                        return;
                     }

                     Tblclient client = DbOperator.findClient(this.tblclientService, device.getClientid());
                     Integer rState = 85;
                     Special special = DbOperator.findSpecial(this.specialService, map);
                     strJson = currentuser.getQrcode();
                     if (special != null && special.getBarcode().equalsIgnoreCase(strJson)) {
                        rState = 170;
                     } else if (client == null) {
                        logger.info("-> 非正常断电: 客户二维码进行本地验证  !!");
                        UserEx userEx = DbOperator.findUserJoinStation(this.userService, map);
                        rState = this.verifyUser(userEx);
                     } else {
                        logger.info("-> 非正常断电: 客户二维码进行接口调用验证  !!");
                        BaseInvokeInf baseInf = this.getInvokeObject(client, map);
                        if (baseInf != null) {
                           String strJsonA = baseInf.checkQrAndAddress(client.getCheckurl(), map);
                           if (!strJsonA.equalsIgnoreCase("")) {
                              rState = baseInf.parseData(strJsonA);
                           }

                           this.setVoucher(ctx, client, baseInf, rState);
                        } else {
                           logger.info("-> 非正常断电: 接口调用未定义！  !! clientid :" + client.getId());
                        }
                     }

                     if (rState == 170) {
                        DeviceStateContext.setLoginParams(ctx, 1, currentuser.getQrcode(), (Byte)map.get("address"));
                     }
                  } else {
                     logger.info("-> 数据库中查询缓存的当前用户为空  !!");
                  }
               }

               if (DeviceStateContext.getDevState(ctx) != 1) {
                  logger.info("-> 二维码未通过登录验证 => 清除下位机缓存数据??");
                  this.sendbackToClient(ctx, map);
                  return;
               }

               if (DeviceStateContext.getAddressCode(ctx) != (Byte)map.get("address")) {
                  logger.info("-> 设备地址码验证出错!!");
                  ctx.close();
                  return;
               }

               BizTool.PrintTime("当前时间：");
               logger.info("@@-> 接收灌装完成后数据!!");
               boolean bCRCOk = BizTool.checkInboundCRC_Ex(map);
               if (bCRCOk) {
                  qrCode = DeviceStateContext.getQrCode(ctx);
                  map.put("qrcode", qrCode);
                  voucher = DeviceStateContext.getParam1(ctx);
                  if (voucher != null) {
                     map.put("param1", voucher);
                  } else {
                     map.put("param1", "");
                  }

                  String param2 = DeviceStateContext.getParam2(ctx);
                  if (param2 != null) {
                     map.put("param2", param2);
                  } else {
                     map.put("param2", "");
                  }

                  boolean bSendBroadMessage = true;
                  Special special = DbOperator.findSpecial(this.specialService, map);
                  if (special != null && special.getBarcode().equalsIgnoreCase(qrCode)) {
                     bSendBroadMessage = false;
                  }

                  DbOperator.saveDataToDb(this.tbldataService, map, this.applicationContext, bSendBroadMessage);
                  DbOperator.delCurrentuser(this.currentuserService, map);
                  this.sendbackToClient(ctx, map);
                  DeviceStateContext.setLoginParams(ctx, 0, "", (byte)0);
                  DeviceStateContext.setParam1(ctx, "");
                  DeviceStateContext.setParam2(ctx, "");
               } else {
                  logger.info("@@@ 数据CRC 验证失败 !!!");
                  this.sendbackRetryToClient(ctx, map);
               }
            }
         }
      } finally {
         ReferenceCountUtil.release(msg);
      }

   }

   BaseInvokeInf getInvokeObject(Tblclient client, LinkedHashMap<String, Object> map) {
      BaseInvokeInf baseInf = null;
      if (client.getId() == MyInf2.Client_Id) {
         baseInf = new MyInvoke2();
         logger.info("验证-> 调用科宏接口");
      } else if (client.getId() == MyInf3.Client_Id) {
         baseInf = new MyInvoke3();
         logger.info("验证-> 调用乐山接口");
      } else if (client.getId() == MyInf4.Client_Id) {
         baseInf = new MyInvoke4(this.tbltokenService);
         map.put("p1", client.getP1());
         map.put("p2", client.getP2());
         map.put("p3", client.getP3());
         logger.info("验证-> 调用重庆延伸接口");
      } else if (client.getId() == MyInf5.Client_Id) {
         baseInf = new MyInvoke5();
         logger.info("验证-> 调用巴通达接口");
      } else if (client.getId() == MyInf6.Client_Id) {
         baseInf = new MyInvoke6();
         logger.info("验证-> 调用软宇接口");
      } else if (client.getId() == MyInf7.Client_Id) {
         baseInf = new MyInvoke7(this.xkCylinderrecService, this.xkStationmapService);
         logger.info("验证-> 调用祥康接口");
      } else if (client.getId() == MyInf8.Client_Id) {
         baseInf = new MyInvoke8();
         logger.info("验证-> 调用佳贝尔接口");
      } else if (client.getId() == MyInf9.Client_Id) {
         baseInf = new MyInvoke9();
         logger.info("验证-> 调用多利恒接口");
      } else if (client.getId() == MyInf10.Client_Id) {
         baseInf = new MyInvoke10();
         map.put("p1", client.getP1());
         map.put("p2", client.getP2());
         map.put("p3", client.getP3());
         logger.info("验证-> 调用中腾建设接口");
      } else if (client.getId() == MyInf11.Client_Id) {
         baseInf = new MyInvoke11();
         logger.info("验证-> 调用英维特接口");
      } else if (client.getId() == MyInf12.Client_Id) {
         baseInf = new MyInvoke12(this.fjBottleService, this.fjDwService);
         logger.info("验证-> 调用君则接口");
      } else if (client.getId() == MyInf13.Client_Id) {
         baseInf = new MyInvoke13();
         map.put("p1", client.getP1());
         map.put("p2", client.getP2());
         map.put("p3", client.getP3());
         logger.info("验证-> 调用安博(万源市泰平液化石油气销售有限公司)接口");
      } else if (client.getId() == MyInf14.Client_Id) {
         baseInf = new MyInvoke14();
         map.put("p1", client.getP1());
         map.put("p2", client.getP2());
         map.put("p3", client.getP3());
         logger.info("验证-> 调用安博(渠县泰昌燃气有限公司)接口");
      } else if (client.getId() == MyInf15.Client_Id) {
         baseInf = new MyInvoke15();
         map.put("p1", client.getP1());
         map.put("p2", client.getP2());
         map.put("p3", client.getP3());
         logger.info("验证-> 调用安博(万源市合兴液化石油气销售有限公司)接口");
      } else if (client.getId() == MyInf16.Client_Id) {
         baseInf = new MyInvoke16();
         logger.info("验证-> 调用旭永接口");
      } else {
         logger.info("验证-> 调用未知接口");
      }

      return (BaseInvokeInf)baseInf;
   }

   void setVoucher(ChannelHandlerContext ctx, Tblclient client, BaseInvokeInf baseInf, Integer rState) {
      if ((client.getId() == MyInf2.Client_Id || client.getId() == MyInf3.Client_Id || client.getId() == MyInf4.Client_Id || client.getId() == MyInf5.Client_Id || client.getId() == MyInf6.Client_Id || client.getId() == MyInf7.Client_Id || client.getId() == MyInf8.Client_Id || client.getId() == MyInf9.Client_Id || client.getId() == MyInf10.Client_Id || client.getId() == MyInf11.Client_Id || client.getId() == MyInf12.Client_Id || client.getId() == MyInf13.Client_Id || client.getId() == MyInf14.Client_Id || client.getId() == MyInf15.Client_Id || client.getId() == MyInf16.Client_Id) && rState == 170) {
         DeviceStateContext.setParam1(ctx, baseInf.getParam1());
         DeviceStateContext.setParam2(ctx, baseInf.getParam2());
      }

   }

   void sendbackLoginStateToClient(ChannelHandlerContext ctx, LinkedHashMap<String, Object> map, byte state) {
      byte addr_to = (Byte)map.get("address");
      byte cmd_to = -62;
      byte[] respByte = BizTool.createStartAndStopResponsePkg(addr_to, cmd_to, state);
      this.writeToClient(respByte, ctx);
   }

   void sendbackRetryToClient(ChannelHandlerContext ctx, LinkedHashMap<String, Object> map) {
      byte addr_to = (Byte)map.get("address");
      byte cmd_to = -54;
      byte[] byteSend = BizTool.createRetryResponsePkg(addr_to, cmd_to);
      this.writeToClient(byteSend, ctx);
   }

   void sendbackToClient(ChannelHandlerContext ctx, LinkedHashMap<String, Object> map) {
      byte addr_to = (Byte)map.get("address");
      byte cmd_to = -52;
      byte command_to = (Byte)map.get("command");
      byte forder_to = (Byte)map.get("forder");
      byte[] byteSend = BizTool.createResponseOkPkg(addr_to, cmd_to, command_to, forder_to);
      this.writeToClient(byteSend, ctx);
   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      logger.info("...............数据接收-完毕...............");
   }

   private void writeToClient(final byte[] zendStr, ChannelHandlerContext ctx) {
      try {
         ByteBuf bufff = Unpooled.buffer();
         bufff.writeBytes(zendStr);
         ctx.writeAndFlush(bufff).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
               if (future.isSuccess()) {
                  DataHandler.logger.info("回写成功!!");
               } else {
                  DataHandler.logger.info("回写失败!!");
                  DataHandler.logger.info("@@->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>-");
                  DataHandler.logger.info(future.cause().toString());
                  DataHandler.logger.info("@@-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<-");
               }

            }
         });
      } catch (Exception var4) {
         var4.printStackTrace();
         logger.info("调用通用writeToClient()异常" + var4.getMessage());
      }

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

   private Integer verifyUser(UserEx user) {
      Integer rState;
      if (user == null) {
         rState = XTool.byte2Integer((byte)2);
         logger.info("-> 设备验证 : 非本站瓶码 !!");
      } else if (user.getEndtime().before(new Date())) {
         rState = XTool.byte2Integer((byte)1);
         logger.info("-> 设备验证 : 超期码 !!");
      } else if (user.getState() == 1) {
         rState = XTool.byte2Integer((byte)-86);
         logger.info("-> 设备验证 : 正常起动 !!");
      } else if (user.getState() == 0) {
         rState = XTool.byte2Integer((byte)85);
         logger.info("-> 设备验证 : 停止灌装 !!");
      } else {
         rState = XTool.byte2Integer((byte)85);
         logger.info("-> 设备验证 : 未知状态 !!");
      }

      return rState;
   }

   private boolean checkLic() {
      try {
         License lic = DbOperator.findLicense(this.licenseService);
         if (lic == null) {
            logger.info("-> LICENSE NOT FOUND!!");
            return false;
         } else {
            if (lic.getState() != 9999) {
               String sCounter = BASE64.Decoder(lic.getCounter());
               String sDoomday = BASE64.Decoder(lic.getDoomday());
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               String dstr = sDoomday.substring(0, 4) + "-" + sDoomday.substring(4, 6) + "-" + sDoomday.substring(6, 8);
               Date date1 = sdf.parse(dstr);
               Date date2 = new Date();
               boolean before = date1.before(date2);
               if (before) {
                  logger.info("-> Date Expired!!");
                  return false;
               }

               String sDate_start = sdf.format(new Date()) + " 00:00:01";
               String sDate_end = sdf.format(new Date()) + " 23:59:59";
               int nCounter = DbOperator.queryCountByDate(this.tbldataService, sDate_start, sDate_end);
               if (nCounter > Integer.parseInt(sCounter)) {
                  logger.info("-> Use times of the day exceed the set value!!");
                  return false;
               }
            }

            return true;
         }
      } catch (Exception var12) {
         var12.printStackTrace();
         return false;
      }
   }
}
