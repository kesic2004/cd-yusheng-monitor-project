package com.myserver;

import com.tools.XTool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class DataDecoder extends ByteToMessageDecoder {
   private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private static final Logger logger = Logger.getLogger(DataDecoder.class);

   protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
      logger.info("...............开始数据解析 ...............");
      if (buf.readableBytes() >= 42) {
         LinkedHashMap<String, Object> dataMap = new LinkedHashMap();
         ByteBuf headFlag = buf.readSlice(3);
         byte[] b = ByteBufUtil.getBytes(headFlag);
         if (b.length != 3 || b[0] != -1 || b[1] != -1 || b[2] != -1) {
            String client = ctx.channel().remoteAddress().toString();
            logger.info("@@接受到未知数据包头 客户端IP: " + client);
            ctx.close();
            return;
         }

         List<Byte> byteList = new ArrayList();
         byteList.add((byte)-1);
         byteList.add((byte)-1);
         byteList.add((byte)-1);
         this.readByteData("province", buf, dataMap, byteList);
         this.readByteData("city", buf, dataMap, byteList);
         this.readByteData("station", buf, dataMap, byteList);
         this.readByteData("address", buf, dataMap, byteList);
         this.readByteData("points", buf, dataMap, byteList);
         String dataTag = "data";

         for(int i = 0; i < 16; ++i) {
            this.readShortData(dataTag + i, buf, dataMap, byteList);
         }

         this.readShortData("machineno", buf, dataMap, byteList);
         buf.readUnsignedShortLE();
         byteList.clear();
         byteList = null;
         out.add(dataMap);
      }

   }

   public void readShortData(String key, ByteBuf buf, LinkedHashMap<String, Object> dataMap, List<Byte> byteList) {
      ByteBuf bBuf = buf.readSlice(2);
      byte[] bData = ByteBufUtil.getBytes(bBuf);
      dataMap.put(key, XTool.getShort(bData));
      byte[] var7 = bData;
      int var8 = bData.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         byte b1 = var7[var9];
         byteList.add(b1);
      }

   }

   public void readByteData(String key, ByteBuf buf, LinkedHashMap<String, Object> dataMap, List<Byte> byteList) {
      byte bData = buf.readByte();
      dataMap.put(key, bData);
      byteList.add(bData);
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      BizTool.PrintTime("@@@[数据解析异常]当前时间：");
      BizTool.PrintRemoteHost("@@@[数据解析异常] ", ctx);
      logger.info("...............数据解析异常 ...............");
      logger.info(cause.toString());
      ctx.close();
   }

   public String convertByteBufToString(ByteBuf buf) throws UnsupportedEncodingException {
      String str;
      if (buf.hasArray()) {
         str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
      } else {
         byte[] bytes = new byte[buf.readableBytes()];
         buf.getBytes(buf.readerIndex(), bytes);
         str = new String(bytes, 0, buf.readableBytes());
      }

      return str;
   }
}
