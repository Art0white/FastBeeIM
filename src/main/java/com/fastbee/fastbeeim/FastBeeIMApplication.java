package com.fastbee.fastbeeim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * SpringBoot启动类
 * @author Lovsog
 */

@SpringBootApplication
@MapperScan("com.fastbee.fastbeeim.mapper")
public class FastBeeIMApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastBeeIMApplication.class, args);
    }

}
