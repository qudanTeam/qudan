package com.qudan.qingcloud.msqudan.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    public String getQiniuImageUrl(){
        return "http://qudan.com/";
    }
}
