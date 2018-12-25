package com.qudan.qingcloud.msqudan;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/19.
 */

@SpringBootApplication
@EnableEurekaClient//开启Eureka注册功能
@EnableCircuitBreaker//开启断路器功能
@EnableDiscoveryClient//开启服务注册功能
@EnableFeignClients
@EnableSwagger2
@EnableAutoConfiguration
@MapperScan(basePackages = "com.qudan.qingcloud.msqudan.dao")
@ComponentScan()
//@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class MsQudanApplication {


    public static void main(String[] args) {
        SpringApplication.run(MsQudanApplication.class, args);

    }
}
