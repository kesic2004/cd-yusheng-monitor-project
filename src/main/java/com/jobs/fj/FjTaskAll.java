package com.jobs.fj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.fj.entity.FjBottle;
import com.model.fj.entity.FjBottleBak;
import com.model.fj.entity.FjDw;
import com.model.fj.service.FjBottleBakService;
import com.model.fj.service.FjBottleService;
import com.model.fj.service.FjDwService;
import com.model.fj.service.FjLogService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FjTaskAll {
   private static final Logger logger = Logger.getLogger(FjTaskAll.class);
   @Autowired
   private FjBottleBakService fjBottleBakService;
   @Autowired
   private FjBottleService fjBttleService;
   @Autowired
   FjDwService fjDwService;
   @Autowired
   FjLogService fjLogService;
   private boolean bDownOK = false;
   private boolean plus_onesecond = false;

   void execute() {
      this.run("@@定时全体气瓶档案下载");
   }

   synchronized void run(String tag) {
      logger.info("-> All Synchronization task start.............................");
      logger.info("-> 加载单位列表.............................");
      List dwList = this.fjDwService.list();

      try {
         this.fjBottleBakService.deleteBottleBak();
      } catch (Exception var5) {
         var5.printStackTrace();
         return;
      }

      boolean bAllOk = true;

      for(int i = 0; i < dwList.size(); ++i) {
         this.bDownOK = false;
         logger.info("-> <<" + tag + ">> start.............................");
         this.fjLogService.newLog(tag, "全体数据更新 >> 开始 :" + ((FjDw)dwList.get(i)).getStationname());
         this.plus_onesecond = false;
         if (!this.downAllData((FjDw)dwList.get(i))) {
            this.fjLogService.newLog(tag, "全体数据更新出错，更新终止 >> error :" + ((FjDw)dwList.get(i)).getStationname());
            bAllOk = false;
            break;
         }

         logger.info("-> <<" + tag + ">> end!!.............................");
         this.fjLogService.newLog(tag, "全体数据更新 >> 结束 :" + ((FjDw)dwList.get(i)).getStationname());
         if (i == dwList.size() - 1) {
            break;
         }

         this.mySleep(3000);
      }

      logger.info("-> All Synchronization task finished.............................");
      if (bAllOk) {
         if (this.data_Transfer()) {
            logger.info("-> 全体数据更新 ok !!");
            this.fjLogService.newLog(tag, "全体数据更新 ok !!");
         } else {
            logger.info("-> 全体数据更新 failed !!");
            this.fjLogService.newLog(tag, "全体数据更新 failed !!");
         }
      }

   }

   private boolean data_Transfer() {
      boolean bOK = true;

      try {
         this.fjBttleService.deleteBottle();
         List<FjBottleBak> l = this.fjBottleBakService.list();

         for(int i = 0; i < l.size(); ++i) {
            FjBottleBak fjBottleBak = (FjBottleBak)l.get(i);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(fjBottleBak);
            FjBottle fjBottle = (FjBottle)objectMapper.readValue(json, FjBottle.class);
            this.fjBttleService.saveOrUpdate(fjBottle);
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         bOK = false;
      }

      return bOK;
   }

   public boolean downAllData(FjDw fjDw) {
      logger.info("-> **down all Data ...... ");

      while(true) {
         boolean bOK = this.fetchBottleList(fjDw);
         if (!bOK) {
            return this.bDownOK;
         }

         this.mySleep(3000);
      }
   }

   void mySleep(int millis) {
      try {
         Thread.sleep((long)millis);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

   }

   boolean fetchBottleList(FjDw fjDw) {
      boolean bOK = false;

      try {
         logger.info("-> 下载气瓶档案.............................");
         this.fjLogService.newLog("更新下载", "下载气瓶档案 >> 开始");
         String dt_MaxUpdateDate = this.fjBottleBakService.getMaxUpdateDateByDid(fjDw.getId());
         String MaxInternalTime;
         if (dt_MaxUpdateDate == null) {
            MaxInternalTime = "";
         } else if (this.plus_onesecond) {
            MaxInternalTime = this.addDateOneSecond(dt_MaxUpdateDate);
         } else {
            MaxInternalTime = dt_MaxUpdateDate;
         }

         String fetchUrl = fjDw.getPlatformurl();
         fetchUrl = fetchUrl + "?lastDate=" + MaxInternalTime + "&token=" + fjDw.getToken();
         logger.info("-> fetchUrl: " + fetchUrl);
         RestTemplate rest = new RestTemplate();
         ObjectMapper objectMapper = new ObjectMapper();
         String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
         logger.info("-> recv [fetchBottleList] response  data: " + jsonString);
         if (jsonString != null && !jsonString.equalsIgnoreCase("")) {
            BottleList bottleList = (BottleList)objectMapper.readValue(jsonString, BottleList.class);
            if (bottleList != null) {
               List<Items> l = bottleList.getItems();
               if (l.size() == 0) {
                  logger.info("本轮下载结束!!");
                  this.bDownOK = true;
                  this.fjLogService.newLog("更新下载", "下载气瓶档案 >> 结束");
               } else {
                  for(int i = 0; i < l.size(); ++i) {
                     Items item = (Items)l.get(i);
                     FjBottleBak fjBottleBak = new FjBottleBak();
                     fjBottleBak.setBarcode(item.getBarcode());
                     fjBottleBak.setPrintno(item.getPrintno());
                     fjBottleBak.setStyle(item.getStyle());
                     fjBottleBak.setValve(item.getValve());
                     fjBottleBak.setProductdate(item.getProductDate());
                     fjBottleBak.setDiscarddate(item.getDiscardDate());
                     fjBottleBak.setCheckdate(item.getCheckDate());
                     fjBottleBak.setNextcheckdate(item.getNextCheckDate());
                     fjBottleBak.setUpdatedate(item.getUpdateDate());
                     fjBottleBak.setState(item.getState());
                     fjBottleBak.setSystemid(item.getId());
                     fjBottleBak.setFactoryid(item.getFactory().getId());
                     fjBottleBak.setFactoryname(item.getFactory().getName());
                     fjBottleBak.setDid(fjDw.getId());
                     this.fjBottleBakService.saveOrUpdate(fjBottleBak);
                  }

                  if (l.size() < bottleList.getPlanCount()) {
                     logger.info("小于等于计划数!!");
                     this.plus_onesecond = true;
                  }

                  bOK = true;
               }
            } else {
               logger.info("气瓶档案为空, 本轮下载结束!!");
               this.bDownOK = true;
               this.fjLogService.newLog("更新下载", "下载气瓶档案 >> 结束");
            }
         } else {
            logger.info("接口返回为空串, 本轮下载结束!!");
            this.bDownOK = true;
            this.fjLogService.newLog("更新下载", "下载气瓶档案 >> 结束");
         }
      } catch (Exception var14) {
         var14.printStackTrace();
         this.fjLogService.newLog("更新下载", "下载气瓶档案 >> error");
      }

      return bOK;
   }

   String addDateOneSecond(String strDate) {
      String dstr = "";
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
      Date date = null;

      try {
         date = sdf.parse(strDate);
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.add(13, 1);
         Date date2 = cal.getTime();
         SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
         dstr = sdf2.format(date2);
      } catch (ParseException var8) {
         var8.printStackTrace();
      }

      return dstr;
   }
}
