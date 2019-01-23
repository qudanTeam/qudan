package com.qudan.qingcloud.msqudan.emailAndSms;


import com.qudan.qingcloud.msqudan.emailAndSms.sms.SmsService;
import com.qudan.qingcloud.msqudan.emailAndSms.sms.SmsServiceSubmail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 * Created by word on 2017/4/20.
 */
@Repository
public class SmsConfig {
    //SUBMAIL 短信  以下4个
    @Value("${sms.submail.xsend_url}")//发送短信
    private String smsSubmailXsendUrl;

    @Value("${sms.submail.template_url}")//模板
    private String smsSubmailTemplateUrl;

    @Value("${sms.submail.market.appId}")
    private String smsSubmailMarketAppId;

    @Value("${sms.submail.market.appKey}")
    private String smsSubmailMarketAppKey;


    @Value("${sms.submail.thing.appId}")
    private String smsSubmailThingAppId;

    @Value("${sms.submail.thing.appKey}")
    private String smsSubmailThineAppKey;

    @Value("${sms.active}")
    private String smsActive;

    @Bean
    public SmsService smsService() {
        SmsService smsService = null;
        if (StringUtils.isNotBlank(smsActive) && smsActive.equals("submail")) {
            smsService = new SmsServiceSubmail(smsSubmailXsendUrl, smsSubmailThingAppId, smsSubmailThineAppKey, smsSubmailMarketAppId, smsSubmailMarketAppKey);
        } else {
            return null;
        }
        return smsService;
    }
}
