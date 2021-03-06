package com.qudan.qingcloud.msqudan.wxpay;

import com.github.wxpay.sdk.WXPayConfig;
import org.bouncycastle.util.test.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/10.
 */
@Configuration
public class MyWXConfig implements WXPayConfig {

//    @Value("${appId}")
//    private String appId;
//
//    @Value("${mchId}")
//    private String  mchId;
//
//    @Value("${key}")
//    private String key;
//
//    @Value("${wxpath}")
//    private String wxpath;


    private String appId = "wx17dd8d8d9de5910e";//appId
    private String  mchId = "1524656921";//商户号
    private String key = "gGq1265zbb6743lH5672019JSynb8tg8";//密钥key

    private byte[] certData;

    private static MyWXConfig INSTANCE;

    public MyWXConfig() throws Exception {
        String env = System.getProperty("spring.profiles.active");
        String wxDeveloper = System.getProperty("wx.developer");
        InputStream certStream= null;
        if(!StringUtils.isEmpty(wxDeveloper) && wxDeveloper.equals("sh-pro")){
            appId = "wx5937eccb3b4a99d7";
            mchId = "1520779301";
            key = "g317g180q3355G2233G3344Q998ggq88";
            certStream=Test.class.getResourceAsStream("/wxproconfig/apiclient_cert_sh.p12");
        } else {
            appId = "wx17dd8d8d9de5910e";
            mchId = "1524656921";
            key = "gGq1265zbb6743lH5672019JSynb8tg8";
            certStream=Test.class.getResourceAsStream("/wxproconfig/apiclient_cert.p12");
        }
        //证书位置(本地读法)
//        File file = (ResourceUtils.getFile("classpath:wxconfig/apiclient_cert.p12"));
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
        //打包后的读法
//        InputStream certStream=Test.class.getResourceAsStream("/wxproconfig/apiclient_cert.p12");
//        InputStream certStream=Test.class.getResourceAsStream(wxpath);
        this.certData = new byte[certStream.available()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return appId;
    }

    public String getMchID() {
        return mchId;
    }

    public String getKey() {
        return key;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
