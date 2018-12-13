package com.qudan.qingcloud.msqudan.wxpay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/10.
 */
public class MyWXConfig implements WXPayConfig {


    private String appId = "wx5937eccb3b4a99d7";//appId
    private String  mchId = "1520779301";//商户号
    private String key = "861f22c47a40774f26e7f44b472069e7";//密钥key

    private byte[] certData;

    private static MyWXConfig INSTANCE;

    public MyWXConfig() throws Exception {
        //证书位置
        String certPath = "src/resources/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    //单例处理
    public static MyWXConfig getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (MyWXConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MyWXConfig();
                }
            }
        }
        return INSTANCE;
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
