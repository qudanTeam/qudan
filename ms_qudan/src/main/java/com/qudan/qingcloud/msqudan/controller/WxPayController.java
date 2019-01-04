package com.qudan.qingcloud.msqudan.controller;

import com.alibaba.fastjson.JSONObject;
import com.qudan.qingcloud.msqudan.service.Impl.WxPayServiceImpl;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.MD5Util;
import com.qudan.qingcloud.msqudan.util.YHResult;
import com.qudan.qingcloud.msqudan.wxpay.MyWXConfig;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/10.
 * 微信支付相关
 */
@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class WxPayController {

    private final Logger logggr = LoggerFactory.getLogger(WxPayController.class);

    @Autowired
    private WxPayServiceImpl wxPayService;

    /**
     * 统一下单接口
     * 同时指出GET POST 默认是GET
     */
    @RequestMapping(value = "wxpay/pay", method = {RequestMethod.GET, RequestMethod.POST})
    public YHResult orderPay(
//            @ApiParam(required = true, name = "user_id", value = "用户ID") @RequestParam(required = true,value = "user_id")String user_id,
//            @ApiParam(required = false, name = "openid", value = "微信用户标识 当交易类型为JSAPI时必传") @RequestParam(required = false,value = "openid")String openid,
//            @ApiParam(required = true, name = "out_trade_no", value = "商户订单号") @RequestParam(required = true,value = "out_trade_no")String out_trade_no,
            @ApiParam(required = true, name = "total_fee", value = "订单总金额，单位为分") @RequestParam(required = true,value = "total_fee")String total_fee,
            @ApiParam(required = true, name = "trade_type", value = "交易类型 JSAPI(h5浏览器调用支付) NATIVE(扫码支付) APP(手机app内支付)") @RequestParam(required = true,value = "trade_type")String trade_type,
            @ApiParam(required = false, name = "product_id", value = "商品id 交易类型为NATIVE时必传") @RequestParam(required = false,value = "product_id")String product_id,
            HttpServletRequest req, HttpServletResponse response) throws Exception {
        logggr.info("进入微信支付申请...");
        JSONObject jsonObject = new JSONObject();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
        //订单号
        String out_trade_no = dateFormat.format(now);
//        String out_trade_no=hehe+"wxpay";  //777777 需要前端给的参数
//        String total_fee="1";              //7777777  微信支付钱的单位为分
//        String user_id="1";               //77777
//        String coupon_id="7";               //777777
        Integer userId = LocalUserHelper.getUserId();
        String attach=userId+"";
        MyWXConfig config = new MyWXConfig();
//        String spbill_create_ip = GetIPAddrUtil.getIpAddr(req);
        String spbill_create_ip="47.99.242.122";
        logggr.info(spbill_create_ip);
        Map<String,String> result = wxPayService.dounifiedOrder(trade_type,product_id,attach,userId+"",out_trade_no,total_fee,spbill_create_ip,1);
        if(result == null){
            return YHResult.build(500,"签名错误");
        }
        String nonce_str = (String)result.get("nonce_str");
        String prepay_id = (String)result.get("prepay_id");
        String code_url ="";
        //交易状态为NATIVE时 获取二维码
        if(trade_type.equals("NATIVE")){
             code_url = (String)result.get("code_url");
        }
        Long time =System.currentTimeMillis()/1000;
        String timestamp=time.toString();
        //签名生成算法
        MD5Util md5Util = new MD5Util();
        Map<String,String> map = new HashMap<>();
        map.put("appid",config.getAppID());
        map.put("partnerid",config.getMchID());
        map.put("package","Sign=WXPay");
        map.put("noncestr",nonce_str);
        map.put("timestamp",timestamp);
        map.put("prepayid",prepay_id);
        String sign = md5Util.getSign(map);

        String resultString="{\"appid\":\""+config.getAppID()+"\",\"partnerid\":\""+config.getMchID()+"\",\"package\":\"Sign=WXPay\"," +
                "\"noncestr\":\""+nonce_str+"\",\"timestamp\":"+timestamp+"," +
                "\"prepayid\":\""+prepay_id+"\",\"sign\":\""+sign+"\"}";
        logggr.info(resultString);
        jsonObject.put("appid",config.getAppID());
        jsonObject.put("partnerid",config.getMchID());
        jsonObject.put("package","Sign=WXPay");
        jsonObject.put("noncestr",nonce_str);
        jsonObject.put("timestamp",timestamp);
        jsonObject.put("prepayid",prepay_id);
        jsonObject.put("sign",sign);
        jsonObject.put("code_url",code_url);

        return YHResult.build(200,"唤起支付成功!",jsonObject);    //给前端app返回此字符串，再调用前端的微信sdk引起微信支付

    }

    /**
     * 订单异步通知
     */
    @ApiOperation(value = "手机订单支付完成后回调")
    @RequestMapping(value = "wxpay/notify",method = {RequestMethod.GET, RequestMethod.POST})
    public String WXPayBack(HttpServletRequest request,HttpServletResponse response){
        String resXml="";
        logggr.info("进入异步通知");
        try{
            //
            InputStream is = request.getInputStream();
            //将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml=sb.toString();
            logggr.info(resXml);
            String result = wxPayService.payBack(resXml);
//            return "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";
            return result;
        }catch (Exception e){
            logggr.error("手机支付回调通知失败",e);
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return result;
        }
    }

}
