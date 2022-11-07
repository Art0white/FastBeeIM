package com.fastbee.fastbeeim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2 配置类
 * @author Lovsog
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指定哪个包下面生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.fastbee.fastbeeim.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 文档基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version("1.0")
                .title("极蜜IM接口文档")
                .description("极蜜IM接口文档")
                .contact(new Contact("Lovsog", "localhost:9292/doc.html", "lovsog@163.com"))
                .build();
    }
}