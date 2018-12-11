package com.qudan.qingcloud.msqudan.alipay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/11.
 */
@ConfigurationProperties(prefix = "alipay")
@Data
@Component
public class AliPayConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    private String appID;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String merchantPrivateKey;

    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;

    /**
     * 签名方式
     */
    private String signType="RSA2";

    /**
     * 网关
     */
    private String gatewayUrl="https://openapi.alipay.com/gateway.do";

    /**
     * 编码
     */
    private String  charset= "utf-8";

    /**
     * 异步通知地址
     */
    private String notifyUrl="";

    /**
     * 同步跳转地址
     */
    //Controller Mapping:得放到服务器上，且使用域名解析 IP
    private String returnUrl = "";

    /**
     * 类型
     */
    private String format="json";

    /**
     * 商户号
     */
    private String  sysServiceProviderId="1234";
}
