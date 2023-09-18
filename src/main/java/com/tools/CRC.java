package com.tools;

public class CRC {
   private static final int BITS_OF_BYTE = 8;
   private static final int POLYNOMIAL = 40961;
   private static final int INITIAL_VALUE = 65535;

   public static int crc16(byte[] bytes) {
      int res = 65535;
      byte[] var2 = bytes;
      int var3 = bytes.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         byte data = var2[var4];
         res ^= data;

         for(int i = 0; i < 8; ++i) {
            res = (res & 1) == 1 ? res >> 1 ^ 'ꀁ' : res >> 1;
         }
      }

      return revert(res);
   }

   private static int revert(int src) {
      int lowByte = (src & '\uff00') >> 8;
      int highByte = (src & 255) << 8;
      return lowByte | highByte;
   }
}
