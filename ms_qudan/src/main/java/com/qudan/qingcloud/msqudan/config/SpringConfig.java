package com.qudan.qingcloud.msqudan.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
public class SpringConfig {

    @Bean
    public FilterRegistrationBean crossFilterregistration() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addUrlPatterns("/msqudan/api/*");
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
}
