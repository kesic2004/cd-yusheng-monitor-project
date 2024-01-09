package com.myserver;

import com.alibaba.fastjson.JSON;
import com.tools.CRC3;
import com.tools.XTool;
import io.netty.channel.ChannelHandlerContext;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;

public class BizTool {
   private static final Logger logger = Logger.getLogger(BizTool.class);

   /**
    * 用日志输出的方式输出时间
    * @param prefix
    */
   static void PrintTime(String prefix) {
      String msg = "";
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.SSS");
      msg = msg + "[" + sdf.format(date) + "]";
      logger.info(prefix + msg);
   }

   /**
    * 用日志输出的方式输出对方主机的IP和port
    * @param prefix
    * @param ctx
    */
   static void PrintRemoteHost(String prefix, ChannelHandlerContext ctx) {
      InetSocketAddress insocket = (InetSocketAddress)ctx.channel().remoteAddress();
      logger.info(prefix + " RemoteIp:RemotePort => " + insocket.getAddress().getHostAddress() + ":" + insocket.getPort());
   }

   /**
    * 用日志输出的方式输出map中所有的键值对
    * @param map
    */
   static void dumpResult(LinkedHashMap<String, Object> map) {
      Iterator var1 = map.entrySet().iterator();

      while(var1.hasNext()) {
         Entry<String, Object> entry = (Entry)var1.next();
         String mapKey = (String)entry.getKey();
         String mapValue = entry.getValue().toString();
         logger.info(mapKey + "-> " + mapValue);
      }

      String jsonString = JSON.toJSONString(map);
      logger.info("数据解析内容:" + jsonString);
   }

   /**
    * 较验map中各个值的CRC16
    * @param map
    * @return
    */
   static boolean checkInboundCRC(LinkedHashMap<String, Object> map) {
      boolean bOK = false;
      byte[] buf = new byte[38];
      int iStart = 0;
      byte[] temp = new byte[3];

      for(int ii = 0; ii < temp.length; ++ii) {
         temp[ii] = -1;
      }

      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart = iStart + temp.length;
      temp = new byte[]{(Byte)map.get("cmd")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("province")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("city")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("station")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("address")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("command")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("next")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("card1")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("card2")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("card3")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("card4")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = XTool.unsignedShortToByte2((Short)map.get("stamp"));
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = XTool.unsignedShortToByte2((Short)map.get("weightend"));
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = XTool.unsignedShortToByte2((Short)map.get("weightgross"));
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = XTool.unsignedShortToByte2((Short)map.get("weightsetting"));
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = XTool.unsignedShortToByte2((Short)map.get("client"));
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("executor")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("gxcode")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("fscode")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("forder")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("year")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("month")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("day")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("hour")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("minute")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = new byte[]{(Byte)map.get("second")};
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = XTool.unsignedShortToByte2((Short)map.get("usedtime"));
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      iStart += temp.length;
      temp = XTool.unsignedShortToByte2((Short)map.get("ticket"));
      System.arraycopy(temp, 0, buf, iStart, temp.length);
      String strHexBytes = DatatypeConverter.printHexBinary(buf);
      logger.info(strHexBytes);
      Integer resCRC = CRC3.get_crc16(buf, buf.length);
      logger.info("@@ 计算接收数据的CRC:" + resCRC.toString());
      int recvCRC = (Integer)map.get("CRC");
      if (recvCRC == resCRC) {
         logger.info("@@ 计算接收数据的 CRC check is ok !!!");
         bOK = true;
      } else {
         logger.info("@@ 计算接收数据的CRC check is failed !!!");
      }

      return bOK;
   }

   static boolean checkInboundCRC_Ex(LinkedHashMap<String, Object> map) {
      boolean bOK = false;
      byte[] buf = (byte[]) map.get("RawData");
      String strHexBytes = DatatypeConverter.printHexBinary(buf);
      logger.info(strHexBytes);
      Integer resCRC = CRC3.get_crc16(buf, buf.length);
      logger.info("@@@ 计算接收数据的CRC:" + resCRC.toString());
      int recvCRC = (Integer)map.get("CRC");
      if (recvCRC == resCRC) {
         logger.info("@@@ 计算接收数据的 CRC check is ok !!!");
         bOK = true;
      } else {
         logger.info("@@@ 计算接收数据的CRC check is failed !!!");
      }

      return bOK;
   }

   /**
    * 把addr, cmd, command, forder转换为字节数组，数组中数据为：addr, cmd, 0x04, command, forder,
    * 再把这5个byte计算crc16，计算后的16位数，低8位在前，高8位在后，放在这5个字节的后面
    * @param addr
    * @param cmd
    * @param command
    * @param forder
    * @return
    */
   static byte[] createResponseOkPkg(byte addr, byte cmd, byte command, byte forder) {
      byte[] buf = new byte[7];
      byte[] temp = new byte[]{addr};
      System.arraycopy(temp, 0, buf, 0, 1);
      temp[0] = cmd;
      System.arraycopy(temp, 0, buf, 1, 1);
      temp[0] = 4;
      System.arraycopy(temp, 0, buf, 2, 1);
      temp[0] = command;
      System.arraycopy(temp, 0, buf, 3, 1);
      temp[0] = forder;
      System.arraycopy(temp, 0, buf, 4, 1);
      Integer resCRC = CRC3.get_crc16(buf, 5);
      logger.info("@@ 发送确认数据 CRC :" + resCRC.toString());
      temp = XTool.unsignedShortToByte2(resCRC.shortValue());
      System.arraycopy(temp, 0, buf, 5, 2);
      return buf;
   }

   /**
    * 把addr, cmd转换为转换为字节数组，数组中数据为：addr, cmd, 再把这2个byte计算crc16，计算后的16位数，低8位在前，高8位在后，
    * 放在这2个字节的后面
    * @param addr
    * @param cmd
    * @return
    */
   static byte[] createRetryResponsePkg(byte addr, byte cmd) {
      byte[] buf = new byte[4];
      byte[] tmp = new byte[]{addr};
      System.arraycopy(tmp, 0, buf, 0, 1);
      tmp[0] = cmd;
      System.arraycopy(tmp, 0, buf, 1, 1);
      Integer resCRC = CRC3.get_crc16(buf, 2);
      logger.info("@@ 发送重发数据 CRC :" + resCRC.toString());
      tmp = XTool.unsignedShortToByte2(resCRC.shortValue());
      System.arraycopy(tmp, 0, buf, 2, 2);
      return buf;
   }

   /**
    * 把addr, cmd转换为转换为字节数组，数组中数据为：addr, cmd, command, 再把这3个byte计算crc16，计算后的16位数，低8位在前，高8位在后，
    * 放在这3个字节的后面
    * @param addr
    * @param cmd
    * @param command
    * @return
    */
   static byte[] createStartAndStopResponsePkg(byte addr, byte cmd, byte command) {
      byte[] buf = new byte[5];
      byte[] temp = new byte[]{addr};
      System.arraycopy(temp, 0, buf, 0, 1);
      temp[0] = cmd;
      System.arraycopy(temp, 0, buf, 1, 1);
      temp[0] = command;
      System.arraycopy(temp, 0, buf, 2, 1);
      Integer resCRC = CRC3.get_crc16(buf, 3);
      logger.info("@@ 启动/停止灌装机验证 CRC :" + resCRC.toString());
      temp = XTool.unsignedShortToByte2(resCRC.shortValue());
      System.arraycopy(temp, 0, buf, 3, 2);
      return buf;
   }

    /**
     * 把字节数组转换为字符串
     * @param index
     * @return
     */
   public static String createQrCode(byte[] index) {
      String qrCode = new String(index);
      logger.info("-> QrCode : " + qrCode);
      return qrCode;
   }
}
