package com.thirdinf;

import java.util.LinkedHashMap;

public interface BaseInvokeInf {
   String checkQrAndAddress(String requestUrl, LinkedHashMap<String, Object> map);

   Integer parseData(String jsonString);

   String getParam1();
}
