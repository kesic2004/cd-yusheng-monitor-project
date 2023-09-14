package com.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XTool2 {
   public static byte[] hexStringToByte(String hex) {
      int len = hex.length() / 2;
      byte[] result = new byte[len];
      char[] achar = hex.toCharArray();

      for(int i = 0; i < len; ++i) {
         int pos = i * 2;
         result[i] = (byte)(toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
      }

      return result;
   }

   private static byte toByte(char c) {
      byte b = (byte)"0123456789ABCDEF".indexOf(c);
      return b;
   }

   public static final String bytesToHexString(byte[] bArray) {
      StringBuffer sb = new StringBuffer(bArray.length);

      for(int i = 0; i < bArray.length; ++i) {
         String sTemp = Integer.toHexString(255 & bArray[i]);
         if (sTemp.length() < 2) {
            sb.append(0);
         }

         sb.append(sTemp.toUpperCase());
      }

      return sb.toString();
   }

   public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
      ByteArrayInputStream in = new ByteArrayInputStream(bytes);
      ObjectInputStream oi = new ObjectInputStream(in);
      Object o = oi.readObject();
      oi.close();
      return o;
   }

   public static final byte[] objectToBytes(Serializable s) throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream ot = new ObjectOutputStream(out);
      ot.writeObject(s);
      ot.flush();
      ot.close();
      return out.toByteArray();
   }

   public static final String objectToHexString(Serializable s) throws IOException {
      return bytesToHexString(objectToBytes(s));
   }

   public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
      return bytesToObject(hexStringToByte(hex));
   }

   public static String bcd2Str(byte[] bytes) {
      StringBuffer temp = new StringBuffer(bytes.length * 2);

      for(int i = 0; i < bytes.length; ++i) {
         temp.append((byte)((bytes[i] & 240) >>> 4));
         temp.append((byte)(bytes[i] & 15));
      }

      return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
   }

   public static String bcd2Str_2(byte bytes) {
      StringBuffer sbu = new StringBuffer();
      byte[] b = new byte[]{(byte)(bytes & 15), (byte)((bytes & 240) >>> 4)};
      sbu.append(b[0]);
      sbu.append(b[1]);
      return sbu.toString();
   }

   public static int byteBcd2Int(byte byteVal) {
      StringBuffer temp = new StringBuffer(2);
      temp.append((byte)((byteVal & 240) >>> 4));
      temp.append((byte)(byteVal & 15));
      String s = temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
      int iVal = Integer.parseInt(s);
      return iVal;
   }

   public static String byteBcd2String(byte byteVal) {
      StringBuffer temp = new StringBuffer(2);
      temp.append((byte)((byteVal & 240) >>> 4));
      temp.append((byte)(byteVal & 15));
      String s = temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
      return s;
   }

   public static byte[] str2Bcd(String asc) {
      int len = asc.length();
      int mod = len % 2;
      if (mod != 0) {
         asc = "0" + asc;
         len = asc.length();
      }

      byte[] abt = new byte[len];
      if (len >= 2) {
         len /= 2;
      }

      byte[] bbt = new byte[len];
      abt = asc.getBytes();

      for(int p = 0; p < asc.length() / 2; ++p) {
         int j;
         if (abt[2 * p] >= 48 && abt[2 * p] <= 57) {
            j = abt[2 * p] - 48;
         } else if (abt[2 * p] >= 97 && abt[2 * p] <= 122) {
            j = abt[2 * p] - 97 + 10;
         } else {
            j = abt[2 * p] - 65 + 10;
         }

         int k;
         if (abt[2 * p + 1] >= 48 && abt[2 * p + 1] <= 57) {
            k = abt[2 * p + 1] - 48;
         } else if (abt[2 * p + 1] >= 97 && abt[2 * p + 1] <= 122) {
            k = abt[2 * p + 1] - 97 + 10;
         } else {
            k = abt[2 * p + 1] - 65 + 10;
         }

         int a = (j << 4) + k;
         byte b = (byte)a;
         bbt[p] = b;
      }

      return bbt;
   }

   public static String BCD2ASC(byte[] bytes) {
      char[] temp = new char[bytes.length * 2];

      for(int i = 0; i < bytes.length; ++i) {
         char val = (char)((bytes[i] & 240) >> 4 & 15);
         temp[i * 2] = (char)(val > '\t' ? val + 65 - 10 : val + 48);
         val = (char)(bytes[i] & 15);
         temp[i * 2 + 1] = (char)(val > '\t' ? val + 65 - 10 : val + 48);
      }

      return new String(temp);
   }

   public static String MD5EncodeToHex(String origin) {
      return bytesToHexString(MD5Encode(origin));
   }

   public static byte[] MD5Encode(String origin) {
      return MD5Encode(origin.getBytes());
   }

   public static byte[] MD5Encode(byte[] bytes) {
      MessageDigest md = null;

      try {
         md = MessageDigest.getInstance("MD5");
         return md.digest(bytes);
      } catch (NoSuchAlgorithmException var3) {
         var3.printStackTrace();
         return new byte[0];
      }
   }
}
