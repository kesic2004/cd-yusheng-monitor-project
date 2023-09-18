package com.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.log4j.Logger;

public class RandomGUID {
   public String valueBeforeMD5 = "";
   public String valueAfterMD5 = "";
   private static Random myRand;
   private static SecureRandom mySecureRand = new SecureRandom();
   static Logger logger = Logger.getLogger(RandomGUID.class);

   public static String generatorGUID() {
      RandomGUID myGUID = new RandomGUID();
      return myGUID.toString();
   }

   public RandomGUID() {
      this.getRandomGUID(false);
   }

   public RandomGUID(boolean secure) {
      this.getRandomGUID(secure);
   }

   private void getRandomGUID(boolean secure) {
      MessageDigest md5 = null;
      StringBuffer sbValueBeforeMD5 = new StringBuffer();

      try {
         md5 = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException var13) {
         logger.error("Error: " + var13);
      }

      try {
         InetAddress id = InetAddress.getLocalHost();
         long time = System.currentTimeMillis();
         long rand = 0L;
         if (secure) {
            rand = mySecureRand.nextLong();
         } else {
            rand = myRand.nextLong();
         }

         sbValueBeforeMD5.append(id.toString());
         sbValueBeforeMD5.append(":");
         sbValueBeforeMD5.append(Long.toString(time));
         sbValueBeforeMD5.append(":");
         sbValueBeforeMD5.append(Long.toString(rand));
         this.valueBeforeMD5 = sbValueBeforeMD5.toString();
         md5.update(this.valueBeforeMD5.getBytes());
         byte[] array = md5.digest();
         StringBuffer sb = new StringBuffer();

         for(int j = 0; j < array.length; ++j) {
            int b = array[j] & 255;
            if (b < 16) {
               sb.append('0');
            }

            sb.append(Integer.toHexString(b));
         }

         this.valueAfterMD5 = sb.toString();
      } catch (UnknownHostException var14) {
         logger.error("Error:" + var14);
      }

   }

   public String toString() {
      String raw = this.valueAfterMD5.toUpperCase();
      StringBuffer sb = new StringBuffer();
      sb.append(raw.substring(0, 8));
      sb.append(raw.substring(8, 12));
      sb.append(raw.substring(12, 16));
      sb.append(raw.substring(16, 20));
      sb.append(raw.substring(20));
      return sb.toString();
   }

   static {
      long secureInitializer = mySecureRand.nextLong();
      myRand = new Random(secureInitializer);
   }
}
