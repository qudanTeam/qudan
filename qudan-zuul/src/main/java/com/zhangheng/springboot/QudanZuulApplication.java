package com.zhangheng.springboot;

import com.netflix.zuul.ZuulFilter;
import com.zhangheng.springboot.controller.VUserInfoController;
import com.zhangheng.springboot.filter.error.ErrorFilter;
import com.zhangheng.springboot.filter.post.LoginResponseFilter;
import com.zhangheng.springboot.filter.pre.PreFilter;
import com.zhangheng.springboot.filter.routing.RoutingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
@EnableCircuitBreaker//开启断路器功能
@EnableZuulProxy
@EnableFeignClients//开启使用Feign调用不同微服务的api
@EnableEurekaClient
public class QudanZuulApplication implements CommandLineRunner {

	//日志
	private final static Logger logger = LoggerFactory.getLogger(QudanZuulApplication.class);

	@Value("${spring.datasource.url}")
	private String dateUrl;

	public static void main(String[] args) {
		SpringApplication.run(QudanZuulApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------------"+dateUrl+"---------------------------------------");
		logger.info("------------"+dateUrl+"---------------------------------------");
	}


    //实例化过滤器

	//pre
	@Bean
	public ZuulFilter perFiler() {
		return new PreFilter();
	}

	//post
//	@Bean
//	public ZuulFilter postFiler() {
//		return new LoginResponseFilter();
//	}
//
//	//error
//	@Bean
//	public ZuulFilter errorFiler() {
//		return new ErrorFilter();
//	}
//
//	//routing
//	@Bean
//	public ZuulFilter routingFiler() {
//		return new RoutingFilter();
//	}

}
