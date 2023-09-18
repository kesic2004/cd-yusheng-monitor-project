package com.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtil {
   public static String urlEncodeURL(String url) {
      try {
         String result = URLEncoder.encode(url, "UTF-8");
         result = result.replaceAll("%3A", ":").replaceAll("%2F", "/").replaceAll("\\+", "%20");
         return result;
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         return null;
      }
   }
}
