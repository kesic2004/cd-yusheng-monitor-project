package com.gen;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator2 {
   public static String scanner(String tip) {
      Scanner scanner = new Scanner(System.in);
      StringBuilder help = new StringBuilder();
      help.append("请输入" + tip + "：");
      System.out.println(help.toString());
      if (scanner.hasNext()) {
         String ipt = scanner.next();
         if (StringUtils.isNotEmpty(ipt)) {
            return ipt;
         }
      }

      throw new MybatisPlusException("请输入正确的" + tip + "！");
   }

   public static void main5(String[] args) {
      AutoGenerator mpg = new AutoGenerator();
      GlobalConfig gc = new GlobalConfig();
      final String projectPath = System.getProperty("user.dir");
      gc.setOutputDir(projectPath + "/src/main/java");
      gc.setAuthor("alex wong");
      gc.setOpen(false);
      gc.setControllerName("%sController");
      gc.setServiceName("%sService");
      gc.setServiceImplName("%sServiceImpl");
      gc.setMapperName("%sMapper");
      gc.setBaseResultMap(true);
      gc.setBaseColumnList(true);
      mpg.setGlobalConfig(gc);
      DataSourceConfig dsc = new DataSourceConfig();
      dsc.setUrl("jdbc:mysql://127.0.0.1:3306/gas?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
      dsc.setDriverName("com.mysql.cj.jdbc.Driver");
      dsc.setUsername("root");
      dsc.setPassword("root");
      mpg.setDataSource(dsc);
      InjectionConfig cfg = new InjectionConfig() {
         public void initMap() {
         }
      };
      final PackageConfig pc = new PackageConfig();
      pc.setParent("com.scaffold.test");
      mpg.setPackageInfo(pc);
      String templatePath = "/templates/mapper.xml.vm";
      List<FileOutConfig> focList = new ArrayList();
      focList.add(new FileOutConfig(templatePath) {
         public String outputFile(TableInfo tableInfo) {
            return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + ".xml";
         }
      });
      cfg.setFileOutConfigList(focList);
      mpg.setCfg(cfg);
      TemplateConfig templateConfig = new TemplateConfig();
      templateConfig.setXml((String)null);
      mpg.setTemplate(templateConfig);
      StrategyConfig strategy = new StrategyConfig();
      strategy.setNaming(NamingStrategy.underline_to_camel);
      strategy.setColumnNaming(NamingStrategy.underline_to_camel);
      strategy.setEntityLombokModel(true);
      strategy.setRestControllerStyle(true);
      strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
      strategy.setControllerMappingHyphenStyle(true);
      strategy.setTablePrefix(new String[]{pc.getModuleName() + "_"});
      mpg.setStrategy(strategy);
      mpg.setTemplateEngine(new VelocityTemplateEngine());
      mpg.execute();
   }
}
