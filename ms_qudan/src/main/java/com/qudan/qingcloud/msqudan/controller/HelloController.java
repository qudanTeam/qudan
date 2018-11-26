package com.qudan.qingcloud.msqudan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/19.
 */


@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String hello() {
//        ServiceInstance instance = client.getLocalServiceInstance();
//        logger.info("/hello, host:"+instance.getHost()+", service_id:"+instance.getServiceId());

        return "Hello World!";
    }
}
