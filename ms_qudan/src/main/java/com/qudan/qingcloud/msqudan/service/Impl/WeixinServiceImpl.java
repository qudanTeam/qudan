package com.qudan.qingcloud.msqudan.service.Impl;

import com.qudan.qingcloud.msqudan.entity.WeixinBinding;
import com.qudan.qingcloud.msqudan.mymapper.self.WeixinMapperSelf;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinServiceImpl {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeixinMapperSelf weixinMapperSelf;

    public WxMpQrCodeTicket getQrTicket(int sceneid, int qrType){
        try {
            WxMpQrCodeTicket ticket = null;
            if (qrType == 2){
                ticket =  wxMpService.getQrcodeService().qrCodeCreateTmpTicket(sceneid, 2592000);//30过期时间
            } else {
                ticket =  wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneid);
            }
            return ticket;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }


    public WeixinBinding selectBindingByOpenId(String openid){
        return weixinMapperSelf.selectBindingByOpenId(openid);
    }

    public WeixinBinding selectBindingByUserId(Integer userid){
        return weixinMapperSelf.selectBindingByUserId(userid);
    }
}
