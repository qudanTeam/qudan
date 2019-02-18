package com.qudan.qingcloud.msqudan.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/25.
 * 支付宝微信支付记录订单
 */
@Getter
@Setter
public class PayOrder {

    private Long sid;
    //订单号
    private String orderId;
    //微信支付所有参数JSAPI(h5浏览器调用支付) NATIVE(扫码支付) APP(手机app内支付)
    private String tradeType;
    //0:待支付,1:支付成功,2:支付失败
    private String orderStatus;
    //1:微信支付，2:支付宝支付
    private String type;
    //用户id
    private String userId;
    //支付金额(单位：分)
    private String totalFee;
    //微信支付 prepay_id
    private String prepayId;
    //交易时间
    private Date tradeTime;

    private Integer extId;

    private String openid;
}
