package com.myserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.apache.log4j.Logger;

public class DeviceStateContext {
   private static final Logger logger = Logger.getLogger(DeviceStateContext.class);

   public static void setDevState(ChannelHandlerContext ctx, Integer devState) {
      AttributeKey<Integer> attrKey = AttributeKey.valueOf("devstate");
      Attribute<Integer> srcdataAttr = ctx.channel().attr(attrKey);
      srcdataAttr.set(devState);
   }

   public static Integer getDevState(ChannelHandlerContext ctx) {
      AttributeKey<Integer> nameAttrKey = AttributeKey.valueOf("devstate");
      Attribute<Integer> attr = ctx.channel().attr(nameAttrKey);
      Integer devstate = (Integer)attr.get();
      return devstate;
   }

   public static void setQrCode(ChannelHandlerContext ctx, String qrValue) {
      AttributeKey<String> attrKey = AttributeKey.valueOf("qrcode");
      Attribute<String> srcdataAttr = ctx.channel().attr(attrKey);
      srcdataAttr.set(qrValue);
   }

   public static String getQrCode(ChannelHandlerContext ctx) {
      AttributeKey<String> nameAttrKey = AttributeKey.valueOf("qrcode");
      Attribute<String> attr = ctx.channel().attr(nameAttrKey);
      String val = (String)attr.get();
      return val;
   }

   public static void setAddressCode(ChannelHandlerContext ctx, byte addresscode) {
      AttributeKey<Byte> attrKey = AttributeKey.valueOf("addresscode");
      Attribute<Byte> srcdataAttr = ctx.channel().attr(attrKey);
      srcdataAttr.set(addresscode);
   }

   public static Byte getAddressCode(ChannelHandlerContext ctx) {
      AttributeKey<Byte> nameAttrKey = AttributeKey.valueOf("addresscode");
      Attribute<Byte> attr = ctx.channel().attr(nameAttrKey);
      Byte val = (Byte)attr.get();
      return val;
   }

   public static void setLoginParams(ChannelHandlerContext ctx, Integer loginState, String qrCode, byte addressCode) {
      logger.info("-> 设置用户登录状态标志 ok !!");
      setDevState(ctx, loginState);
      setQrCode(ctx, qrCode);
      setAddressCode(ctx, addressCode);
   }

   public static void setParam1(ChannelHandlerContext ctx, String voucher) {
      AttributeKey<String> attrKey = AttributeKey.valueOf("param1");
      Attribute<String> srcdataAttr = ctx.channel().attr(attrKey);
      srcdataAttr.set(voucher);
   }

   public static String getParam1(ChannelHandlerContext ctx) {
      AttributeKey<String> nameAttrKey = AttributeKey.valueOf("param1");
      Attribute<String> attr = ctx.channel().attr(nameAttrKey);
      String val = (String)attr.get();
      return val;
   }

   public static void setParam2(ChannelHandlerContext ctx, String param) {
      AttributeKey<String> attrKey = AttributeKey.valueOf("param2");
      Attribute<String> srcdataAttr = ctx.channel().attr(attrKey);
      srcdataAttr.set(param);
   }

   public static String getParam2(ChannelHandlerContext ctx) {
      AttributeKey<String> nameAttrKey = AttributeKey.valueOf("param2");
      Attribute<String> attr = ctx.channel().attr(nameAttrKey);
      String val = (String)attr.get();
      return val;
   }
}
