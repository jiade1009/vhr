package org.javaboy.vhr;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "org.javaboy.vhr.mapper")
@EnableAsync  // 实现异步执行定时任务
@EnableScheduling  //开启对定时任务的支持
public class VhrApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(VhrApplication.class);

    public static void main(String[] args) {
        LOGGER.info("let's go to the new world!");
        SpringApplication.run(VhrApplication.class, args);
    }

}