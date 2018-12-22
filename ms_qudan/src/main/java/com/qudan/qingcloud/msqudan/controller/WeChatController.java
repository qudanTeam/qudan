package com.qudan.qingcloud.msqudan.controller;

import com.google.common.collect.Maps;
import com.qudan.qingcloud.msqudan.service.Impl.WeixinServiceImpl;
import com.qudan.qingcloud.msqudan.util.DateUtil;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.ErrorDetail;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/wechat")
public class WeChatController {


    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;
    @Autowired private WxMpService wxMpService;
    @Autowired private WxMpMessageRouter wxMpMessageRouter;
    private static final Logger log = LoggerFactory.getLogger(WeChatController.class);
    @Autowired private WeixinServiceImpl weixinService;

    @RequestMapping(value = "/router")
    public String router(HttpServletRequest request){
        log.info ("wxMpConfigStorage Info: "+wxMpConfigStorage.getAppId()+","+ wxMpConfigStorage.getSecret()+
                ","+wxMpConfigStorage.getAesKey()+","+wxMpConfigStorage.getToken());

        initRouter(wxMpMessageRouter);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            log.info ("非法请求");
            return "非法请求";
        }

        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            log.info ("Weixin Validate Message, Echostr:"+echostr);
            return echostr;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" : request.getParameter("encrypt_type");

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = null;
            try {
                inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            if (outMessage != null) {
                return outMessage.toXml();
            } else {
                return "";
            }
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = null;
            try {
                inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
            } catch (IOException e) {
            }
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            if (outMessage != null) {
                return outMessage.toEncryptedXml(wxMpConfigStorage);
            } else {
                return "";
            }
        }

        return "";
    }


    @RequestMapping(value = "/oauth")
    public ResponseEntity<Map<String, Object>> oauth(@RequestParam(name = "code")String code,
                                                     @RequestParam(name = "type",defaultValue = "0")Integer type,
                                                     HttpServletRequest request){
        ApiResponseEntity are = new ApiResponseEntity();
        WxMpOAuth2AccessToken oAuth2AccessToken = null;
        WxMpUser wxMpUser = null;
        String ip = request.getHeader("X-Real-IP");
        log.info("ip :" + ip);
        log.info(Thread.currentThread() + ",oauth start time :"+ DateUtil.getFormatDate(new Date(),"yyyy-mm-dd HH:MM:ss.SSS"));
        try {
            if (type.equals(0)) {//用户授权
                Map data = new HashMap();
                oAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
                String openId = oAuth2AccessToken.getOpenId();
                String token = "";
                String unionId = "";
                if (oAuth2AccessToken.getScope().equals(WxConsts.OAUTH2_SCOPE_BASE) ) {//静默模式

                }
            }
        }catch (Throwable ex){
            log.error("授权错误", ex);
        }
        return are.createResponseEntity();
    }


    @RequestMapping(value = "/wxjsapi")
    public ResponseEntity<Map<String, Object>> wxJsApi(@RequestParam(name = "url") String url) {

        ApiResponseEntity are = new ApiResponseEntity();

        String sig_url = null;
        try {
            sig_url = URLDecoder.decode(url, "utf-8");
            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(sig_url);
            Map<String,Object> data = Maps.newHashMap();
            data.put("appId",jsapiSignature.getAppId());
            data.put("noncestr",jsapiSignature.getNonceStr());
            data.put("timestamp",jsapiSignature.getTimestamp());
            data.put("signature",jsapiSignature.getSignature());
            are.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:"+e);
            are.addInfoError(new ErrorDetail("params","params.error","系统异常"));
        }
        return are.createResponseEntity();
    }

    public boolean initRouter = false;

    private void initRouter(WxMpMessageRouter wxMpMessageRouter) {
        if (!initRouter) {
            initRouter = true;
        }
    }
}
