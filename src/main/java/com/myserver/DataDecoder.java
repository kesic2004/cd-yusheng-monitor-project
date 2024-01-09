package com.myserver;

import com.google.common.primitives.Bytes;
import com.tools.XTool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class DataDecoder extends ByteToMessageDecoder {
   private static final Logger logger = Logger.getLogger(DataDecoder.class);

   /**
    * @see ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
    * @param ctx
    * @param buf
    * @param out 已经解码的数据对象的list
    * @throws Exception
    * 解码
    */
   protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
      /*
       * 当数据长度达到12字节时符合解析的基本条件之一
       */
      if (buf.readableBytes() >= 12) {
         logger.info("...............开始数据解析 ...............");
         boolean bOK = true; // 读取是否成功，true表示成功，false表示失败
         int index = buf.readerIndex();
         LinkedHashMap<String, Object> dataMap = new LinkedHashMap();
         /*
          * 读取协议头(前3个字节)
          */
         ByteBuf headFlag = buf.readSlice(3);
         byte[] b = ByteBufUtil.getBytes(headFlag);
         /*
          * 如果不是指定的协议头
          */
         if (b.length != 3 || b[0] != -1 || b[1] != -1 || b[2] != -1) { // byte[0](前导字), byte[1](前导字), byte[2](前导字)
            String client = ctx.channel().remoteAddress().toString();
            logger.info("@@接受到未知数据包头 客户端IP: " + client);
            ctx.close();
            return;
         }

         List<Byte> byteList = new ArrayList();
         byteList.add((byte)-1);
         byteList.add((byte)-1);
         byteList.add((byte)-1);
         /*
          * 取指令
          */
         byte cmd = buf.readByte();
         dataMap.put("cmd", cmd); // byte[3](命令码)
         byteList.add(cmd);
         /*
          * 处理cmd的值为AD, A4, A5, A6, A7, A8
          * -83 = AD, -88 = - A8 -89 = A7 -90 = A6 -91 = A5 -92 = A4
          */
         if (-32 != cmd) {
            byte[] bytes;
            /*
             * 处理cmd的值为AD的
             */
            if (-83 == cmd) {
               if (buf.readableBytes() >= 8) {
                  this.readByteData("province", buf, dataMap, byteList);
                  this.readByteData("city", buf, dataMap, byteList);
                  this.readByteData("station", buf, dataMap, byteList);
                  this.readByteData("address", buf, dataMap, byteList);
                  this.readByteData("command", buf, dataMap, byteList);
                  this.readByteData("next", buf, dataMap, byteList);
                  bytes = Bytes.toArray(byteList);
                  dataMap.put("RawData", bytes);
                  dataMap.put("CRC", buf.readUnsignedShortLE());
               } else {
                  bOK = false;
               }
            } else {
               if (-92 != cmd && -91 != cmd && -90 != cmd && -89 != cmd && -88 != cmd) {
                  String client = ctx.channel().remoteAddress().toString();
                  logger.info("@@接受到未知命令码 客户端IP: " + client);
                  ctx.close();
                  return;
               }

               /*
                * 处理cmd的值为A4, A5, A6, A7, A8
                * -88 = - A8 -89 = A7 -90 = A6 -91 = A5 -92 = A4
                * ★☆
                */
               if (buf.readableBytes() >= 38) {
                  this.readByteData("province", buf, dataMap, byteList);
                  this.readByteData("city", buf, dataMap, byteList);
                  this.readByteData("station", buf, dataMap, byteList);
                  this.readByteData("address", buf, dataMap, byteList);
                  this.readByteData("command", buf, dataMap, byteList);
                  this.readByteData("next", buf, dataMap, byteList);
                  this.readByteData("card1", buf, dataMap, byteList);
                  this.readByteData("card2", buf, dataMap, byteList);
                  this.readByteData("card3", buf, dataMap, byteList);
                  this.readByteData("card4", buf, dataMap, byteList);
                  this.readShortDate("stamp", buf, dataMap, byteList);
                  this.readShortDate("weightend", buf, dataMap, byteList);
                  this.readShortDate("weightgross", buf, dataMap, byteList);
                  this.readShortDate("weightsetting", buf, dataMap, byteList);
                  this.readShortDate("client", buf, dataMap, byteList);
                  this.readByteData("executor", buf, dataMap, byteList);
                  this.readByteData("gxcode", buf, dataMap, byteList);
                  this.readByteData("fscode", buf, dataMap, byteList);
                  this.readByteData("forder", buf, dataMap, byteList);
                  this.readByteData("year", buf, dataMap, byteList);
                  this.readByteData("month", buf, dataMap, byteList);
                  this.readByteData("day", buf, dataMap, byteList);
                  this.readByteData("hour", buf, dataMap, byteList);
                  this.readByteData("minute", buf, dataMap, byteList);
                  this.readByteData("second", buf, dataMap, byteList);
                  this.readShortDate("usedtime", buf, dataMap, byteList);
                  this.readShortDate("ticket", buf, dataMap, byteList);
                  this.readShortDate("machineno", buf, dataMap, byteList);
                  bytes = Bytes.toArray(byteList);
                  dataMap.put("RawData", bytes);
                  dataMap.put("CRC", buf.readUnsignedShortLE());
               } else {
                  bOK = false;
               }
            }
         } else if (buf.readableBytes() < 29) {
            bOK = false;
         } else {
            this.readByteData("province", buf, dataMap, byteList);
            this.readByteData("city", buf, dataMap, byteList);
            this.readByteData("station", buf, dataMap, byteList);
            this.readByteData("address", buf, dataMap, byteList);
            byte len = buf.readByte();
            int qrCode_len = XTool.byte2Integer(len);
            byteList.add(len);
            logger.info("...............二维码有效长度：" + qrCode_len);
            if (qrCode_len > 20) {
               qrCode_len = 20;
            }

            ByteBuf rBuf = buf.readSlice(qrCode_len);
            byte[] rByte = ByteBufUtil.getBytes(rBuf);
            byte[] var15 = rByte;
            int var16 = rByte.length;

            for(int var17 = 0; var17 < var16; ++var17) {
               byte b1 = var15[var17];
               byteList.add(b1);
            }

            String qrCode = BizTool.createQrCode(rByte);
            logger.info("...............二维码：" + qrCode);
            dataMap.put("qrcode", qrCode);
            dataMap.put("RawQrcode", rByte);
            if (qrCode_len < 20) {
               Integer extra_N = 20 - qrCode_len;
               ByteBuf rBuf2 = buf.readSlice(extra_N);
               logger.info("...............二维码保留位长度：" + extra_N);
               byte[] rByte2 = ByteBufUtil.getBytes(rBuf2);
               byte[] var19 = rByte2;
               int var20 = rByte2.length;

               for(int var21 = 0; var21 < var20; ++var21) {
                  byte b2 = var19[var21];
                  byteList.add(b2);
               }
            }

            this.readShortDate("machineno", buf, dataMap, byteList);
            byte[] bytes = Bytes.toArray(byteList);
            dataMap.put("RawData", bytes);
            dataMap.put("CRC", buf.readUnsignedShortLE());
         }

         if (!bOK) {
            buf.readerIndex(index);
         } else {
            out.add(dataMap);
         }
      }

   }

   public void readShortDate(String key, ByteBuf buf, LinkedHashMap<String, Object> dataMap, List<Byte> byteList) {
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
