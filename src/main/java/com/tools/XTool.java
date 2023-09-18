package com.tools;

public class XTool {
   public static byte[] unsignedShortToByte2(short s) {
      byte[] targets = new byte[]{(byte)(s & 255), (byte)(s >> 8 & 255)};
      return targets;
   }

   public static byte[] unsignedShortToByte2Ex(short s) {
      byte[] targets = new byte[]{(byte)(s >> 8 & 255), (byte)(s & 255)};
      return targets;
   }

   public static int byte2Integer(byte b) {
      int result = b & 255;
      return result;
   }

   public static byte byteToBit(byte b, int offset) {
      return (byte)(b >> offset & 1);
   }

   public static byte byteToBit2(byte b, int offset) {
      return (byte)(b >> offset & 3);
   }

   public static byte[] toLH(short i) {
      byte[] result = new byte[]{(byte)(i >> 8 & 255), (byte)(i & 255)};
      return result;
   }

   public static byte[] toLH(int i) {
      byte[] result = new byte[]{(byte)(i >> 24 & 255), (byte)(i >> 16 & 255), (byte)(i >> 8 & 255), (byte)(i & 255)};
      return result;
   }

   private static byte[] toLH(float f) {
      return toLH(Float.floatToRawIntBits(f));
   }

   public static short getShort(byte[] b) {
      return (short)(b[1] << 8 | b[0] & 255);
   }
}
