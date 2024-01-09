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
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class DbOperator {
   private static final Logger logger = Logger.getLogger(DbOperator.class);

   static License findLicense(LicenseService licenseService) {
      Integer id = 1;
      License lic = (License) licenseService.getById(id);
      return lic;
   }

   static int queryCountByDate(TbldataService tbldataService, String starttime, String endtime) {
      return tbldataService.queryCountByDateTime(starttime, endtime);
   }

   static Tblclient findClient(TblclientService tblclientService, Integer clientId) {
      Tblclient client = (Tblclient) tblclientService.getById(clientId);
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
      return specialService.getOne(queryWrapper);
   }

   static UserEx findUserJoinStation(UserService userService, LinkedHashMap<String, Object> map) {
      User user = new User();
      user.setQrcode(map.get("qrcode").toString());
      Integer addresscode = XTool.byte2Integer((Byte)map.get("address"));
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      return userService.findUserJoinStation(addresscode, province, city, station, user);
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
      data.setProvince(XTool.byte2Integer((Byte)map.get("province")));
      data.setCity(XTool.byte2Integer((Byte)map.get("city")));
      data.setStation(XTool.byte2Integer((Byte)map.get("station")));
      data.setAddress(XTool.byte2Integer((Byte)map.get("address")));
      data.setQrcode((String)map.get("qrcode"));
      data.setRecordtime(new Date());
      data.setMachineno(((Short)map.get("machineno")).intValue());
      currentuserService.saveOrUpdate(data);
      return true;
   }

   static Currentuser findCurrentuser(CurrentuserService currentuserService, LinkedHashMap<String, Object> map) {
      Integer province = XTool.byte2Integer((Byte)map.get("province"));
      Integer city = XTool.byte2Integer((Byte)map.get("city"));
      Integer station = XTool.byte2Integer((Byte)map.get("station"));
      Integer address = XTool.byte2Integer((Byte)map.get("address"));
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

    static void saveDataToDb(TbldataService tbldataService, byte[] array) {
        Tbldata data = new Tbldata();
        data.setProvincecode(array[4] & 0xFF);     // (省份编号)
        data.setCitycode(array[5] & 0xFF);         // (地市州编号)
        data.setStationcode(array[6] & 0xFF);      // (气站编号)
        data.setAddresscode(array[7] & 0xFF);      // (地址码)
        data.setCommand(array[8] & 0xFF);          // (命令字。注：命令字与命令码是不同的)
        data.setNextnumber(array[9] & 0xFF);       // (后续数)
        data.setCardnum1(array[10] & 0xFF);        // (卡号1和卡号2)
        data.setCardnum2(array[11] & 0xFF);        // (卡号3和卡号4)
        data.setCardnum3(array[12] & 0xFF);        // (卡号5和卡号6)
        data.setCardnum4(array[13] & 0xFF);        //(卡号7和卡号8)
        data.setStamp((array[14] & 0xFF) | ((array[15] & 0xFF) << 8));                 // (钢印号)
        data.setEndweight((array[16] & 0xFF) | ((array[17] & 0xFF) << 8));             // (结束重量)
        data.setGrossweight((array[18] & 0xFF) | ((array[19] & 0xFF) << 8));           // (皮重)
        data.setSettingweight((array[20] & 0xFF) | ((array[21] & 0xFF) << 8));         // (设重)
        data.setClientid((array[22] & 0xFF) | ((array[23] & 0xFF) << 8));              // (客户号)
        data.setOperatorid(array[24] & 0xFF);      // (操作员)
        data.setGxcode(array[25] & 0xFF);          // (罐型码)
        data.setFscode(array[26] & 0xFF);          // (方式码)
        data.setFrameid(array[27] & 0xFF);         // (当前帧序号)★★★
        int year = XTool2.byteBcd2Int(array[28]) + 100;                // (年)
        int month = XTool2.byteBcd2Int(array[29]);                     // (月)
        if (month >= 1 && month <= 12) {
            --month;
        }
        int day    = XTool2.byteBcd2Int(array[30]);                    // (日)
        int hour   = XTool2.byteBcd2Int(array[31]);                    // (时)
        int minute = XTool2.byteBcd2Int(array[32]);                    // (分)
        int second = XTool2.byteBcd2Int(array[33]);                    // (秒)
        data.setUsedtime((array[34] & 0xFF) | ((array[35] & 0xFF) << 8));               // (用时)
        /*
         * 由于部分灌装秤的时间误差超过1个自然日，2023年10月13日贺总经理决凡时灌装完成时间与服务器的时间的差超过半小时的数据，
         * 上传时间一律取服务器时间
         * 检查灌装完成时间是否在允许的范围内，同时设置数据的记录时间
         */
        checkUploadTime(data, year, month, day, hour, minute, second);
        data.setTicket((array[36] & 0xFF) | ((array[37] & 0xFF) << 8));               // (票号)
        data.setMachineno((array[38] & 0xFF) | ((array[39] & 0xFF) << 8));            // (机器编号)
        if (tbldataService.dataIsNotExists(data.getProvincecode(), data.getCitycode(), data.getStationcode(), data.getMachineno(), data.getCurtime(), data.getRemoteTime(), data.getFrameid())) {
            tbldataService.insertData(data);
        }
    }
    static boolean saveDataToDb(TbldataService tbldataService, LinkedHashMap<String, Object> map, ApplicationContext applicationContext, boolean bSendBroadMessage) {
        Tbldata data = new Tbldata();
        data.setProvincecode(XTool.byte2Integer((Byte) map.get("province"))); // (省份编号)
        data.setCitycode(XTool.byte2Integer((Byte) map.get("city")));         // (地市州编号)
        data.setStationcode(XTool.byte2Integer((Byte) map.get("station")));   // (气站编号)
        data.setAddresscode(XTool.byte2Integer((Byte) map.get("address")));   // (地址码)
        data.setCommand(XTool.byte2Integer((Byte) map.get("command")));       // (命令字。注：命令字与命令码是不同的)
        data.setNextnumber(XTool.byte2Integer((Byte) map.get("next")));       // (后续数)
        data.setCardnum1(XTool.byte2Integer((Byte) map.get("card1")));        // (卡号1和卡号2)
        data.setCardnum2(XTool.byte2Integer((Byte) map.get("card2")));        // (卡号3和卡号4)
        data.setCardnum3(XTool.byte2Integer((Byte) map.get("card3")));        // (卡号5和卡号6)
        data.setCardnum4(XTool.byte2Integer((Byte) map.get("card4")));        //(卡号7和卡号8)
        data.setStamp(((Short) map.get("stamp")).intValue());                 // (钢印号)
        data.setEndweight(((Short) map.get("weightend")).intValue());         // (结束重量)
        data.setGrossweight(((Short) map.get("weightgross")).intValue());     // (皮重)
        data.setSettingweight(((Short) map.get("weightsetting")).intValue()); // (设重)
        data.setClientid(((Short) map.get("client")).intValue());             // (客户号)
        data.setOperatorid(XTool.byte2Integer((Byte) map.get("executor")));   // (操作员)
        data.setGxcode(XTool.byte2Integer((Byte) map.get("gxcode")));         // (罐型码)
        data.setGxcode2((Byte) map.get("gxcode"));                            // (罐型码)
        data.setFscode(XTool.byte2Integer((Byte) map.get("fscode")));         // (方式码)
        data.setFrameid(XTool.byte2Integer((Byte) map.get("forder")));        // (当前帧序号)★★★
        int year = XTool2.byteBcd2Int((Byte) map.get("year"));                // (年)
        year = year + 100;/*2000 - 1900;*/
        int month = XTool2.byteBcd2Int((Byte) map.get("month"));              // (月)
        if (month >= 1 && month <= 12) {
            --month;
        }

        int day    = XTool2.byteBcd2Int((Byte) map.get("day"));               // (日)
        int hour   = XTool2.byteBcd2Int((Byte) map.get("hour"));              // (时)
        int minute = XTool2.byteBcd2Int((Byte) map.get("minute"));            // (分)
        int second = XTool2.byteBcd2Int((Byte) map.get("second"));            // (秒)

      data.setUsedtime(((Short)map.get("usedtime")).intValue());
        checkUploadTime(data, year, month, day, hour, minute, second);
      data.setTicket(((Short)map.get("ticket")).intValue());
      data.setMachineno(((Short)map.get("machineno")).intValue());
      data.setQrcode(map.get("qrcode").toString());
      data.setParam1(map.get("param1").toString());
      data.setParam2(map.get("param2").toString());
        if (tbldataService.dataIsNotExists(data.getProvincecode(), data.getCitycode(), data.getStationcode(), data.getMachineno(), data.getCurtime(), data.getRemoteTime(), data.getFrameid())) {
            tbldataService.insertData(data);
        }
        logger.info("@@=> [id] = " + data.getId());
        if (bSendBroadMessage) {
            postMessage(applicationContext, data);
        }
        return true;
    }

    private static void checkUploadTime(Tbldata data, int year, int month, int day, int hour, int minute, int second) {
        final long usedtimeMillis    = data.getUsedtime().longValue() * 1_000L; // 用时毫秒
        final long currentTimeMillis = System.currentTimeMillis(); // 服务器时间
        if (checkAndSetRemoteTime(data, year, month, day, hour, minute, second)) {
            Date localUploadTime = new Date(year, month, day, hour, minute, second);
            if (Math.abs(currentTimeMillis - (localUploadTime.getTime() + usedtimeMillis)) > 1_800_000L) {
                data.setCurtime(new Date(currentTimeMillis - usedtimeMillis));
                data.setRecordtime(new Date(currentTimeMillis));                                       // 持久化时的时间
            } else {
                data.setCurtime(new Date(localUploadTime.getTime() - usedtimeMillis + (currentTimeMillis % 1_000L)));
                data.setRecordtime(new Date(localUploadTime.getTime() + (currentTimeMillis % 1_000L)));
            }
        } else {
            logger.info("@@接收下位机时间数据错误：year（0-8099）:" + year + ", month(0-11): " + month + ", day(1-31): " + day + ", hour(0-23): " + hour + ", minute(0-59): " + minute + ", second(0-59): " + second);
            logger.info("@@ 用当前时间替换！！");
            data.setCurtime(new Date(currentTimeMillis - usedtimeMillis));
            data.setRecordtime(new Date(currentTimeMillis));                                       // 持久化时的时间
        }
    }
    private static final boolean checkAndSetRemoteTime(final Tbldata data, final int year, final int month, final int day, final int hour, final int minute, final int second) {
        StringBuilder builder = new StringBuilder(14);
        String string = null;
        boolean success = true;
        builder.append(Integer.toString(year + 1900));
        if (month >= 0 && month <= 11) {
            string = Integer.toString(month + 1);
            if (string.length() == 1) {
                builder.append("0");
            }
            builder.append(string);
        } else {
            builder.append("XX");
            success = false;
        }
        if (day >= 1 && day <= 31) {
            string = Integer.toString(day);
            if (string.length() == 1) {
                builder.append("0");
            }
            builder.append(string);
        } else {
            builder.append("XX");
            success = false;
        }
        if (hour >= 0 && hour <= 23) {
            string = Integer.toString(hour);
            if (string.length() == 1) {
                builder.append("0");
            }
            builder.append(string);
        } else {
            builder.append("XX");
            success = false;
        }
        if (minute >= 0 && minute <= 59) {
            string = Integer.toString(minute);
            if (string.length() == 1) {
                builder.append("0");
            }
            builder.append(string);
        } else {
            builder.append("XX");
            success = false;
        }
        if (second >= 0 && second <= 59) {
            string = Integer.toString(second);
            if (string.length() == 1) {
                builder.append("0");
            }
            builder.append(string);
        } else {
            builder.append("XX");
            success = false;
        }
        data.setRemoteTime(builder.toString());
        return success;
    }
   static boolean postMessage(ApplicationContext applicationContext, Tbldata data) {
      applicationContext.publishEvent(new MyEvent(applicationContext, data));
      return true;
   }
}
