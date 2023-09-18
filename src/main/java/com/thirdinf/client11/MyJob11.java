package com.thirdinf.client11;

import cn.hutool.core.util.XmlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.entity.Tbldata;
import com.thirdinf.MyEvent;
import com.tools.XTool;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyJob11 implements MyInf11, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob11.class);
   @Autowired
   TblclientService tblclientService;
   @Autowired
   DeviceService deviceService;
   private static List<Tbldata> lDATA = new ArrayList();

   @PostConstruct
   public void Ini() throws Exception {
      (new Thread(this)).start();
   }

   public static synchronized void addData(Tbldata t) {
      lDATA.add(t);
   }

   public static synchronized Tbldata popData() {
      Tbldata t = null;
      if (lDATA.size() > 0) {
         t = (Tbldata)lDATA.remove(0);
      }

      return t;
   }

   public void run() {
      Tblclient client = (Tblclient)this.tblclientService.getById(Client_Id);
      if (client != null && client.getState() != 0) {
         logger.info("-> start job ok !! => " + client.getId());
         logger.info("-------------------------------------------------");
         logger.info(client.toString());
         logger.info("-------------------------------------------------");

         while(true) {
            Tbldata d = popData();
            if (d != null) {
               logger.info("@@ 广播处理消息: " + d.toString());
               boolean bOk = this.checkClient(Client_Id, d.getAddresscode(), d.getProvincecode(), d.getCitycode(), d.getStationcode());
               if (bOk) {
                  this.sendData(d, client);
               }
            }

            this.mySleep(100);
         }
      }

      logger.info("-> start job!! =>  error!!! stop job!!! => Client_Id: " + Client_Id);
   }

   public boolean checkClient(Integer clientid, Integer addresscode, Integer province, Integer city, Integer station) {
      boolean bOk = false;
      Device device = this.deviceService.findDeviceByClientIdAndAddress(clientid, addresscode, province, city, station);
      if (device == null) {
         logger.info("@@ 消息检查: 不传送 !!");
      } else {
         logger.info("@@ 消息检查: 传送!!");
         bOk = true;
      }

      return bOk;
   }

   public String getFill_Typename(byte b) {
      int iType = this.getFill_Type(b);
      if (iType == 0) {
         return "LPG-05";
      } else if (iType == 1) {
         return "LPG-15";
      } else {
         return iType == 2 ? "LPG-50" : "";
      }
   }

   public int getFill_Type(byte b) {
      byte ret4 = XTool.byteToBit(b, 4);
      byte ret5 = XTool.byteToBit(b, 5);
      byte ret6 = XTool.byteToBit(b, 6);
      int i4 = XTool.byte2Integer(ret4);
      int i5 = XTool.byte2Integer(ret5);
      int i6 = XTool.byte2Integer(ret6);
      int iType = 1 * i4 + 2 * i5 + 4 * i6;
      return iType;
   }

   public Integer getFill_Way(byte b) {
      byte ret7 = XTool.byteToBit(b, 7);
      int i = XTool.byte2Integer(ret7);
      if (i == 0) {
         return 85;
      } else {
         return i == 1 ? 170 : 0;
      }
   }

   public boolean sendData(Tbldata d, Tblclient client) {
      boolean bOk = true;
      MyRequestData11 requestData = new MyRequestData11();
      requestData.setStation_num(d.getProvincecode().toString() + d.getCitycode().toString() + d.getStationcode().toString());
      Integer address = d.getAddresscode() / 2;
      requestData.setNumber(address.toString());
      requestData.setQrcode(d.getQrcode());
      this.getFill_Way(d.getGxcode2());
      requestData.setPz(d.getGrossweight().floatValue());
      requestData.setZz(d.getEndweight().floatValue());
      requestData.setSz(d.getSettingweight().floatValue());
      Integer jz = d.getEndweight() - d.getGrossweight();
      requestData.setJz(jz.floatValue());
      requestData.setPeop_num(d.getOperatorid().toString());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String sStartDatetime = sdf.format(new Date());
      requestData.setEnd_time(sStartDatetime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(13, -1 * d.getUsedtime());
      Date endDate = calendar.getTime();
      String sEndDatetime = sdf.format(endDate);
      requestData.setStart_time(sEndDatetime);
      HttpHeaders headers = new HttpHeaders();
      MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
      headers.setContentType(type);
      StringBuffer soapRequestData = new StringBuffer("");
      soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">");
      soapRequestData.append("<soapenv:Header/>");
      soapRequestData.append("<soapenv:Body>");
      soapRequestData.append("<tem:UpdateGasInspection>");
      soapRequestData.append("<tem:KSSJ>");
      soapRequestData.append(requestData.getStart_time());
      soapRequestData.append("</tem:KSSJ>");
      soapRequestData.append("<tem:JSSJ>");
      soapRequestData.append(requestData.getEnd_time());
      soapRequestData.append("</tem:JSSJ>");
      soapRequestData.append("<tem:BQBH>");
      soapRequestData.append(requestData.getQrcode());
      soapRequestData.append("</tem:BQBH>");
      soapRequestData.append("<tem:ZDBH>");
      soapRequestData.append(requestData.getStation_num());
      soapRequestData.append("</tem:ZDBH>");
      soapRequestData.append("<tem:CZG>");
      soapRequestData.append(requestData.getPeop_num());
      soapRequestData.append("</tem:CZG>");
      soapRequestData.append("<tem:CZDW>");
      soapRequestData.append(requestData.getDw());
      soapRequestData.append("</tem:CZDW>");
      soapRequestData.append("<tem:QH>");
      soapRequestData.append(requestData.getNumber());
      soapRequestData.append("</tem:QH>");
      soapRequestData.append("<tem:SDJZ>");
      soapRequestData.append(requestData.getSz());
      soapRequestData.append("</tem:SDJZ>");
      soapRequestData.append("<tem:SJJZ>");
      soapRequestData.append(requestData.getJz());
      soapRequestData.append("</tem:SJJZ>");
      soapRequestData.append("<tem:PIZ>");
      soapRequestData.append(requestData.getPz());
      soapRequestData.append("</tem:PIZ>");
      soapRequestData.append("<tem:WCZL>");
      soapRequestData.append(requestData.getZz());
      soapRequestData.append("</tem:WCZL>");
      soapRequestData.append("<tem:BJ>");
      soapRequestData.append(requestData.getBJ());
      soapRequestData.append("</tem:BJ>");
      soapRequestData.append("<tem:FHGD>");
      soapRequestData.append(requestData.getFHGD());
      soapRequestData.append("</tem:FHGD>");
      soapRequestData.append("<tem:YS>");
      soapRequestData.append(requestData.getYS());
      soapRequestData.append("</tem:YS>");
      soapRequestData.append("<tem:WG>");
      soapRequestData.append(requestData.getWG());
      soapRequestData.append("</tem:WG>");
      soapRequestData.append("<tem:YY>");
      soapRequestData.append(requestData.getYY());
      soapRequestData.append("</tem:YY>");
      soapRequestData.append("<tem:CZKHZH>");
      soapRequestData.append(requestData.getCZKHZH());
      soapRequestData.append("</tem:CZKHZH>");
      soapRequestData.append("<tem:AQFJ>");
      soapRequestData.append(requestData.getAQFJ());
      soapRequestData.append("</tem:AQFJ>");
      soapRequestData.append("<tem:XL>");
      soapRequestData.append(requestData.getXL());
      soapRequestData.append("</tem:XL>");
      soapRequestData.append("<tem:WGJC>");
      soapRequestData.append(requestData.getWGJC());
      soapRequestData.append("</tem:WGJC>");
      soapRequestData.append("<tem:PTWD>");
      soapRequestData.append(requestData.getPTWD());
      soapRequestData.append("</tem:PTWD>");
      soapRequestData.append("<tem:FJBQ>");
      soapRequestData.append(requestData.getFJBQ());
      soapRequestData.append("</tem:FJBQ>");
      soapRequestData.append("<tem:BZ>");
      soapRequestData.append(requestData.getBZ());
      soapRequestData.append("</tem:BZ>");
      soapRequestData.append("</tem:UpdateGasInspection>");
      soapRequestData.append("</soapenv:Body>");
      soapRequestData.append("</soapenv:Envelope>");
      logger.info("@@ Resquest Soap Content =>: " + soapRequestData.toString());
      HttpEntity<String> request = new HttpEntity(soapRequestData + "", headers);
      String resultStr = "";
      String str = "";
      new HashMap();

      try {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(5000);
         httpRequestFactory.setConnectTimeout(5000);
         httpRequestFactory.setReadTimeout(10000);
         RestTemplate rest = new RestTemplate(httpRequestFactory);
         resultStr = (String)rest.postForObject(client.getPosturl(), request, String.class, new Object[0]);
         logger.info("-----------Response content-----------: " + resultStr);
         String responseXml = XmlUtil.unescape(resultStr);
         Map<String, Object> resultMap = XmlUtil.xmlToMap(responseXml);
         logger.info("-----------Response content-----------: " + resultMap);
         Object m1 = (Map)resultMap.get("soap:Body");
         if (m1 != null) {
            Object m2 = ((Map)m1).get("UpdateGasInspectionResponse");
            if (m2 != null) {
               Object m3 = ((Map)m2).get("UpdateGasInspectionResult");
               if (m3 != null) {
                  str = m3.toString();
               }
            }
         }

         ObjectMapper objectMapper = new ObjectMapper();
         MyCheckResponseData11[] myResponseData = (MyCheckResponseData11[])objectMapper.readValue(str, MyCheckResponseData11[].class);
         logger.info("-> parse check result data: " + myResponseData.toString());
         if (myResponseData.length == 1 && myResponseData[0].getCode().equalsIgnoreCase("1")) {
            logger.info("@@罐装数据发送成功！！=> UpdateGasInspectionResult: " + str);
         } else {
            logger.info("@@罐装数据发送失败！！=> UpdateGasInspectionResult: " + str);
         }
      } catch (Exception var27) {
         logger.info(var27.getMessage());
      }

      return bOk;
   }

   void mySleep(int millis) {
      try {
         Thread.sleep((long)millis);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

   }

   public void onApplicationEvent(MyEvent myEvent) {
      logger.info("Client_Id = " + Client_Id + "-* 收到广播消息事件");
      Tbldata t = myEvent.getT();
      addData(t);
   }
}
