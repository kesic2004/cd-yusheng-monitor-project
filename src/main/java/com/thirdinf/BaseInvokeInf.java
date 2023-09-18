package com.thirdinf;

import java.util.LinkedHashMap;

public interface BaseInvokeInf {
   int ConnectionRequestTimeout = 5000;
   int ConnectTimeout = 5000;
   int ReadTimeout = 10000;

   String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map);

   Integer parseData(String jsonString);

   String getParam1();

   String getParam2();
}
