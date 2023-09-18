package com.jobs.fj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.fj.entity.FjBottle;
import com.model.fj.entity.FjDw;
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
public class FjTaskPartial {
   private static final Logger logger = Logger.getLogger(FjTaskPartial.class);
   @Autowired
   private FjBottleService fjBottleService;
   @Autowired
   FjDwService fjDwService;
   @Autowired
   FjLogService fjLogService;
   private boolean bDownOK = false;
   private boolean plus_onesecond = false;

   void execute() {
      this.run("@@气瓶档案增量下载");
   }

   synchronized void run(String tag) {
      logger.info("-> 增量下载 start.............................");
      logger.info("-> 增量下载 加载单位列表.............................");
      List<FjDw> dwList = this.fjDwService.list();

      for(int i = 0; i < dwList.size(); ++i) {
         this.bDownOK = false;
         logger.info("-> <<" + tag + ">> start.............................");
         this.fjLogService.newLog(tag, "数据增量更新 >> 开始 :" + ((FjDw)dwList.get(i)).getStationname());
         this.plus_onesecond = false;
         if (!this.downPartialData((FjDw)dwList.get(i))) {
            this.fjLogService.newLog(tag, "数据增量更新出错，更新终止 >> error :" + ((FjDw)dwList.get(i)).getStationname());
            break;
         }

         logger.info("-> <<" + tag + ">> end!!.............................");
         this.fjLogService.newLog(tag, "数据增量更新 >> 结束 :" + ((FjDw)dwList.get(i)).getStationname());
         this.mySleep(1000);
      }

      logger.info("-> 增量下载 finished.............................");
   }

   public boolean downPartialData(FjDw fjDw) {
      logger.info("-> **down partial Data ...... ");

      while(true) {
         boolean bOK = this.fetchPartialBottleList(fjDw);
         if (!bOK) {
            return this.bDownOK;
         }

         this.mySleep(1000);
      }
   }

   boolean fetchPartialBottleList(FjDw fjDw) {
      boolean bOK = false;

      try {
         logger.info("-> 增量下载气瓶档案.............................");
         this.fjLogService.newLog("增量更新下载", "增量下载气瓶档案 >> 开始");
         String dt_MaxUpdateDate = this.fjBottleService.getMaxUpdateDateByDid(fjDw.getId());
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
                  logger.info("增量下载结束!!");
                  this.bDownOK = true;
                  this.fjLogService.newLog("增量更新下载", "增量下载气瓶档案 >> 结束");
               } else {
                  for(int i = 0; i < l.size(); ++i) {
                     Items item = (Items)l.get(i);
                     FjBottle bottle = this.fjBottleService.findBottle(fjDw.getId(), item.getBarcode());
                     if (bottle == null) {
                        FjBottle fjBottle = new FjBottle();
                        fjBottle.setBarcode(item.getBarcode());
                        fjBottle.setPrintno(item.getPrintno());
                        fjBottle.setStyle(item.getStyle());
                        fjBottle.setValve(item.getValve());
                        fjBottle.setProductdate(item.getProductDate());
                        fjBottle.setDiscarddate(item.getDiscardDate());
                        fjBottle.setCheckdate(item.getCheckDate());
                        fjBottle.setNextcheckdate(item.getNextCheckDate());
                        fjBottle.setUpdatedate(item.getUpdateDate());
                        fjBottle.setState(item.getState());
                        fjBottle.setSystemid(item.getId());
                        fjBottle.setFactoryid(item.getFactory().getId());
                        fjBottle.setFactoryname(item.getFactory().getName());
                        fjBottle.setDid(fjDw.getId());
                        this.fjBottleService.saveOrUpdate(fjBottle);
                     }
                  }

                  if (l.size() < bottleList.getPlanCount()) {
                     logger.info("小于等于计划数!!");
                     this.plus_onesecond = true;
                  }

                  bOK = true;
               }
            } else {
               logger.info("气瓶档案为空, 增量下载结束!!");
               this.bDownOK = true;
               this.fjLogService.newLog("增量更新下载", "增量下载气瓶档案 >> 结束");
            }
         } else {
            logger.info("接口返回为空串, 增量下载结束!!");
            this.bDownOK = true;
            this.fjLogService.newLog("增量更新下载", "增量下载气瓶档案 >> 结束");
         }
      } catch (Exception var15) {
         var15.printStackTrace();
         this.fjLogService.newLog("增量更新下载", "增量下载气瓶档案 >> error");
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

   void mySleep(int millis) {
      try {
         Thread.sleep((long)millis);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

   }
}
