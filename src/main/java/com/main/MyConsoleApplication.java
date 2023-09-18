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
   scanBasePackages = {"com.**"}
)
@MapperScan({"com.model.**.mapper"})
@EnableScheduling
@EnableAsync
public class MyConsoleApplication implements ApplicationRunner {
   private static final Logger logger = Logger.getLogger(MyApplication.class);
   @Autowired
   ApplicationContext applicationContext;

   public static void main(String[] args) {
      (new SpringApplicationBuilder(new Class[]{MyConsoleApplication.class})).web(WebApplicationType.NONE).bannerMode(Mode.OFF).run(args);
   }

   public void run(ApplicationArguments args) throws Exception {
      logger.info("-> console run......");
      NServerEx nServerEx = (NServerEx)this.applicationContext.getBean(NServerEx.class);
      if (nServerEx != null) {
         nServerEx.startRun();
      } else {
         logger.info("-> cannot find  server object!!");
      }

   }
}
