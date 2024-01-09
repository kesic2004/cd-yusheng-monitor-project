package com.mytest;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class Mytest {
   void test() {
      String jsonString = "";
      String requestUrl = "http://127.0.0.1:8080/test";

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(5000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         System.out.println("-> start read!!");
         jsonString = rest.getForObject(requestUrl, String.class, new Object[0]);
         System.out.println("-> recv check result: " + jsonString);
      } catch (Exception var5) {
         System.out.println("-> read timeout!!");
      }

   }
}
