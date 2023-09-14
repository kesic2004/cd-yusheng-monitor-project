package com.myserver;

import com.thirdinf.MyEvent;
import com.tools.XTool;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class DbOperator {
   private static final Logger logger = Logger.getLogger(DbOperator.class);

   static boolean saveDataToClient(LinkedHashMap<String, Object> map, ApplicationContext applicationContext) {
      DataPackage data = new DataPackage();
      data.setProvincecode(XTool.byte2Integer((Byte)map.get("province")));
      data.setCitycode(XTool.byte2Integer((Byte)map.get("city")));
      data.setStationcode(XTool.byte2Integer((Byte)map.get("station")));
      data.setAddresscode(XTool.byte2Integer((Byte)map.get("address")));
      data.setMachineno(((Short)map.get("machineno")).intValue());
      data.setPoins(XTool.byte2Integer((Byte)map.get("points")));
      ArrayList<Integer> l = new ArrayList();
      String dataTag = "data";

      for(int i = 0; i < 16; ++i) {
         int intVal = XTool.getUnsignedByte((Short)map.get(dataTag + i));
         l.add(intVal);
      }

      data.setL(l);
      postMessage(applicationContext, data);
      return true;
   }

   static boolean postMessage(ApplicationContext applicationContext, DataPackage data) {
      applicationContext.publishEvent(new MyEvent(applicationContext, data));
      return true;
   }
}
