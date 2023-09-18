package com.jobs.xk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.xk.entity.XkAssocstation;
import com.model.xk.entity.XkCylinderrec;
import com.model.xk.entity.XkCylindertype;
import com.model.xk.entity.XkDw;
import com.model.xk.entity.XkManufacturer;
import com.model.xk.entity.XkMedium;
import com.model.xk.entity.XkParams;
import com.model.xk.service.XkAssocstationService;
import com.model.xk.service.XkCylinderrecService;
import com.model.xk.service.XkCylindertypeService;
import com.model.xk.service.XkDwService;
import com.model.xk.service.XkLogService;
import com.model.xk.service.XkManufacturerService;
import com.model.xk.service.XkMediumService;
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
public class XkTask {
   private static final Logger logger = Logger.getLogger(XkTask.class);
   final String MAX_DOWNLOAD_NUM = "800";
   @Autowired
   private XkManufacturerService xkManufacturerService;
   @Autowired
   private XkMediumService xkMediumService;
   @Autowired
   private XkCylindertypeService xkCylindertypeService;
   @Autowired
   private XkAssocstationService xkAssocstationService;
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
   private boolean bDownManufactureList = false;
   private boolean bDownMediumList = false;
   private boolean bDownCylinderTypeList = false;

   void execute() {
      this.run(true, "定时下载");
   }

   void executeAtOnce() {
      this.run(false, "立即下载");
   }

   synchronized void run(boolean beDownAll, String tag) {
      logger.info("-> Synchronization task start.............................");
      logger.info("-> 加载参数.............................");
      List<XkParams> params = this.xkParamsService.list();
      System.out.println(params.toString());
      Map<Integer, XkParams> map = this.changeParams(params);
      logger.info("-> 加载单位列表.............................");
      List<XkDw> dwList = this.xkDwService.list();
      this.bDownManufactureList = false;
      this.bDownMediumList = false;
      this.bDownCylinderTypeList = false;

      for(int i = 0; i < dwList.size(); ++i) {
         this.ApiToken = null;
         this.KeepAlive = 0;
         if (beDownAll || ((XkDw)dwList.get(i)).getUpdateAtonce() == 1) {
            logger.info("-> <<" + tag + ">> start.............................");
            this.xkLogService.newLog(tag, "数据更新 >> 开始");

            for(int ii = 0; ii < 5; ++ii) {
               this.downData(map, ii, (XkDw)dwList.get(i));
            }

            logger.info("-> <<" + tag + ">> end!!.............................");
            this.xkLogService.newLog(tag, "数据更新 >> 结束");
         }

         ((XkDw)dwList.get(i)).setUpdateAtonce(0);
         this.xkDwService.saveOrUpdate(dwList.get(i));
         if (i == dwList.size() - 1) {
            break;
         }

         this.mySleep(30000);
      }

      logger.info("-> Synchronization task finished.............................");
      this.mySleep(30000);
   }

   public boolean downData(Map<Integer, XkParams> map, int order, XkDw xkDw) {
      logger.info("-> **downData ...... ");
      boolean bOK = this.fectchOrValidApiToken((XkParams)map.get(1), (XkParams)map.get(2), xkDw.getUserCode(), xkDw.getUserPass());
      if (bOK) {
         if (order == 0) {
            bOK = this.fetchManufactureList((XkParams)map.get(3));
         } else if (order == 1) {
            bOK = this.fetchMediumList((XkParams)map.get(4));
         } else if (order == 2) {
            bOK = this.fetchCylinderTypeList((XkParams)map.get(5));
         } else if (order == 3) {
            bOK = this.fetchAssocStationList((XkParams)map.get(6), xkDw);
         } else if (order == 4) {
            bOK = this.fetchCylinderRecList((XkParams)map.get(7), xkDw);
         }
      }

      this.mySleep(40000);
      return bOK;
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
         logger.info("@@-> recv [validApiToken] response apiToken data: " + jsonString);
         TokenResp tokenResp = (TokenResp)objectMapper.readValue(jsonString, TokenResp.class);
         logger.info("@@-> parse [validApiToken] response apiToken data: " + tokenResp.toString());
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

   boolean fetchManufactureList(XkParams param) {
      boolean bOK = false;

      try {
         if (param.getState() == 1 && !this.bDownManufactureList) {
            logger.info("-> 下载制造厂商列表.............................");
            this.xkLogService.newLog("更新下载", "下载制造厂商列表 >> 开始");
            String MaxInternalTime = "";
            Date dt_MaxInternalTime = this.xkManufacturerService.getMaxInternalTime();
            if (dt_MaxInternalTime == null) {
               MaxInternalTime = "2000-01-01 00:00:00.000";
            } else {
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
               MaxInternalTime = formatter.format(dt_MaxInternalTime);
            }

            String fetchUrl = param.getValue();
            fetchUrl = fetchUrl + "/" + this.ApiToken + "/" + MaxInternalTime + "/";
            RestTemplate rest = new RestTemplate();
            String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
            logger.info("-> recv [fetchFactoryList] response  data: " + jsonString);
            if (jsonString != null) {
               ObjectMapper objectMapper = new ObjectMapper();
               XkManufacturer[] xkManufacturer = (XkManufacturer[])objectMapper.readValue(jsonString, XkManufacturer[].class);
               logger.info("-> parse [fetchFactoryList] response  data: " + xkManufacturer.toString());
               this.xkManufacturerService.saveOrUpdateBatch(Arrays.asList(xkManufacturer));
               this.bDownManufactureList = true;
            }

            this.xkLogService.newLog("更新下载", "下载制造厂商列表 >> 结束");
         }

         bOK = true;
      } catch (Exception var10) {
         var10.printStackTrace();
         this.xkLogService.newLog("更新下载", "下载制造厂商列表 >> error");
      }

      return bOK;
   }

   boolean fetchMediumList(XkParams param) {
      boolean bOK = false;

      try {
         if (param.getState() == 1 && !this.bDownMediumList) {
            logger.info("-> 下载充装介质列表.............................");
            this.xkLogService.newLog("更新下载", "下载充装介质列表 >> 开始");
            String MaxInternalTime = "";
            Date dt_MaxInternalTime = this.xkMediumService.getMaxInternalTime();
            if (dt_MaxInternalTime == null) {
               MaxInternalTime = "2000-01-01 00:00:00.000";
            } else {
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
               MaxInternalTime = formatter.format(dt_MaxInternalTime);
            }

            String fetchUrl = param.getValue();
            fetchUrl = fetchUrl + "/" + this.ApiToken + "/" + MaxInternalTime + "/";
            RestTemplate rest = new RestTemplate();
            String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
            logger.info("-> recv [fetchMediumList] response  data: " + jsonString);
            if (jsonString != null) {
               ObjectMapper objectMapper = new ObjectMapper();
               XkMedium[] xkMedium = (XkMedium[])objectMapper.readValue(jsonString, XkMedium[].class);
               logger.info("-> parse [fetchMediumList] response  data: " + xkMedium.toString());
               this.xkMediumService.saveOrUpdateBatch(Arrays.asList(xkMedium));
               this.bDownMediumList = true;
            }

            this.xkLogService.newLog("更新下载", "下载充装介质列表 >> 结束");
         }

         bOK = true;
      } catch (Exception var10) {
         var10.printStackTrace();
         this.xkLogService.newLog("更新下载", "下载充装介质列表 >> error");
      }

      return bOK;
   }

   boolean fetchCylinderTypeList(XkParams param) {
      boolean bOK = false;

      try {
         if (param.getState() == 1 && !this.bDownCylinderTypeList) {
            logger.info("-> 下载气瓶种类列表.............................");
            this.xkLogService.newLog("更新下载", "下载气瓶种类列表 >> 开始");
            String MaxInternalTime = "";
            Date dt_MaxInternalTime = this.xkCylindertypeService.getMaxInternalTime();
            if (dt_MaxInternalTime == null) {
               MaxInternalTime = "2000-01-01 00:00:00.000";
            } else {
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
               MaxInternalTime = formatter.format(dt_MaxInternalTime);
            }

            String fetchUrl = param.getValue();
            fetchUrl = fetchUrl + "/" + this.ApiToken + "/" + MaxInternalTime + "/";
            RestTemplate rest = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
            logger.info("-> recv [fetchCylinderTypeList] response  data: " + jsonString);
            if (jsonString != null) {
               XkCylindertype[] xkCylindertype = (XkCylindertype[])objectMapper.readValue(jsonString, XkCylindertype[].class);
               logger.info("-> parse [fetchCylinderTypeList] response  data: " + xkCylindertype.toString());
               this.xkCylindertypeService.saveOrUpdateBatch(Arrays.asList(xkCylindertype));
               this.bDownCylinderTypeList = true;
            }

            this.xkLogService.newLog("更新下载", "下载气瓶种类列表 >> 结束");
         }

         bOK = true;
      } catch (Exception var10) {
         var10.printStackTrace();
         this.xkLogService.newLog("更新下载", "下载气瓶种类列表 >> error");
      }

      return bOK;
   }

   boolean fetchAssocStationList(XkParams param, XkDw xkDw) {
      boolean bOK = false;

      try {
         if (param.getState() == 1) {
            logger.info("-> 下载联营充装单位列表.............................");
            this.xkLogService.newLog("更新下载", "下载联营充装单位列表 >> 开始");
            String MaxInternalTime = "";
            Date dt_MaxInternalTime = this.xkAssocstationService.getMaxInternalTime();
            if (dt_MaxInternalTime == null) {
               MaxInternalTime = "2000-01-01 00:00:00.000";
            } else {
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
               MaxInternalTime = formatter.format(dt_MaxInternalTime);
            }

            String fetchUrl = param.getValue();
            fetchUrl = fetchUrl + "/" + this.ApiToken + "/" + MaxInternalTime + "/";
            RestTemplate rest = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
            logger.info("-> recv [fetchAssocStationList] response  data: " + jsonString);
            if (jsonString != null) {
               XkAssocstation[] xkAssocstation = (XkAssocstation[])objectMapper.readValue(jsonString, XkAssocstation[].class);
               logger.info("-> parse [fetchAssocStationList] response  data: " + xkAssocstation.toString());
               List<XkAssocstation> listData = Arrays.asList(xkAssocstation);
               listData.forEach((p) -> {
                  p.setDid(xkDw.getId());
               });
               this.xkAssocstationService.deleteAssocstationByDid(xkDw.getId());
               this.xkAssocstationService.saveOrUpdateBatch(listData);
            }

            this.xkLogService.newLog("更新下载", "下载联营充装单位列表 >> 结束");
         }

         bOK = true;
      } catch (Exception var12) {
         var12.printStackTrace();
         this.xkLogService.newLog("更新下载", "下载联营充装单位列表 >> error");
      }

      return bOK;
   }

   boolean fetchCylinderRecList(XkParams param, XkDw xkDw) {
      boolean bOK = false;

      try {
         if (param.getState() == 1) {
            logger.info("-> 下载气瓶档案.............................");
            this.xkLogService.newLog("更新下载", "下载气瓶档案 >> 开始");
            String MaxInternalTime = "";
            Date dt_MaxInternalTime = this.xkCylinderrecService.getMaxInternalTimeByDid(xkDw.getId());
            if (dt_MaxInternalTime == null) {
               MaxInternalTime = "2000-01-01 00:00:00.000";
            } else {
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
               MaxInternalTime = formatter.format(dt_MaxInternalTime);
            }

            String fetchUrl = param.getValue();
            fetchUrl = fetchUrl + "/" + this.ApiToken + "/" + MaxInternalTime + "/" + "800" + "/";
            RestTemplate rest = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
            logger.info("-> recv [fetchCylinderRecList] response  data: " + jsonString);
            if (jsonString != null) {
               XkCylinderrec[] xkCylinderrec = (XkCylinderrec[])objectMapper.readValue(jsonString, XkCylinderrec[].class);
               logger.info("-> parse [fetchCylinderRecList] response  data: " + xkCylinderrec.toString());
               List<XkCylinderrec> listData = Arrays.asList(xkCylinderrec);
               listData.forEach((p) -> {
                  p.setDid(xkDw.getId());
               });
               this.xkCylinderrecService.saveOrUpdateBatch(listData);
            }

            this.xkLogService.newLog("更新下载", "下载气瓶档案 >> 结束");
         }

         bOK = true;
      } catch (Exception var12) {
         var12.printStackTrace();
         this.xkLogService.newLog("更新下载", "下载气瓶档案 >> error");
      }

      return bOK;
   }

   boolean fetchCylinderRecListByStationCode(XkParams param, String stationCode, XkDw xkDw) {
      boolean bOK = false;

      try {
         if (param.getState() == 1) {
            logger.info("-> 下载指定站点的气瓶档案............................." + stationCode);
            this.xkLogService.newLog("更新下载", "下载指定站点的气瓶档案 >> 开始");
            String MaxInternalTime = "";
            Date dt_MaxInternalTime = this.xkCylinderrecService.getMaxInternalTimeByStationCode(stationCode);
            if (dt_MaxInternalTime == null) {
               MaxInternalTime = "2000-01-01 00:00:00.000";
            } else {
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
               MaxInternalTime = formatter.format(dt_MaxInternalTime);
            }

            String fetchUrl = param.getValue();
            fetchUrl = fetchUrl + "/" + this.ApiToken + "/" + MaxInternalTime + "/" + stationCode + "/" + "800" + "/";
            RestTemplate rest = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = (String)rest.getForObject(fetchUrl, String.class, new Object[0]);
            logger.info("-> recv [fetchCylinderRecList] response  data: " + jsonString);
            if (jsonString != null) {
               XkCylinderrec[] xkCylinderrec = (XkCylinderrec[])objectMapper.readValue(jsonString, XkCylinderrec[].class);
               logger.info("-> parse [fetchCylinderRecList] response  data: " + xkCylinderrec.toString());
               List<XkCylinderrec> listData = Arrays.asList(xkCylinderrec);
               listData.forEach((p) -> {
                  p.setDid(xkDw.getId());
               });
               this.xkCylinderrecService.saveOrUpdateBatch(listData);
            }

            this.xkLogService.newLog("更新下载", "下载指定站点的气瓶档案 >> 结束");
         }

         bOK = true;
      } catch (Exception var13) {
         var13.printStackTrace();
         this.xkLogService.newLog("更新下载", "下载指定站点的气瓶档案 >> error");
      }

      return bOK;
   }
}
