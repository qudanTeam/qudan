package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.qudan.qingcloud.msqudan.util.MD5Util;
import com.qudan.qingcloud.msqudan.wxpay.MyWXConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/10.
 * 微信支付相关服务
 */
@Service
public class WxPayServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    /**
     * 统一下单
     * @param trade_type
     * @param product_id
     * @param attach
     * @param out_trade_no
     * @param total_fee
     * @param spbill_create_ip
     * @param type
     * @return
     * @throws Exception
     */
    public Map<String, String> dounifiedOrder(String trade_type,String product_id, String attach, String out_trade_no, String total_fee, String spbill_create_ip, int type) throws Exception {
        Map<String, String> fail = new HashMap<>();
        MyWXConfig config = new MyWXConfig();
        MD5Util md5Util = new MD5Util();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<>();
//        data.put("appid", config.getAppID());
//        data.put("mch_id", config.getMchID());
//        data.put("nonce_str","6128be982a7f40daa930025dedd1a90d");
        String body="订单支付";
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("spbill_create_ip",spbill_create_ip);
        //异步通知地址（请注意必须是外网）
        data.put("notify_url", "http://47.99.242.122:8763/wxpay/notify");
        data.put("nonce_str",WXPayUtil.generateNonceStr());
        /**
         * 交易类型 JSAPI、NATIVE、APP
         * JSAPI h5浏览器调用支付
         * NATIVE 扫码支付
         * APP 手机app内调用支付
         */
        data.put("trade_type", trade_type);
        data.put("attach", attach);
        data.put("product_id",product_id);//如果类型为NATIVE 此参数必传
//        data.put("sign", md5Util.getSign(data));
        StringBuffer url= new StringBuffer();
        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            logger.info("统一下单接口返回:"+resp);
            String returnCode = resp.get("return_code");    //获取返回码
            String returnMsg = resp.get("return_msg");

            if("SUCCESS".equals(returnCode)){       //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
                String resultCode = (String)resp.get("result_code");
                String errCodeDes = (String)resp.get("err_code_des");
                System.out.print(errCodeDes);
                if("SUCCESS".equals(resultCode)){
                    //获取预支付交易回话标志
                    Map<String,String> map = new HashMap<>();
                    String prepay_id = resp.get("prepay_id");
                    String signType = "MD5";
                    map.put("prepay_id",prepay_id);
                    map.put("signType",signType);
                    String sign = md5Util.getSign(map);
                    resp.put("realsign",sign);
                    url.append("prepay_id="+prepay_id+"&signType="+signType+ "&sign="+sign);
                    return resp;
                }else {
                    logger.info("订单号：{},错误信息：{}",out_trade_no,errCodeDes);
                    url.append(errCodeDes);
                }
            }else {
                logger.info("订单号：{},错误信息：{}",out_trade_no,returnMsg);
                url.append(returnMsg);
            }

        } catch (Exception e) {
            logger.info("调取微信支付异常！！！");
            System.out.println(e);
            logger.info(e.getMessage());
        }
        return fail;
    }

    /**
     *  支付结果通知
     * @param notifyData    异步通知后的XML数据
     * @return
     */
    public String payBack(String notifyData) {
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        String xmlBack="";
        Map<String, String> notifyMap = null;
        try {
            notifyMap = WXPayUtil.xmlToMap(notifyData);         // 转换成map
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                String  return_code = notifyMap.get("return_code");//状态
                String out_trade_no = notifyMap.get("out_trade_no");//订单号

                if(return_code.equals("SUCCESS")){
                    if(out_trade_no!=null){
                        //处理订单逻辑
                        /**
                         *          更新数据库中支付状态。
                         *          特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功。
                         *          此处需要判断一下。后面写入库操作的时候再写
                         *
                         */

                        logger.info(">>>>>支付成功");

                        logger.info("微信手机支付回调成功订单号:{}",out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    }else {
                        logger.info("微信手机支付回调失败订单号:{}",out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }

                }
                return xmlBack;
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                logger.error("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return xmlBack;
            }
        } catch (Exception e) {
            logger.error("手机支付回调通知失败",e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        return xmlBack;
    }


    /**
     * 订单查询
     * @param out_trade_no 订单号
     */
    public Map<String, String> orderQuery(String  out_trade_no){
        logger.info("订单查询...");
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==》订单查询");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<>();
        data.put("out_trade_no", out_trade_no);

        try {
            Map<String, String> resp = wxpay.orderQuery(data);
            logger.info("订单查询:"+resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 退款
     * @param out_trade_no 订单号
     * @param total_fee 总金额
     * @param refund_fee 退款金额
     */
    public void doRefund(String out_trade_no,String total_fee,String refund_fee){
        logger.info("退款...");
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>退款");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        HashMap<String, String> data = new HashMap<>();
        data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", refund_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", config.getMchID());

        try {
            Map<String, String> r = wxpay.refund(data);
            logger.info("退款返回:"+r);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("退款异常返回:"+e.getMessage());
        }
    }

    /**
     * 退款订单查询
     * @param out_trade_no 订单号
     */
    public void doRefundQuery(String out_trade_no){
        logger.info("退款订单查询...");
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>退款订单查询");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        HashMap<String, String> data = new HashMap<>();
        data.put("out_refund_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.refundQuery(data);
            logger.info("退款订单查询:"+r);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("退款订单查询异常返回:"+e.getMessage());
        }
    }

    /**
     * 关闭订单
     * @param out_trade_no 订单号
     */
    public void doOrderClose(String out_trade_no){
        logger.info("关闭订单...");
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>关闭订单");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        HashMap<String, String> data = new HashMap<>();
        data.put("out_trade_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.closeOrder(data);
            logger.info("关闭订单返回:"+r);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("关闭订单异常返回:"+e.getMessage());
        }
    }

    /**
     * 撤销
     * @param out_trade_no 订单号
     */
    public void doOrderReverse(String out_trade_no){
        logger.info("撤销订单...");
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>撤销订单");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            Map<String, String> r = wxpay.reverse(data);
            logger.info("撤销订单返回:"+r);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("撤销订单异常返回:"+e.getMessage());
        }
    }

    /**
     * 长链接转短链接
     * @param long_url 长链接
     */
    public void doShortUrl(String long_url){
//        String long_url = "weixin://wxpay/bizpayurl?pr=etxB4DY";
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>长链接转短链接");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        HashMap<String, String> data = new HashMap<>();
        data.put("long_url", long_url);
        try {
            Map<String, String> r = wxpay.shortUrl(data);
            logger.info("长链接转短链接:"+r);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("长链接转短链接异常返回:"+e.getMessage());
        }
    }

    /**
     * 下载对账单
     */
    public void doDownloadBill(){
        logger.info("下载对账单...");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(now);
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>下载对账单");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        HashMap<String, String> data = new HashMap<>();
        data.put("bill_date", format);
        data.put("bill_type", "ALL");
        try {
            Map<String, String> r = wxpay.downloadBill(data);
            logger.info("下载对账单返回:"+r);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("下载对账单异常返回:"+e.getMessage());
        }
    }

}
