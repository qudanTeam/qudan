package com.zhangheng.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/27.
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigServer//开启config-server
//@EnableDiscoveryClient//将其注册到服务中心
@EnableEurekaClient
@EnableAutoConfiguration
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);

    }
}
