package com.main;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
public class MyApplication extends SpringBootServletInitializer implements CommandLineRunner {
   private static final Logger logger = Logger.getLogger(MyApplication.class);
   @Autowired
   ApplicationContext applicationContext;

   public void run(String... args) throws Exception {
   }
}
