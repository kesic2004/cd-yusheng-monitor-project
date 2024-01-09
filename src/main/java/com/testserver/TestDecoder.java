package com.testserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class TestDecoder extends ByteToMessageDecoder {
   private static final SimpleDateFormat sdf    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private static final Logger           logger = Logger.getLogger(TestDecoder.class);
   private static final String clientIpAllowed = "127.0.0.1";

   protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
      System.out.println("...............客户端连接服务器...............");
      String client = ctx.channel().remoteAddress().toString();
      System.out.println("客户端IP: " + client);
      System.out.println("连接时间：" + sdf.format(new Date()));
      if (StringUtils.isNotBlank(client) && client.contains(clientIpAllowed)) {
         System.out.println("...............客户端IP合法，开始数据解析 ...............");
         LinkedHashMap<String, Object> dataMap = new LinkedHashMap();
         new StringBuffer();
         dataMap.put("dataTime", sdf.format(new Date()));
         ByteBuf headFlag = buf.readBytes(3);
         dataMap.put("headFlag", ByteBufUtil.hexDump(headFlag));
         ByteBuf command = buf.readBytes(2);
         dataMap.put("command", ByteBufUtil.hexDump(command));
         dataMap.put("packetID", buf.readUnsignedByte());
         dataMap.put("retcode", buf.readShortLE());
         int DataLen = buf.readUnsignedShortLE();
         dataMap.put("DataLen", DataLen);
         if (DataLen > 0) {
            ByteBuf DeviceNo = buf.readBytes(12);
            dataMap.put("DeviceNo", this.convertByteBufToString(DeviceNo));
            ByteBuf CarNum = buf.readBytes(16);
            dataMap.put("CarNum", this.convertByteBufToString(CarNum));
            ByteBuf DriverID = buf.readBytes(16);
            dataMap.put("DriverID", this.convertByteBufToString(DriverID));
            StringBuffer sb = new StringBuffer();

            int i;
            for(i = 0; i < 4; ++i) {
               if (i == 0) {
                  sb.append(buf.readUnsignedByte() + ".");
               } else {
                  sb.append(String.valueOf(buf.readUnsignedByte()));
               }
            }

            dataMap.put("x", sb.toString());
            sb = new StringBuffer();

            for(i = 0; i < 4; ++i) {
               if (i == 0) {
                  sb.append(buf.readUnsignedByte() + ".");
               } else {
                  sb.append(String.valueOf(buf.readUnsignedByte()));
               }
            }

            dataMap.put("y", sb.toString());
            dataMap.put("speed", buf.readUnsignedByte());
            dataMap.put("DrverAuth", buf.readUnsignedByte());
            dataMap.put("gpsinvalid", buf.readUnsignedByte());
            dataMap.put("overZoneWarn", buf.readUnsignedByte());
            dataMap.put("overSpeedWarn", buf.readUnsignedByte());
            dataMap.put("overWeightWarn", buf.readUnsignedByte());
            dataMap.put("TryeWarn", buf.readUnsignedByte());
            dataMap.put("DriverStatusWarn", buf.readUnsignedByte());
            dataMap.put("rpm", buf.readUnsignedShortLE());
            ByteBuf direc = buf.readBytes(10);
            dataMap.put("direc", this.convertByteBufToString(direc));
            dataMap.put("ContinueDrvTime", buf.readUnsignedShortLE());
            int DataLen2 = buf.readUnsignedShortLE();
            dataMap.put("DataLen2", DataLen2);
            if (DataLen2 > 0) {
               long DataType = buf.readUnsignedIntLE();
               dataMap.put("DataType", DataType);
               long DataLen3 = buf.readUnsignedIntLE();
               dataMap.put("DataLen3", DataLen3);
               if (DataLen3 > 0L) {
                  if (DataType == 1L) {
                     dataMap.put("BoxTemp", buf.readIntLE());
                     dataMap.put("humidity", buf.readIntLE());
                     dataMap.put("ObjectWarn", buf.readUnsignedIntLE());
                     dataMap.put("resv", buf.readUnsignedIntLE());
                  } else {
                     ByteBuf FileName;
                     ByteBuf url;
                     if (DataType == 2L) {
                        FileName = buf.readBytes(32);
                        dataMap.put("TryeWarType", this.convertByteBufToString(FileName));
                        url = buf.readBytes(32);
                        dataMap.put("TryeID", this.convertByteBufToString(url));
                        sb = new StringBuffer();

                        for(int ia = 0; ia < 32; ++ia) {
                           sb.append(buf.readUnsignedShortLE());
                        }

                        dataMap.put("TryeValue", sb.toString());
                        ByteBuf AxiesID = buf.readBytes(16);
                        dataMap.put("AxiesID", this.convertByteBufToString(AxiesID));
                        sb = new StringBuffer();

                        for(int ic = 0; ic < 32; ++ic) {
                           sb.append(buf.readUnsignedShortLE());
                        }

                        dataMap.put("AxiesValue", sb.toString());
                     } else if (DataType == 3L) {
                        dataMap.put("weight", buf.readUnsignedIntLE());
                     } else if (DataType == 4L) {
                        dataMap.put("WarnType", buf.readUnsignedIntLE());
                     } else if (DataType == 5L) {
                        FileName = buf.readBytes(20);
                        dataMap.put("FileName", this.convertByteBufToString(FileName));
                        url = buf.readBytes(256);
                        dataMap.put("url", this.convertByteBufToString(url));
                     }
                  }
               }
            }

            dataMap.put("CRC", buf.readUnsignedShort());
         }

         out.add(dataMap);
      } else {
         System.out.println("...............服务端暂不接收该IP数据 ...............");
      }

   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      System.out.println("...............数据解析异常 ...............");
      cause.printStackTrace();
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
