package com.jobs.xk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.xk.entity.XkCylinderrec;
import com.model.xk.entity.XkCylinderrecbak;
import com.model.xk.entity.XkDw;
import com.model.xk.entity.XkParams;
import com.model.xk.service.XkCylinderrecService;
import com.model.xk.service.XkCylinderrecbakService;
import com.model.xk.service.XkDwService;
import com.model.xk.service.XkLogService;
import com.model.xk.service.XkParamsService;
import com.thirdinf.client7.TokenResp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class XkTask2 {
   private static final Logger logger = Logger.getLogger(XkTask2.class);
   final String MAX_DOWNLOAD_NUM = "800";
   @Autowired
   private XkCylinderrecbakService xkCylinderrecbakService;
   @Autowired
   private XkCylinderrecService xkCylinderrecService;
   @Autowired
   private XkParamsService xkParamsService;
   @Autowired
   XkDwService xkDwService;
   @Autowired
   XkLogService xkLogService;
   private String ApiToken;
   private int KeepAlive = 0;
   private boolean bDownOK = false;

   void execute() {
      this.run("定时全体气瓶档案下载");
   }

   synchronized void run(String tag) {
      logger.info("-> All Synchronization task start.............................");
      logger.info("-> 加载参数.............................");
      List<XkParams> params = this.xkParamsService.list();
      System.out.println(params.toString());
      Map<Integer, XkParams> map = this.changeParams(params);
      logger.info("-> 加载单位列表.............................");
      List dwList = this.xkDwService.list();

      try {
         this.xkCylinderrecbakService.deleteCylinderrecbak();
      } catch (Exception var7) {
         var7.printStackTrace();
         return;
      }

      boolean bAllOk = true;

      for(int i = 0; i < dwList.size(); ++i) {
         this.ApiToken = null;
         this.KeepAlive = 0;
         this.bDownOK = false;
         logger.info("-> <<" + tag + ">> start.............................");
         this.xkLogService.newLog(tag, "全体数据更新 >> 开始 :" + ((XkDw)dwList.get(i)).getName());
         if (!this.downAllData(map, 4, (XkDw)dwList.get(i))) {
            this.xkLogService.newLog(tag, "全体数据更新出错，更新终止 >> error :" + ((XkDw)dwList.get(i)).getName());
            bAllOk = false;
            break;
         }

         logger.info("-> <<" + tag + ">> end!!.............................");
         this.xkLogService.newLog(tag, "全体数据更新 >> 结束 :" + ((XkDw)dwList.get(i)).getName());
         if (i == dwList.size() - 1) {
            break;
         }

         this.mySleep(1000);
      }

      logger.info("-> All Synchronization task finished.............................");
      if (bAllOk) {
         if (this.data_Transfer()) {
            logger.info("-> 全体数据更新 ok !!");
            this.xkLogService.newLog(tag, "全体数据更新 ok !!");
         } else {
            logger.info("-> 全体数据更新 failed !!");
            this.xkLogService.newLog(tag, "全体数据更新 failed !!");
         }
      }

   }

   private boolean data_Transfer() {
      boolean bOK = true;

      try {
         this.xkCylinderrecService.deleteCylinderrec();
         List<XkCylinderrecbak> l = this.xkCylinderrecbakService.list();

         for(int i = 0; i < l.size(); ++i) {
            XkCylinderrecbak xkCylinderrecbak = (XkCylinderrecbak)l.get(i);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(xkCylinderrecbak);
            XkCylinderrec XkCylinderrec = (XkCylinderrec)objectMapper.readValue(json, XkCylinderrec.class);
            this.xkCylinderrecService.saveOrUpdate(XkCylinderrec);
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         bOK = false;
      }

      return bOK;
   }

   public boolean downAllData(Map<Integer, XkParams> map, int order, XkDw xkDw) {
      logger.info("-> **down all Data ...... ");
      if (order == 4) {
         while(true) {
            boolean bOK;
            do {
               bOK = this.fectchOrValidApiToken((XkParams)map.get(1), (XkParams)map.get(2), xkDw.getUserCode(), xkDw.getUserPass());
            } while(!bOK);

            bOK = this.fetchCylinderRecList((XkParams)map.get(7), xkDw);
            if (!bOK || this.bDownOK) {
               break;
            }

            this.mySleep(1000);
         }
      }

      return this.bDownOK;
   }

   public boolean fectchOrValidApiToken(XkParams param1, XkParams param2, String userCode, String userPass) {
      boolean bOk = false;
      if (this.ApiToken != null && !this.ApiToken.equalsIgnoreCase("") && this.KeepAlive != 0) {
         logger.info("@@-> 验证 ApiToken !");
         bOk = this.validApiToken(param2);
      } else {
         logger.info("@@-> 获取 ApiToken !");
         bOk = this.fetchApiToken(param1, userCode, userPass);
      }

      if (bOk && this.KeepAlive > 5) {
         logger.info("@@-> 获取 or 验证 ApiToken OK!!");
         return true;
      } else {
         logger.info("@@-> 获取 or 验证 ApiToken falied!!");
         return false;
      }
   }

   void mySleep(int millis) {
      try {
         Thread.sleep((long)millis);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

   }

   public boolean fetchApiToken(XkParams param1, String userCode, String UserPass) {
      boolean bOk = false;

      try {
         this.xkLogService.newLog("更新下载", "取得 token >> 开始");
         String fetchTokenUrl = param1.getValue();
         fetchTokenUrl = fetchTokenUrl + "/" + userCode + "/" + UserPass + "/";
         RestTemplate rest = new RestTemplate();
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         String jsonString = (String)rest.getForObject(fetchTokenUrl, String.class, new Object[0]);
         logger.info("@@-> recv [fetchApiToken] response apiToken data: " + jsonString);
         TokenResp tokenResp = (TokenResp)objectMapper.readValue(jsonString, TokenResp.class);
         logger.info("@@-> parse [fetchApiToken] response apiToken data: " + tokenResp.toString());
         this.ApiToken = tokenResp.getToken();
         this.KeepAlive = tokenResp.getKeepAlive();
         bOk = true;
         this.xkLogService.newLog("更新下载", "取得 token >> 结束");
      } catch (Exception var10) {
         var10.printStackTrace();
         this.xkLogService.newLog("更新下载", "取得 token >> error");
      }

      return bOk;
   }

   public boolean validApiToken(XkParams param1) {
      boolean bOk = false;

      try {
         this.xkLogService.newLog("更新下载", "验证 token >> 开始");
         RestTemplate rest = new RestTemplate();
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         String validTokenUrl = param1.getValue();
         validTokenUrl = validTokenUrl + "/" + this.ApiToken + "/";
         String jsonString = (String)rest.getForObject(validTokenUrl, String.class, new Object[0]);
         TokenResp tokenResp = (TokenResp)objectMapper.readValue(jsonString, TokenResp.class);
         this.KeepAlive = tokenResp.getKeepAlive();
         bOk = true;
         this.xkLogService.newLog("更新下载", "验证 token >> 结束");
      } catch (Exception var8) {
         var8.printStackTrace();
         this.xkLogService.newLog("更新下载", "验证 token >> error");
      }

      return bOk;
   }

   Map<Integer, XkParams> changeParams(List<XkParams> params) {
      Map<Integer, XkParams> m = new HashMap();

      for(int i = 0; i < params.size(); ++i) {
         XkParams param = (XkParams)params.get(i);
         m.put(param.getCode(), param);
      }

      return m;
   }

   boolean fetchCylinderRecList(XkParams param, XkDw xkDw) {
      boolean bOK = false;

      try {
         logger.info("-> 下载气瓶档案.............................");
         this.xkLogService.newLog("更新下载", "下载气瓶档案 >> 开始");
         String MaxInternalTime = "";
         Date dt_MaxInternalTime = this.xkCylinderrecbakService.getMaxInternalTimeByDid(xkDw.getId());
         if (dt_MaxInternalTime == null) {
            MaxInternalTime = "2000-01-01 00:00:00.000";
         } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            MaxInternalTime = formatter.format(dt_MaxInternalTime);
         }

         String fetchUrl = param.getValue();
         fetchUrl = fetchUrl + "/" + this.ApiToken + "/" + MaxInternalTime + "/" + "800" + "/";
         logger.info("-> fetchUrl: " + fetchUrl);
         RestTemplate rest = new RestTemplate();
         ObjectMapper objectMapper = new ObjectMapper();
         String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
         if (jsonString != null) {
            XkCylinderrecbak[] xkCylinderrecbak = (XkCylinderrecbak[])objectMapper.readValue(jsonString, XkCylinderrecbak[].class);
            logger.info("-> fetchUrl 数量: " + xkCylinderrecbak.length);
            List<XkCylinderrecbak> listData = Arrays.asList(xkCylinderrecbak);
            listData.forEach((p) -> {
               p.setDid(xkDw.getId());
            });
            this.xkCylinderrecbakService.saveOrUpdateBatch(listData);
         } else {
            logger.info("本轮下载结束!!");
            this.bDownOK = true;
         }

         this.xkLogService.newLog("更新下载", "下载气瓶档案 >> 结束");
         bOK = true;
      } catch (Exception var12) {
         var12.printStackTrace();
         this.xkLogService.newLog("更新下载", "下载气瓶档案 >> error");
      }

      return bOK;
   }
}
