package com.qudan.qingcloud.msqudan.config;

import com.qudan.qingcloud.msqudan.base.JwtFilter;
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

    @Bean
    public JwtFilter JwtFilter(){
        return  new JwtFilter();
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setOrder(3);
        registrationBean.setFilter(JwtFilter());
        registrationBean.addUrlPatterns("/msqudan/api/user/*");

        //跳过过滤的链接 以逗号隔开
        registrationBean.addInitParameter("excludeUrl",
                "/msqudan/api/login," +
                        "/msqudan/api/products/*," +
                        "/msqudan/api/products/," +
                        "/msqudan/api/product/*,"

        );
        //无论有没有JWT都给通过的链接 以逗号隔开
        registrationBean.addInitParameter("uncertainUrl",""
/*     "/msqudan/api/"+*/
        );
        return registrationBean;
    }
}
