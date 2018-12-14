package com.qudan.qingcloud.msqudan.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qudan.qingcloud.msqudan.util.BusinessData;
import com.qudan.qingcloud.msqudan.util.MD5Util;
import com.qudan.qingcloud.msqudan.wxpay.MyWXConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

    /** 用户支付中，需要输入密码 */
    private static final String ERR_CODE_USERPAYING = "USERPAYING";
    private static final String ERR_CODE_AUTHCODEEXPIRE = "AUTHCODEEXPIRE";
    /** 交易状态: 未支付 */
    private static final String TRADE_STATE_NOTPAY = "NOTPAY";
    /** 用户输入密码，尝试30秒内去查询支付结果 */
    private static Integer remainingTimeMs = 10000;
    /** 密钥算法 */
    private static final String ALGORITHM = "AES";
    /** 加解密算法/工作模式/填充方式 */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";


    /**
     * 统一下单
     * @param openid
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
    public Map<String, String> dounifiedOrder(String openid,String trade_type,String product_id, String attach, String out_trade_no, String total_fee, String spbill_create_ip, int type) throws Exception {
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
        data.put("notify_url", "http://47.99.242.122:8763/msqudan/api/wxpay/notify");
        data.put("nonce_str",WXPayUtil.generateNonceStr());
        /**
         * 交易类型 JSAPI、NATIVE、APP
         * JSAPI h5浏览器调用支付
         * NATIVE 扫码支付
         * APP 手机app内调用支付
         */
        data.put("trade_type", trade_type);
        if(!StringUtils.isEmpty(attach)){
            data.put("attach", attach);
        }
        if(!StringUtils.isEmpty(product_id)){
            data.put("product_id",product_id);//如果类型为NATIVE 此参数必传
        }
        if(!StringUtils.isEmpty(openid)){
            data.put("openid",openid);
        }
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
    public Map<String, String> orderQuery(String out_trade_no){
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
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
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
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

    /**
     * 刷卡支付
     * 注意：该方法没有处理return_code=FAIL的情况，暂时不考虑网络问题，这种情况直接返回错误
     */
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
    public Map<String, String> microPayWithPOS(Map<String, String> reqData) throws Exception {
        logger.info("刷卡支付...");
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>刷卡支付");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        // 开始时间(毫秒)
        long startTimestampMs = System.currentTimeMillis();

        Map<String, String> responseMapForPay = wxpay.microPay(reqData);
        logger.info(responseMapForPay.toString());

        // // 先判断 协议字段返回(return_code)，再判断 业务返回，最后判断 交易状态(trade_state)
        // 通信标识，非交易标识
        String returnCode = responseMapForPay.get("return_code");
        if (WXPayConstants.SUCCESS.equals(returnCode)) {
            String errCode = responseMapForPay.get("err_code");
            // 余额不足，信用卡失效
            if (ERR_CODE_USERPAYING.equals(errCode) || "SYSTEMERROR".equals(errCode) || "BANKERROR".equals(errCode)) {
                Map<String, String> orderQueryMap = null;
                Map<String, String> requestData = new HashMap<>();
                requestData.put("out_trade_no", reqData.get("out_trade_no"));

                // 用户支付中，需要输入密码或系统错误则去重新查询订单API err_code, result_code, err_code_des
                // 每次循环时的当前系统时间 - 开始时记录的时间 > 设定的30秒时间就退出
                while (System.currentTimeMillis() - startTimestampMs < remainingTimeMs) {
                    // 商户收银台得到USERPAYING状态后，经过商户后台系统调用【查询订单API】查询实际支付结果。
                    orderQueryMap = wxpay.orderQuery(requestData);
                    String returnCodeForQuery = orderQueryMap.get("return_code");
                    if (WXPayConstants.SUCCESS.equals(returnCodeForQuery)) {
                        // 通讯成功
                        String tradeState = orderQueryMap.get("trade_state");
                        if (WXPayConstants.SUCCESS.equals(tradeState)) {
                            // 如果成功了直接将查询结果返回
                            return orderQueryMap;
                        }
                        // 如果支付结果仍为USERPAYING，则每隔5秒循环调用【查询订单API】判断实际支付结果
                        Thread.sleep(1000);
                    }
                }

                // 如果用户取消支付或累计30秒用户都未支付，商户收银台退出查询流程后继续调用【撤销订单API】撤销支付交易。
                String tradeState = orderQueryMap.get("trade_state");
                if (TRADE_STATE_NOTPAY.equals(tradeState) || ERR_CODE_USERPAYING.equals(tradeState) || ERR_CODE_AUTHCODEEXPIRE.equals(tradeState)) {
                    Map<String, String> reverseMap = wxpay.reverse(requestData);
                    String returnCodeForReverse = reverseMap.get("return_code");
                    String resultCode = reverseMap.get("result_code");
                    if (WXPayConstants.SUCCESS.equals(returnCodeForReverse) && WXPayConstants.SUCCESS.equals(resultCode)) {
                        // 如果撤销成功，需要告诉客户端已经撤销订单了
                        responseMapForPay.put("err_code_des", "用户取消支付或尚未支付，后台已经撤销该订单，请重新支付！");
                    }
                }
            }
        }

        return responseMapForPay;
    }

    /**
     * 从request的inputStream中获取参数
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, String> getNotifyParameter(HttpServletRequest request) throws Exception {
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, length);
        }
        outSteam.close();
        inputStream.close();

        // 获取微信调用我们notify_url的返回信息
        String resultXml = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(resultXml);

        return notifyMap;
    }
    /**
     * 解密退款通知
     * 刷卡支付的
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_16&index=11>退款结果通知文档</a>
     * @return
     * @throws Exception
     */
//    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod",commandProperties = {
//            //设置熔断
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            //时间滚动中最小请求参数，只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
////            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            //休眠时间窗
////            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
//            //错误百分比，判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
////            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),
//            //设置超时
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")
//    })
    public Map<String, String> decodeRefundNotify(HttpServletRequest request) throws Exception {
        logger.info("解密退款通知...");
        MyWXConfig config = null;
        try {
            config = new MyWXConfig();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("单例MyWXConfig-------》异常==>解密退款通知");
            logger.info(e.getMessage());
        }
        WXPay wxpay = new WXPay(config);
        // 从request的流中获取参数
        Map<String, String> notifyMap = this.getNotifyParameter(request);
        logger.info(notifyMap.toString());

        String reqInfo = notifyMap.get("req_info");
        //（1）对加密串A做base64解码，得到加密串B
        byte[] bytes = new BASE64Decoder().decodeBuffer(reqInfo);

        //（2）对商户key做md5，得到32位小写key* ( key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置 )
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        SecretKeySpec key = new SecretKeySpec(WXPayUtil.MD5(config.getKey()).toLowerCase().getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        //（3）用key*对加密串B做AES-256-ECB解密（PKCS7Padding）
        // java.security.InvalidKeyException: Illegal key size or default parameters
        // https://www.cnblogs.com/yaks/p/5608358.html
        String responseXml = new String(cipher.doFinal(bytes),"UTF-8");
        Map<String, String> responseMap = WXPayUtil.xmlToMap(responseXml);
        return responseMap;
    }


    /**
     * 超时回调
     */
//    public BusinessData timeoutFallbackMethod(JSONObject params){
//        BusinessData businessData = new BusinessData();
//        //设置超时返回
//        businessData.setStatusCode("408");
//        businessData.setStatusMessage("服务连接超时!");
//        return businessData;
//    }

}
