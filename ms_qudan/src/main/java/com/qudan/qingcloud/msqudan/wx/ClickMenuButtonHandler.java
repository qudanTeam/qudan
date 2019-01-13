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

public class ClickMenuButtonHandler implements WxMpMessageHandler {
    private static Logger log = LoggerFactory.getLogger("weixin-log");
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("ClickMenuButtonHandler:"+wxMessage.toString());
        WxMpKefuMessage mpCustomMessage = null;
        if ("lxwm".equals(wxMessage.getEventKey())) {
            String messageText = "微信号：QDSH-0918\nQQ号：609677651";
            mpCustomMessage = WxMpKefuMessage
                    .TEXT()
                    .toUser(wxMessage.getFromUser())
                    .content(messageText)
                    .build();
        } else if ("zxjd".equals(wxMessage.getEventKey())) {
            mpCustomMessage = WxMpKefuMessage.IMAGE().toUser(wxMessage.getFromUser()).mediaId("vA29UdLF_BJpFYjU7Z1Ss5BM9YHnzdEytmvhOlvkYds").build();
        }
        if(mpCustomMessage != null){
            wxMpService.getKefuService().sendKefuMessage(mpCustomMessage);
        }
        return null;
    }
}
