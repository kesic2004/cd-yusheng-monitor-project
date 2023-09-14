package com.gen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusGenerator3 {
   public static void main3(String[] args) {
      AutoGenerator mpg = new AutoGenerator();
      mpg.setGlobalConfig((new GlobalConfig()).setFileOverride(true).setOutputDir(System.getProperty("user.dir") + "/src/main/java").setIdType(IdType.AUTO).setBaseResultMap(true).setEntityName("%sEntity").setControllerName("%sController").setServiceName("%sService").setServiceImplName("%sServiceImpl").setMapperName("%sMapper").setXmlName("%sMapper"));
      mpg.setDataSource((new DataSourceConfig()).setUsername("root").setPassword("root").setDbType(DbType.MYSQL).setDriverName("com.mysql.cj.jdbc.Driver").setUrl("jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC"));
      TemplateConfig templateConfig = new TemplateConfig();
      templateConfig.setController("vm/java/Controller.java");
      mpg.setTemplate(templateConfig);
      mpg.setPackageInfo((new PackageConfig()).setParent("com.plus.live").setModuleName("mydb").setController("controller").setService("service").setServiceImpl("service.impl").setEntity("entity").setMapper("mapper"));
      mpg.setStrategy((new StrategyConfig()).setNaming(NamingStrategy.underline_to_camel).setRestControllerStyle(true));
      mpg.execute();
   }
}
