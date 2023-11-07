package com.main;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.myserver.NServerEx;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
   exclude = {DruidDataSourceAutoConfigure.class},
   scanBasePackages = {"com.**"} /* 扫描package：com包及其子包*/
)
@MapperScan({"com.model.**.mapper"}) /* MyBatis plus的扫描包范围. */
@EnableScheduling
@EnableAsync
public class MyConsoleApplication implements ApplicationRunner {
   private static final Logger logger = Logger.getLogger(MyApplication.class);
   @Autowired
   ApplicationContext applicationContext;

   /**
    * 程序启动入口
    * @param args
    */
   public static void main(String[] args) {
      (new SpringApplicationBuilder(new Class[]{MyConsoleApplication.class})).web(WebApplicationType.SERVLET).bannerMode(Mode.OFF).run(args);
   }

   /**
    * Server服务启动
    * @param args
    * @throws Exception
    */
   public void run(ApplicationArguments args) throws Exception {
      /*
       * run:52, MyConsoleApplication (com.main)
       * callRunner:786, SpringApplication (org.springframework.boot)
       * callRunners:776, SpringApplication (org.springframework.boot)
       * run:322, SpringApplication (org.springframework.boot)
       * run:140, SpringApplicationBuilder (org.springframework.boot.builder)
       * main:35, MyConsoleApplication (com.main)
       */
      logger.info("-> console run......");
      NServerEx nServerEx = (NServerEx)this.applicationContext.getBean(NServerEx.class);
      if (nServerEx != null) {
         nServerEx.startRun();
      } else {
         logger.info("-> cannot find  server object!!");
      }

   }
}
