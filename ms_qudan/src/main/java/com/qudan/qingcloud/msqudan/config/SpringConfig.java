package com.qudan.qingcloud.msqudan.config;

import com.github.pagehelper.PageHelper;
import com.qudan.qingcloud.msqudan.base.JwtFilter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan
public class SpringConfig {

    @Bean
    public PageHelper pageHelper() {
        System.out.println("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");
        p.setProperty("dialect","mysql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    @Bean
    public FilterRegistrationBean crossFilterregistration() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addUrlPatterns("/msqudan/api/*","/wechat/*");
        registrationBean.addInitParameter("cors.allowed.headers","DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,Accept,Access-Control-Request-Method,Access-Control-Request-Headers");
        registrationBean.addInitParameter("cors.allowed.origins","*");
        registrationBean.addInitParameter("cors.allowed.methods","POST, GET, OPTIONS, PUT, DELETE, HEAD");
        registrationBean.addInitParameter("cors.preflight.maxage","18000");
        registrationBean.addInitParameter("cors.request.decorate","true");
        registrationBean.addInitParameter("cors.support.credentials","false");
        registrationBean.setOrder(1);
        registrationBean.setFilter(corsFilter());
        return registrationBean;
    }


    @Bean
    public CorsFilter corsFilter(){
        return new CorsFilter();
    }

    @Bean
    public JwtFilter JwtFilter(){
        return  new JwtFilter();
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setOrder(3);
        registrationBean.setFilter(JwtFilter());
        registrationBean.addUrlPatterns("/msqudan/api/user/*","/msqudan/api/product/*","/msqudan/api/wxpay/pay");

        //跳过过滤的链接 以逗号隔开
        registrationBean.addInitParameter("excludeUrl",
                "/msqudan/api/login," +
                        "/msqudan/api/products/*," +
                        "/msqudan/api/products/"

        );
        //无论有没有JWT都给通过的链接 以逗号隔开
        registrationBean.addInitParameter("uncertainUrl",
                "/msqudan/api/product/*,"
                + "/msqudan/api/user/apply/loan,"
                + "/msqudan/api/user/apply/card,"
                + "/msqudan/api/user/apply/pos,"
                + "/msqudan/api/user/apply/pos/test,"
                + "/msqudan/api/user/apply/pos/pay/test"
        );
        return registrationBean;
    }

    @Bean
    TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setCorePoolSize(30);
        //taskExecutor.setQueueCapacity(10000);
        //taskExecutor.setKeepAliveSeconds(5000);
        //taskExecutor.initialize();
        //taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }
}
