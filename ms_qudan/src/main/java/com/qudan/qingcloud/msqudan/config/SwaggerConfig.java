package com.qudan.qingcloud.msqudan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qudan.qingcloud.msqudan.controller"))
                .paths(PathSelectors.any())
                .build();
    }

 
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "趣单前端接口Resful API", "","1.0", "", "趣单","", null);
        return apiInfo;
    }
}