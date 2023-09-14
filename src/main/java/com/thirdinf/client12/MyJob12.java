package com.thirdinf.client12;

import com.model.device.entity.Device;
import com.model.device.service.DeviceService;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.service.TblclientService;
import com.model.tblvalues.entity.Tblvalues;
import com.model.tblvalues.service.TblvaluesService;
import com.myserver.DataPackage;
import com.thirdinf.MyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyJob12 implements MyInf12, Runnable, ApplicationListener<MyEvent> {
   private static final Logger logger = Logger.getLogger(MyJob12.class);
   @Autowired
   TblclientService tblclientService;
   @Autowired
   DeviceService deviceService;
   @Autowired
   TblvaluesService tblvaluesService;
   private static List<DataPackage> lDATA = new ArrayList();

   @PostConstruct
   public void Ini() throws Exception {
      (new Thread(this)).start();
   }

   public static synchronized void addData(DataPackage t) {
      lDATA.add(t);
   }

   public static synchronized DataPackage popData() {
      DataPackage t = null;
      if (lDATA.size() > 0) {
         t = (DataPackage)lDATA.remove(0);
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
            DataPackage data = popData();
            if (data != null) {
               logger.info("@@ 广播处理消息: " + data.toString());
               Device device = this.getDevice(Client_Id, data.getAddresscode(), data.getProvincecode(), data.getCitycode(), data.getStationcode());
               if (device != null && device.getState() != 0) {
                  logger.info("@@ 消息检查: 传送!!=> 君则系统");
                  ArrayList<Integer> chanList = new ArrayList();
                  ArrayList<Float> keyList = new ArrayList();
                  ArrayList<Float> zeroList = new ArrayList();
                  ArrayList<Float> lxList = new ArrayList();
                  this.fillParams(device, chanList, keyList, zeroList, lxList);
                  this.sendData(data, client, device, chanList, keyList, zeroList, lxList);
                  chanList = null;
                  keyList = null;
                  zeroList = null;
                  lxList = null;
               }

               data = null;
            }

            this.mySleep(10);
         }
      }

      logger.info("-> start job!! =>  error!!! stop job!!! => Client_Id: " + Client_Id);
   }

   public Device getDevice(Integer clientid, Integer addresscode, Integer province, Integer city, Integer station) {
      Device device = this.deviceService.findDeviceByClientIdAndAddress(clientid, addresscode, province, city, station);
      return device;
   }

   public void fillParams(Device device, ArrayList<Integer> chanList, ArrayList<Float> keyList, ArrayList<Float> zeroList, ArrayList<Float> lxList) {
      chanList.add(device.getChan1());
      chanList.add(device.getChan2());
      chanList.add(device.getChan3());
      chanList.add(device.getChan4());
      chanList.add(device.getChan5());
      chanList.add(device.getChan6());
      chanList.add(device.getChan7());
      chanList.add(device.getChan8());
      chanList.add(device.getChan9());
      chanList.add(device.getChan10());
      chanList.add(device.getChan11());
      chanList.add(device.getChan12());
      chanList.add(device.getChan13());
      chanList.add(device.getChan14());
      chanList.add(device.getChan15());
      chanList.add(device.getChan16());
      keyList.add(device.getKey1());
      keyList.add(device.getKey2());
      keyList.add(device.getKey3());
      keyList.add(device.getKey4());
      keyList.add(device.getKey5());
      keyList.add(device.getKey6());
      keyList.add(device.getKey7());
      keyList.add(device.getKey8());
      keyList.add(device.getKey9());
      keyList.add(device.getKey10());
      keyList.add(device.getKey11());
      keyList.add(device.getKey12());
      keyList.add(device.getKey13());
      keyList.add(device.getKey14());
      keyList.add(device.getKey15());
      keyList.add(device.getKey16());
      zeroList.add(device.getZero1());
      zeroList.add(device.getZero2());
      zeroList.add(device.getZero3());
      zeroList.add(device.getZero4());
      zeroList.add(device.getZero5());
      zeroList.add(device.getZero6());
      zeroList.add(device.getZero7());
      zeroList.add(device.getZero8());
      zeroList.add(device.getZero9());
      zeroList.add(device.getZero10());
      zeroList.add(device.getZero11());
      zeroList.add(device.getZero12());
      zeroList.add(device.getZero13());
      zeroList.add(device.getZero14());
      zeroList.add(device.getZero15());
      zeroList.add(device.getZero16());
      lxList.add(device.getLx1());
      lxList.add(device.getLx2());
      lxList.add(device.getLx3());
      lxList.add(device.getLx4());
      lxList.add(device.getLx5());
      lxList.add(device.getLx6());
      lxList.add(device.getLx7());
      lxList.add(device.getLx8());
      lxList.add(device.getLx9());
      lxList.add(device.getLx10());
      lxList.add(device.getLx11());
      lxList.add(device.getLx12());
      lxList.add(device.getLx13());
      lxList.add(device.getLx14());
      lxList.add(device.getLx15());
      lxList.add(device.getLx16());
   }

   public boolean sendData(DataPackage d, Tblclient client, Device device, ArrayList<Integer> chanList, ArrayList<Float> keyList, ArrayList<Float> zeroList, ArrayList<Float> lxList) {
      boolean bOk = true;
      ArrayList<Integer> dataList = d.getL();
      int iNum = d.getPoins();
      if (iNum > dataList.size()) {
         iNum = dataList.size();
      }

      String temp = null;
      String press = null;

      for(int i = 0; i < iNum; ++i) {
         Integer c = (Integer)chanList.get(i);
         Float key = (Float)keyList.get(i);
         Float zero = (Float)zeroList.get(i);
         Float lx = (Float)lxList.get(i);
         DecimalFormat df;
         double value;
         String val;
         if (temp == null && c != null && c == 1 && key != null && key > 0.0F && zero != null && lx != null) {
            df = new DecimalFormat("0.0");
            value = (double)(((float)(Integer)dataList.get(i) - zero) / key + lx);
            val = df.format(value);
            logger.info("@@ 通道[" + (i + 1) + "] 温度 => " + val);
            temp = val;
         } else if (press == null && c != null && c == 2 && key != null && key > 0.0F && zero != null && lx != null) {
            df = new DecimalFormat("0.00");
            value = (double)(((float)(Integer)dataList.get(i) - zero) / key + lx);
            val = df.format(value);
            logger.info("@@ 通道[" + (i + 1) + "] 压力 => " + val);
            press = val;
         } else {
            if (c == null || c != 1 && c != 2) {
               logger.info("@@ 通道[" + (i + 1) + "] 设置成未知通道类型 !!！ ");
            }

            if (key == null || key <= 0.0F) {
               logger.info("@@ 通道[" + (i + 1) + "] key值为 null或者 小于等于0 !!！ ");
            }

            if (zero == null) {
               logger.info("@@ 通道[" + (i + 1) + "] zero值为 null !!！ ");
            }

            if (lx == null) {
               logger.info("@@ 通道[" + (i + 1) + "] lx值为 null !!！ ");
            }
         }
      }

      String addressCode = d.getProvincecode().toString() + d.getCitycode().toString() + d.getStationcode().toString();
      Tblvalues tblvalues = this.tblvaluesService.findValues(addressCode);
      if (tblvalues != null) {
         this.tblvaluesService.updateValues(addressCode, temp, press, new Date());
      } else {
         logger.info("@@ 地址码 " + addressCode + "未设置 !!！ => 新增!!");
         Tblvalues val = new Tblvalues();
         val.setAddresscode(addressCode);
         val.setTemperature(temp);
         val.setPressure(press);
         val.setDetail("");
         val.setRecordtime(new Date());
         this.tblvaluesService.saveOrUpdate(val);
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
      DataPackage t = myEvent.getT();
      addData(t);
      myEvent = null;
   }
}
