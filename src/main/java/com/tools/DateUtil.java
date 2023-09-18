package com.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
   private static SimpleDateFormat sf = null;

   public static String getDateToString(long time) {
      Date d = new Date(time);
      sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return sf.format(d);
   }

   public static String getCurrentDate() {
      Date d = new Date();
      sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return sf.format(d);
   }

   public static long getStringToDate(String time) {
      sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = new Date();

      try {
         date = sf.parse(time);
      } catch (ParseException var3) {
         var3.printStackTrace();
      }

      return date.getTime();
   }

   public static String getTimeStamp() {
      String currentDate = getCurrentDate();
      sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = new Date();

      try {
         date = sf.parse(currentDate);
      } catch (ParseException var3) {
         var3.printStackTrace();
      }

      return String.valueOf(date.getTime());
   }

   public static String getTimeStamp2() {
      Date day = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      String sEndDatetime = sdf.format(day);
      return sEndDatetime;
   }

   public static long getStringToDate2(String time) {
      sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Date date = new Date();

      try {
         date = sf.parse(time);
      } catch (ParseException var3) {
         var3.printStackTrace();
      }

      return date.getTime();
   }

   public static long getStringToDate3(String time) {
      sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      Date date = new Date();

      try {
         date = sf.parse(time);
      } catch (ParseException var3) {
         var3.printStackTrace();
      }

      return date.getTime();
   }
}
