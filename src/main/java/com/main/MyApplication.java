package com.main;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.myserver.NServerEx;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(
   exclude = {DruidDataSourceAutoConfigure.class},
   scanBasePackages = {"com.**"}
)
@MapperScan({"com.model.**.mapper"})
public class MyApplication extends SpringBootServletInitializer implements CommandLineRunner {
   private static final Logger logger = Logger.getLogger(MyApplication.class);
   @Autowired
   ApplicationContext applicationContext;

   public void run(String... args) throws Exception {
      logger.info("-> run......");
      NServerEx nServerEx = (NServerEx)this.applicationContext.getBean(NServerEx.class);
      if (nServerEx != null) {
         nServerEx.startRun();
      } else {
         logger.info("-> cannot find  server object!!");
      }

   }
}
