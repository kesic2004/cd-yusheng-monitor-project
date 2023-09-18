package com.testserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

public class TestServerHandler extends ChannelInboundHandlerAdapter {
   private static final Logger logger = Logger.getLogger(TestServerHandler.class);

   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      System.out.println("服务端：" + ctx.channel().localAddress().toString() + " 通道开启！");
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      System.out.println("服务端：" + ctx.channel().localAddress().toString() + " 通道关闭！");
   }

   private String getMessage(ByteBuf buf) {
      byte[] con = new byte[buf.readableBytes()];
      buf.readBytes(con);

      try {
         return new String(con, "GBK");
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
         return null;
      }
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      LinkedHashMap<String, Object> map = (LinkedHashMap)msg;
      Iterator var4 = map.entrySet().iterator();

      while(var4.hasNext()) {
         Entry<String, Object> entry = (Entry)var4.next();
         String mapKey = (String)entry.getKey();
         String mapValue = entry.getValue().toString();
         System.out.println(mapKey + ":" + mapValue);
      }

      ReferenceCountUtil.release(msg);
   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      System.out.println("...............数据接收-完毕...............");
      ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      System.out.println("...............业务处理异常...............");
      cause.printStackTrace();
      ctx.close();
   }
}
