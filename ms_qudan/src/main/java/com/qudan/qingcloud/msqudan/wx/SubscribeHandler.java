package com.qudan.qingcloud.msqudan.wx;

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
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("SubscribeHandler:"+wxMessage.toString());
        WxMpKefuMessage mpCustomMessage = null;
        String messageText = "[玫瑰]欢迎您关注趣单[玫瑰]\n" +
                "佣金高，下卡快，无需出门即可办理信用卡\n" +
                "成为代理，躺赚佣金➕分成《成为代理》\n" +
                "\n" +
                "免费领取POS机，养卡提额一键在手《POS界面》\n" +
                "这是你的专属学习空间，进来看看吧《新手攻略》\n" +
                "如果您的问题还未得到解决，请点击《个人中心》联系客服\n" +
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
