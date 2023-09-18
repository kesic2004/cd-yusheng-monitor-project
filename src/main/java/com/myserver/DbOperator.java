package com.myserver;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.model.currentuser.entity.Currentuser;
import com.model.currentuser.service.CurrentuserService;
import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.license.entity.License;
import com.model.license.service.LicenseService;
import com.model.special.entity.Special;
import com.model.special.service.SpecialService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.entity.Tbldata;
import com.model.tbldata.service.TbldataService;
import com.model.tbldevicelog.entity.Tbldevicelog;
import com.model.tbldevicelog.service.TbldevicelogService;
import com.model.user.entity.User;
import com.model.user.entity.UserEx;
import com.model.user.service.UserService;
import com.thirdinf.MyEvent;
import com.tools.XTool;
import com.tools.XTool2;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class DbOperator {
   private static final Logger logger = Logger.getLogger(DbOperator.class);

   static License findLicense(LicenseService licenseService) {
      Integer id = 1;
      License lic = (License)licenseService.getById(id);
      return lic;
   }

   static int queryCountByDate(TbldataService tbldataService, String starttime, String endtime) {
      return tbldataService.queryCountByDateTime(starttime, endtime);
   }

   static Tblclient findClient(TblclientService tblclientService, Integer clientId) {
      Tblclient client = (Tblclient)tblclientService.getById(clientId);
      return client;
   }

   static Device findDevice(DeviceService deviceService, LinkedHashMap<String, Object> map) {
      int address = XTool.byte2Integer((Byte)map.get("address"));
      int province = XTool.byte2Integer((Byte)map.get("province"));
      int city = XTool.byte2Integer((Byte)map.get("city"));
      int station = XTool.byte2Integer((Byte)map.get("station"));
      int machineno = ((Short)map.get("machineno")).intValue();
      Device device = deviceService.findDevice(address, province, city, station, machineno);
      return device;
   }

   static Special findSpecial(SpecialService specialService, LinkedHashMap<String, Object> map) {
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      String stationCode = province.toString() + city.toString() + station.toString();
      QueryWrapper queryWrapper = new QueryWrapper();
      queryWrapper.eq("stationcode", stationCode);
      Special special = (Special)specialService.getOne(queryWrapper);
      return special;
   }

   static UserEx findUserJoinStation(UserService userService, LinkedHashMap<String, Object> map) {
      User user = new User();
      user.setQrcode(map.get("qrcode").toString());
      Integer addresscode = XTool.byte2Integer((Byte)map.get("address"));
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      UserEx userEx = userService.findUserJoinStation(addresscode, province, city, station, user);
      return userEx;
   }

   static boolean saveLoginInfo(TbldevicelogService tbldevicelogService, LinkedHashMap<String, Object> map, int state) {
      Tbldevicelog data = new Tbldevicelog();
      data.setAddresscode(XTool.byte2Integer((Byte)map.get("address")));
      data.setProvincecode(XTool.byte2Integer((Byte)map.get("province")));
      data.setCitycode(XTool.byte2Integer((Byte)map.get("city")));
      data.setStationcode(XTool.byte2Integer((Byte)map.get("station")));
      data.setQrcode((String)map.get("qrcode"));
      data.setRecordtime(new Date());
      data.setState(state);
      data.setMachineno(((Short)map.get("machineno")).intValue());
      tbldevicelogService.saveOrUpdate(data);
      return true;
   }

   static boolean saveCurrentUser(CurrentuserService currentuserService, LinkedHashMap<String, Object> map) {
      Currentuser data = new Currentuser();
      data.setAddress(XTool.byte2Integer((Byte)map.get("address")));
      data.setProvince(XTool.byte2Integer((Byte)map.get("province")));
      data.setCity(XTool.byte2Integer((Byte)map.get("city")));
      data.setStation(XTool.byte2Integer((Byte)map.get("station")));
      data.setQrcode((String)map.get("qrcode"));
      data.setRecordtime(new Date());
      data.setMachineno(((Short)map.get("machineno")).intValue());
      currentuserService.saveOrUpdate(data);
      return true;
   }

   static Currentuser findCurrentuser(CurrentuserService currentuserService, LinkedHashMap<String, Object> map) {
      Integer address = XTool.byte2Integer((Byte)map.get("address"));
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer machineno = ((Short)map.get("machineno")).intValue();
      Currentuser currentuser = currentuserService.findCurrentuser(province, city, station, address, machineno);
      return currentuser;
   }

   static void delCurrentuser(CurrentuserService currentuserService, LinkedHashMap<String, Object> map) {
      Integer address = XTool.byte2Integer((Byte)map.get("address"));
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer machineno = ((Short)map.get("machineno")).intValue();
      currentuserService.delCurrentuser(province, city, station, address, machineno);
   }

   static String getQrcode(LinkedHashMap<String, Object> map) {
      String qrcode = "";
      byte b = (Byte)map.get("card1");
      qrcode = qrcode + XTool2.bcd2Str_2(b);
      b = (Byte)map.get("card2");
      qrcode = qrcode + XTool2.bcd2Str_2(b);
      b = (Byte)map.get("card3");
      qrcode = qrcode + XTool2.bcd2Str_2(b);
      b = (Byte)map.get("card4");
      qrcode = qrcode + XTool2.bcd2Str_2(b);
      Short stamp = (Short)map.get("stamp");
      byte[] byteStamp = XTool.unsignedShortToByte2(stamp);

      for(int i = 0; i < byteStamp.length; ++i) {
         b = byteStamp[i];
         qrcode = qrcode + XTool2.bcd2Str_2(b);
      }

      return qrcode;
   }

   static boolean saveDataToDb(TbldataService tbldataService, LinkedHashMap<String, Object> map, ApplicationContext applicationContext, boolean bSendBroadMessage) {
      Tbldata data = new Tbldata();
      data.setProvincecode(XTool.byte2Integer((Byte)map.get("province")));
      data.setCitycode(XTool.byte2Integer((Byte)map.get("city")));
      data.setStationcode(XTool.byte2Integer((Byte)map.get("station")));
      data.setAddresscode(XTool.byte2Integer((Byte)map.get("address")));
      data.setCommand(XTool.byte2Integer((Byte)map.get("command")));
      data.setNextnumber(XTool.byte2Integer((Byte)map.get("next")));
      data.setCardnum1(XTool.byte2Integer((Byte)map.get("card1")));
      data.setCardnum2(XTool.byte2Integer((Byte)map.get("card2")));
      data.setCardnum3(XTool.byte2Integer((Byte)map.get("card3")));
      data.setCardnum4(XTool.byte2Integer((Byte)map.get("card4")));
      data.setStamp(((Short)map.get("stamp")).intValue());
      data.setEndweight(((Short)map.get("weightend")).intValue());
      data.setGrossweight(((Short)map.get("weightgross")).intValue());
      data.setSettingweight(((Short)map.get("weightsetting")).intValue());
      data.setClientid(((Short)map.get("client")).intValue());
      data.setOperatorid(XTool.byte2Integer((Byte)map.get("executor")));
      data.setGxcode(XTool.byte2Integer((Byte)map.get("gxcode")));
      data.setGxcode2((Byte)map.get("gxcode"));
      data.setFscode(XTool.byte2Integer((Byte)map.get("fscode")));
      data.setFrameid(XTool.byte2Integer((Byte)map.get("forder")));
      int year = XTool2.byteBcd2Int((Byte)map.get("year"));
      year = year + 2000 - 1900;
      int month = XTool2.byteBcd2Int((Byte)map.get("month"));
      if (month >= 1 && month <= 12) {
         --month;
      }

      int day = XTool2.byteBcd2Int((Byte)map.get("day"));
      int hour = XTool2.byteBcd2Int((Byte)map.get("hour"));
      int minute = XTool2.byteBcd2Int((Byte)map.get("minute"));
      int second = XTool2.byteBcd2Int((Byte)map.get("second"));
      if (year >= 0 && year <= 8099 && month >= 0 && month <= 11 && day >= 1 && day <= 31 && hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59 && second >= 0 && second <= 59) {
         data.setCurtime(new Date(year, month, day, hour, minute, second));
      } else {
         logger.info("@@接收下位机时间数据错误：year（0-8099）:" + year + ", month(0-11): " + month + ", day(1-31): " + day + ", hour(0-23): " + hour + ", minute(0-59): " + minute + ", second(0-59): " + second);
         logger.info("@@ 用当前时间替换！！");
         data.setCurtime(new Date());
      }

      data.setUsedtime(((Short)map.get("usedtime")).intValue());
      data.setTicket(((Short)map.get("ticket")).intValue());
      data.setMachineno(((Short)map.get("machineno")).intValue());
      data.setRecordtime(new Date());
      data.setQrcode(map.get("qrcode").toString());
      data.setParam1(map.get("param1").toString());
      data.setParam2(map.get("param2").toString());
      tbldataService.insertData(data);
      logger.info("@@=> [id] = " + data.getId());
      if (bSendBroadMessage) {
         postMessage(applicationContext, data);
      }

      return true;
   }

   static boolean postMessage(ApplicationContext applicationContext, Tbldata data) {
      applicationContext.publishEvent(new MyEvent(applicationContext, data));
      return true;
   }
}
