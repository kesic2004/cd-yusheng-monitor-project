package com.tools;

import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64 {
   public static String Encoder(String plainText) {
      BASE64Encoder encoder = new BASE64Encoder();
      String cipherText = encoder.encode(plainText.getBytes());
      return cipherText;
   }

   public static String Decoder(String cipherText) {
      BASE64Decoder decoder = new BASE64Decoder();
      String plainText = null;

      try {
         plainText = new String(decoder.decodeBuffer(cipherText));
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      return plainText;
   }
}
