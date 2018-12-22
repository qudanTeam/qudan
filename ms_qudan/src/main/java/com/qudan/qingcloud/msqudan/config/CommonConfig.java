package com.qudan.qingcloud.msqudan.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.util.Config;

import java.util.List;
import java.util.Properties;

@Configuration
public class CommonConfig {

    //七牛 以下2个
    @Value("${qiniu.account.accesskey}")
    private String qiniuAccesskey;

    @Value("${qiniu.account.secretkey}")
    private String qiniuSecretkey;

    @Value("${qiniu.upload.image.bucket}")
    private String qiniuUploadImageBucket;

    @Value("${qiniu.upload.image.url}")
    private String qiniuUploadImageUrl;


    public String getQiniuImageUrl(){
        return qiniuUploadImageUrl;
    }

    public String getQiniuUploadImageBucket() {
        return qiniuUploadImageBucket;
    }

    public String getQiniuAccesskey() {
        return qiniuAccesskey;
    }

    public String getQiniuSecretkey() {
        return qiniuSecretkey;
    }

    public void setQiniuUploadImageBucket(String qiniuUploadImageBucket) {
        this.qiniuUploadImageBucket = qiniuUploadImageBucket;
    }

    @Value("${kaptcha.textproducer.font.color}")
    private String fcolor;

    @Value("${kaptcha.textproducer.font.size}")
    private String fsize;

    @Value("${kaptcha.obscurificator.impl}")
    private String obscurificator;

    @Value("${kaptcha.noise.impl}")
    private String noise;

    @Value("${kaptcha.image.width}")
    private String width;

    @Value("${kaptcha.image.height}")
    private String height;

    @Value("${kaptcha.textproducer.char.length}")
    private String clength;

    @Value("${kaptcha.textproducer.char.space}")
    private String cspace;

    @Value("${kaptcha.background.clear.from}")
    private String from;

    @Value("${kaptcha.background.clear.to}")
    private String to;

    @Value("${kaptcha.background.white.from}")
    private String backgroundWhiteFrom;

    @Value("${kaptcha.background.white.to}")
    private String backgroundWhiteTo;

    @Value("${kaptcha.textproducer.font.white}")
    private String fontWhite;

    @Bean
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no"/*kborder*/);//无边框
        properties.put("kaptcha.textproducer.font.color", fcolor);
        properties.put("kaptcha.textproducer.font.size", fsize);
        properties.put("kaptcha.obscurificator.impl", obscurificator);
        properties.put("kaptcha.noise.impl", noise);
        properties.put("kaptcha.image.width", width);
        properties.put("kaptcha.image.height", height);
        properties.put("kaptcha.textproducer.char.length", clength);
        properties.put("kaptcha.textproducer.char.space", cspace);
        properties.put("kaptcha.background.clear.from", from); //和登录框背景颜色一致
        properties.put("kaptcha.background.clear.to", to);
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
