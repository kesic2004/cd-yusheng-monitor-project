package com.tools;

public class CRC3 {
   public static int get_crc16(byte[] bufData, int buflen) {
      int ret = false;
      int CRC = 65535;
      int POLYNOMIAL = 'ꀁ';

      for(int i = 0; i < buflen; ++i) {
         CRC ^= bufData[i] & 255;

         for(int j = 0; j < 8; ++j) {
            if ((CRC & 1) != 0) {
               CRC >>= 1;
               CRC ^= POLYNOMIAL;
            } else {
               CRC >>= 1;
            }
         }
      }

      return CRC;
   }
}
