package com.gen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyBatisPlusGenerator2 {
   static final String dirPath = "C://";
   public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/gas?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
   public static final String USER_NAME = "root";
   public static final String PASSWORD = "root";
   public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
   public static final String AUTHOR = "my";
   public static final String OUTPUT_FILE = "D:\\lht\\java_workspace\\z";
   public static final String PACKAGE = "com.dyp";

   public static void generateByTables(boolean serviceNameStartWithI, String... tableNames) {
      AutoGenerator mpg = new AutoGenerator();
      GlobalConfig gc = new GlobalConfig();
      gc.setOutputDir("D:\\lht\\java_workspace\\z");
      gc.setAuthor("my");
      gc.setFileOverride(true);
      gc.setActiveRecord(true);
      gc.setEnableCache(false);
      gc.setBaseResultMap(true);
      gc.setBaseColumnList(true);
      mpg.setGlobalConfig(gc);
      DataSourceConfig dsc = new DataSourceConfig();
      dsc.setDbType(DbType.MYSQL);
      dsc.setTypeConvert(new MySqlTypeConvert() {
      });
      dsc.setDriverName("com.mysql.cj.jdbc.Driver");
      dsc.setUsername("root");
      dsc.setPassword("root");
      dsc.setUrl("jdbc:mysql://127.0.0.1:3306/gas?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
      mpg.setDataSource(dsc);
      StrategyConfig strategy = new StrategyConfig();
      strategy.setTablePrefix(new String[]{"tb_", "tsys_"});
      strategy.setNaming(NamingStrategy.underline_to_camel);
      strategy.setInclude(tableNames);
      strategy.setEntityBuilderModel(true);
      mpg.setStrategy(strategy);
      PackageConfig pc = new PackageConfig();
      pc.setParent("com.dyp");
      pc.setModuleName("dyptest");
      pc.setController("controler");
      pc.setEntity("entity");
      pc.setMapper("mapper");
      pc.setService("service");
      pc.setServiceImpl("serviceImpl");
      pc.setXml("mapperXml");
      mpg.setPackageInfo(pc);
      InjectionConfig cfg = new InjectionConfig() {
         public void initMap() {
            Map<String, Object> map = new HashMap();
            map.put("dyp", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
            this.setMap(map);
         }
      };
      new ArrayList();
      mpg.setCfg(cfg);
      mpg.execute();
      System.err.println(mpg.getCfg().getMap().get("dyp"));
   }

   public static void main2(String[] args) {
      boolean serviceNameStartWithI = false;
      String[] tableNames = new String[]{"tbldata"};
      generateByTables(serviceNameStartWithI, tableNames);
   }
}
