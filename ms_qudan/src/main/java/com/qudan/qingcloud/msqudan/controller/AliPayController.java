package com.qudan.qingcloud.msqudan.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.qudan.qingcloud.msqudan.alipay.AliPayConfig;
import com.qudan.qingcloud.msqudan.util.YHResult;
import io.swagger.annotations.ApiParam;
import org.jolokia.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/11.
 * 支付宝支付相关
 */
@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class AliPayController {
    private final Logger logger = LoggerFactory.getLogger(AliPayController.class);

    @Autowired
    AliPayConfig aliPayConfig;
    /**
     * 生成支付链接
     */
    @PostMapping("alipay/pay")
    public YHResult alipay(
            @ApiParam(required = true, name = "orderNo", value = "订单号") @RequestParam(required = true,value = "orderNo")String orderNo,
            @ApiParam(required = true, name = "totalAmount", value = "支付总金额(单位为元)") @RequestParam(required = true,value = "totalAmount")String totalAmount,
            HttpServletRequest request, HttpServletResponse response
    )throws Exception {

        // 模拟从前台传来的数据
//        String orderNo = DateUtil.getCurrentDateStr(); // 生成订单号
//        String totalAmount = money; // 支付总金额
        String subject = "趣单"; // 订单名称
        String body = "趣单支付宝"; // 商品描述

        // 封装请求客户端
        AlipayClient client = new DefaultAlipayClient(aliPayConfig.getGatewayUrl(), aliPayConfig.getAppID(), aliPayConfig.getMerchantPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType());

        // 支付请求
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliPayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());
        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setProductCode("FAST_INSTANT_TRADE_PAY"); // 设置销售产品码
        model.setOutTradeNo(orderNo); // 设置订单号
        model.setSubject(subject); // 订单名称
        model.setTotalAmount(totalAmount); // 支付总金额
        model.setBody(body); // 设置商品描述
        alipayRequest.setBizModel(model);
        String payUrl = "";
        JSONObject jsonObject = new JSONObject();
        try {
        	//这里使用GET的方式，这样就能生成支付链接
            payUrl = client.pageExecute(alipayRequest, "GET").getBody(); //调用SDK生成表单
            jsonObject.put("payUrl",payUrl);
            return YHResult.build(200,"生成支付链接成功!",jsonObject);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.info("生成支付链接失败!");
            logger.error(e.getErrMsg());
            return YHResult.build(500,"生成支付链接异常!");
        }
//        String form = client.pageExecute(alipayRequest).getBody(); // 生成表单
//
//        response.setContentType("text/html;charset=" + aliPayConfig.getCharset());
//        response.getWriter().write(form); // 直接将完整的表单html输出到页面
//        response.getWriter().flush();
//        response.getWriter().close();
    }

    /**
     * 同步通知
     */
    @RequestMapping(value = "alipay/returnUrl",method= RequestMethod.POST)
    public YHResult returnUrl(HttpServletRequest request) throws Exception {

        // 获取支付宝GET过来反馈信息（官方固定代码）
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        //拼接参数验签
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("params={}", params);
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, aliPayConfig.getAlipayPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType()); // 调用SDK验证签名
        } catch (AlipayApiException e) {
            logger.error("【支付宝同步通知】支付宝回调通知失败 e={} params={}", e, params);
            e.printStackTrace();
            return YHResult.build(500,"同步验签异常!");
        }
        // 返回界面
        if (signVerified) {
            logger.info("前往支付成功页面");
            //商户订单号
            String out_trade_no = params.get("out_trade_no");
            //支付宝交易号
            String trade_no = params.get("trade_no");
            //交易状态
            String trade_status = params.get("trade_status");
            // 支付成功修改订单状态
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {

                //业务处理，主要是更新订单状态
            }
            return YHResult.build(200,"前往支付成功页面");
        } else {
            logger.error("【支付宝同步通知】验证签名错误 params={} ", params);
            logger.info("前往支付失败页面");
            return YHResult.build(400,"前往支付失败页面");
        }
    }

    /**
     * 支付宝服务器异步通知
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "alipay/notifyUrl",method= RequestMethod.POST)
    public YHResult notifyUrl(HttpServletRequest request) throws Exception {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"),"utf-8");
            params.put(name, valueStr);
        }
        logger.info("params={}", params);
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, aliPayConfig.getAlipayPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType()); // 调用SDK验证签名
        } catch (AlipayApiException e) {
            logger.error("【支付宝异步通知】支付宝回调通知失败 e={} params={}", e, params);
            e.printStackTrace();
            return YHResult.build(500,"异步验签异常!");
        }
        if (signVerified) { // 验证成功 更新订单信息
            logger.info("异步通知成功");
            // 修改数据库
            //商户订单号
            String out_trade_no = params.get("out_trade_no");
            //支付宝交易号
            String trade_no = params.get("trade_no");
            //交易状态
            String trade_status = params.get("trade_status");
            // 支付成功修改订单状态
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {

                //业务处理，主要是更新订单状态
            }
            //验签成功返回成功状态码
            return YHResult.build(200,"异步通知成功!");
        } else {
            logger.info("异步通知失败");
            return YHResult.build(400,"异步通知失败!");
        }
    }
}
