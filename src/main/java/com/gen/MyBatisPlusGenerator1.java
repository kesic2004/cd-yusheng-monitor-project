package com.gen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.sql.SQLException;

public class MyBatisPlusGenerator1 {
   public static void main1(String[] args) throws SQLException {
      GlobalConfig config = new GlobalConfig();
      String projectPath = System.getProperty("user.dir");
      config.setActiveRecord(true).setAuthor("author").setOutputDir(projectPath + "/src/main/java").setFileOverride(true).setIdType(IdType.AUTO).setServiceName("%sService").setBaseResultMap(true).setBaseColumnList(true);
      DataSourceConfig dsConfig = new DataSourceConfig();
      dsConfig.setDbType(DbType.MYSQL).setDriverName("com.mysql.cj.jdbc.Driver").setUsername("root").setPassword("root").setUrl("jdbc:mysql://127.0.0.1:3306/gas?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
      StrategyConfig stConfig = new StrategyConfig();
      stConfig.setCapitalMode(true).setNaming(NamingStrategy.underline_to_camel).setColumnNaming(NamingStrategy.underline_to_camel).setTablePrefix("tbl_").setInclude("tblUserManager");
      stConfig.setRestControllerStyle(true);
      PackageConfig pkConfig = new PackageConfig();
      pkConfig.setParent("com.model").setModuleName("tblusermanager").setMapper("mapper").setService("service").setController("controller").setEntity("entity").setXml("mapper/xml");
      AutoGenerator ag = new AutoGenerator();
      ag.setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig).setPackageInfo(pkConfig);
      TemplateConfig templateConfig = new TemplateConfig();
      ag.setTemplate(templateConfig);
      InjectionConfig cfg = new InjectionConfig() {
         public void initMap() {
         }
      };
      ag.setCfg(cfg);
      ag.execute();
   }
}
