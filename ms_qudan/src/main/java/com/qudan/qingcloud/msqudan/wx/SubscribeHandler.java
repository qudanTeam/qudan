package com.qudan.qingcloud.msqudan.wx;

import com.qudan.qingcloud.msqudan.config.CommonConfig;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SubscribeHandler implements WxMpMessageHandler {
    private static Logger log = LoggerFactory.getLogger("weixin-log");
    private CommonConfig config;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("SubscribeHandler:"+wxMessage.toString());
        WxMpKefuMessage mpCustomMessage = null;
        String messageText = "欢迎您关注趣单 玫瑰][鼓掌][鼓掌]\n" +
                "--------------------------------\n" +
                "1、佣金高，下卡快，无需出门即可办理信用卡[强][强]\n" +
                "--------------------------------\n" +
                "2、成为代理，躺赚佣金➕分成<a href=\"http://www.myhshop.top/login.html\"> 《成为代理》</a> [色][色]\n" +
                "--------------------------------\n" +
                "3、免费领取POS机，养卡提额一键在手<a href=\"http://www.myhshop.top/qudanIndex.html?type=3\"> 《POS界面》</a> [鼓掌][鼓掌]\n" +
                "--------------------------------\n" +
                "4、这是你的专属学习空间，进来看看吧<a href=\"http://www.myhshop.top/novice.html\"> 《新手攻略》</a> [玫瑰][玫瑰]\n" +
                "--------------------------------\n" +
                "5、如果您的问题还未得到解决，请点击<a href=\"http://www.myhshop.top/userMain.html\">《个人中心》</a>联系客服[愉快][愉快]\n" +
                "--------------------------------\n" +
                "客服微信号：\n" +
                "趣单大家庭：QDSH-0918";
        mpCustomMessage = WxMpKefuMessage
                .TEXT()
                .toUser(wxMessage.getFromUser())
                .content(messageText)
                .build();
        if(mpCustomMessage != null){
            wxMpService.getKefuService().sendKefuMessage(mpCustomMessage);
        }
        return null;
    }
}
