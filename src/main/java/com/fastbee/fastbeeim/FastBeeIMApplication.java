package com.fastbee.fastbeeim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * SpringBoot启动类
 * @author Lovsog
 */
@EnableSwagger2
@SpringBootApplication
public class FastBeeIMApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastBeeIMApplication.class, args);
    }

}
