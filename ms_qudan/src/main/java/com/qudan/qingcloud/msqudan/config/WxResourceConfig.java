package com.qudan.qingcloud.msqudan.config;

import com.qudan.qingcloud.msqudan.wx.WxMpInDataSourceConfigStorage;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class WxResourceConfig {
    public static String WEIXIN_QUDAN_APPID;

    public static String WEIXIN_QUDAN_APPSECRET;

    public static String WEIXIN_QUDAN_TOKEN;

    public static String WEIXIN_QUDAN_ENCODINGAESKEY;

    public static String WEIXIN_SERVER_URL;

    public static String WEIXIN_SERVER_API_URL;

    public static String WEIXIN_MCH_ID;

    public static String WEIXIN_MCH_KEY;

    public static String WEIXIN_KEY_PATH;

    @PostConstruct
    private void init(){
        if (StringUtils.isNotBlank(System.getProperty("wx.developer"))) {
            switch (System.getProperty("wx.developer")) {
                case "liuzhi"://刘智的测试号
                    WEIXIN_QUDAN_APPID = "wx06e1b69e6f6096f2";
                    WEIXIN_QUDAN_APPSECRET = "81bd331f3954a668b00965fdfd6049a9";
                    WEIXIN_QUDAN_TOKEN = "weixinQudan";
                    WEIXIN_QUDAN_ENCODINGAESKEY = "CaTcTjaZqZIhZSMCnmJojhiAAwaBAjz3guaFU1BsHRg";
                    WEIXIN_SERVER_URL = "http://qudan.dev.moore.ren/";
                    WEIXIN_SERVER_API_URL = "http://qudan.dev.moore.ren/";
                    break;
                case "idc"://idc的测试号
                    WEIXIN_QUDAN_APPID = "wx17dd8d8d9de5910e";
                    WEIXIN_QUDAN_APPSECRET = "d0088c1d4ce6036620435e707b47570a";
                    WEIXIN_QUDAN_TOKEN = "weixinQudan";
                    WEIXIN_QUDAN_ENCODINGAESKEY = "x8kys4HygZdqZh6ZSXUHYEHwrbQZiz2OnndKXYvDqDg";
                    WEIXIN_SERVER_URL = "http://msqudan.myhshop.top";
                    WEIXIN_SERVER_API_URL = "http://msqudan.myhshop.top/msquan/api";
            }
        } else {//正式 wx-pro
            WEIXIN_QUDAN_APPID = "wx5937eccb3b4a99d7";
            WEIXIN_QUDAN_APPSECRET = "861f22c47a40774f26e7f44b472069e7";
            WEIXIN_QUDAN_TOKEN = "weixinQudan";
            WEIXIN_QUDAN_ENCODINGAESKEY = "LrXr0FUsS1WVZ151OYXicZoVx7UqyB01BtRbM9W9bgu";
            WEIXIN_SERVER_URL = "http://msqudan.myhshop.top";
            WEIXIN_SERVER_API_URL = "http://msqudan.myhshop.top/msquan/api";
        }
    }


    @Bean
    WxMpConfigStorage wxMpConfigStorage(){
        WxMpConfigStorage storage = new WxMpInDataSourceConfigStorage(WEIXIN_QUDAN_APPID,WEIXIN_QUDAN_APPSECRET,
                WEIXIN_QUDAN_TOKEN,WEIXIN_QUDAN_ENCODINGAESKEY);
        return storage;
    }

    @Bean
    WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    WxMpMessageRouter wxMpMessageRouter(){
        WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService());
        return wxMpMessageRouter;
    }
}
