package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.config.QudanConstant;
import com.qudan.qingcloud.msqudan.entity.*;
import com.qudan.qingcloud.msqudan.mymapper.*;
import com.qudan.qingcloud.msqudan.mymapper.self.BankMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.UserMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.VipMapperSelf;
import com.qudan.qingcloud.msqudan.util.*;
import com.qudan.qingcloud.msqudan.util.requestBody.QueryBankRB;
import com.qudan.qingcloud.msqudan.util.requestBody.TxRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.BankSimple;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserFinanceServiceImpl {
    private final Logger log = LoggerFactory.getLogger(UserFinanceServiceImpl.class);
    @Autowired
    UserMapperSelf userMapperSelf;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TradeTypeMapper tradeTypeMapper;

    @Autowired
    VipConfigMapper vipConfigMapper;

    @Autowired
    VipMapperSelf vipMapperSelf;

    @Autowired
    VipRecordMapper vipRecordMapper;

    @Autowired
    BankMapperSelf bankMapperSelf;

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    CommonConfig config;

    @Autowired
    BankQueryMapper bankQueryMapper;



    //TODO 商品搜索记录
    //TODO 记录商品搜索记录
    //TODO 成为VIP

    public Map<String,Object>queryCardInfo(ApiResponseEntity ARE, QueryBankRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(RB.getBankId() == null){
            ARE.addInfoError("bankId.isEmpty", "bankId不能为空");
            return null;
        }
        BankSimple bankSimple = bankMapperSelf.selectSimpleByProductId(RB.getBankId());
        if(StringUtils.isNotBlank(bankSimple.getLogo())){
            bankSimple.setLogo(ComUtils.addPrefixToImg(bankSimple.getLogo(), config.getQiniuImageUrl()));
        }
        if(bankSimple == null){
            ARE.addInfoError("bankId.isNotExist", "银行信息不存在");
            return null;
        }
        if(StringUtils.isBlank(bankSimple.getGetLink())){
            ARE.addInfoError("bankId.linkIsEmpty", "查询链接不存在");
            return null;
        }
        if(bankSimple.getGetLink().indexOf("xyk.cebbank") > -1) { //光大
            if(StringUtils.isBlank(RB.getIdno())){
                ARE.addInfoError("idno.isEmpty", "身份证不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getName())){
                ARE.addInfoError("name.isEmpty", "姓名不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getImgCode())){
                ARE.addInfoError("imgCode.isEmpty", "imgCode不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getActivityCode())){
                ARE.addInfoError("activityCode.isEmpty", "activityCode不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getCookieStr())){
                ARE.addInfoError("cookieStr.isEmpty", "cookieStr不能为空");
                return null;
            }
            List<ProcessFromBank> bfbs = processGDJsoup(ARE, RB);
            if(!CollectionUtils.isEmpty(bfbs)){
                for (ProcessFromBank processFromBank : bfbs){
                    BankQuery bankQuery = new BankQuery();
                    BeanUtils.copyProperties(processFromBank, bankQuery);
                    bankQuery.setBankId(RB.getBankId());
                    bankQuery.setBankName(bankSimple.getBankName());
                    bankQueryMapper.insertSelective(bankQuery);
                }
                data.put("bfbs", bfbs);
            }
        } else  if(bankSimple.getGetLink().indexOf("creditcard.cmbc") > -1) { //民生
            if(StringUtils.isBlank(RB.getIdno())){
                ARE.addInfoError("idno.isEmpty", "身份证不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getName())){
                ARE.addInfoError("name.isEmpty", "姓名不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getImgCode())){
                ARE.addInfoError("imgCode.isEmpty", "imgCode不能为空");
                return null;
            }
            List<ProcessFromBank> bfbs = processMSJsoup(ARE, RB);
            if(!CollectionUtils.isEmpty(bfbs)){
                for (ProcessFromBank processFromBank : bfbs){
                    BankQuery bankQuery = new BankQuery();
                    BeanUtils.copyProperties(processFromBank, bankQuery);
                    bankQuery.setBankId(RB.getBankId());
                    bankQuery.setBankName(bankSimple.getBankName());
                    bankQueryMapper.insertSelective(bankQuery);
                }
                data.put("bfbs", bfbs);
            }
        } else{
            ARE.addInfoError("imgCode.notNeed", "暂未对接该银行，请联系客服!");
        }
        return data;
    }


    public List<ProcessFromBank> processMSJsoup(ApiResponseEntity ARE,  QueryBankRB RB) {
        List<ProcessFromBank> bfbs = new ArrayList<ProcessFromBank>();
        try {
            HttpResponse<String> response = Unirest.post("https://creditcard.cmbc.com.cn/fe/op_exchange_rate/messageSubmit.gsp")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Cookie", RB.getCookieStr())
                    .header("cache-control", "no-cache")
                    .body("sKeyType=01&sCustId="+ RB.getIdno() +"&DYPW="+ RB.getActivityCode() +"&mar=1")
                    .asString();
            String body = response.getBody();
            log.info("ms responseBody", body);
            Map<String,Object> map = QudanJsonUtils.parseJSONToMap(body);
            String retCode = map.get("retCode").toString();
            if(Integer.valueOf(retCode) > 0){
                ARE.addInfoError("mobileCodeLink.isError", map.get("msg").toString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("民生银行 验证手机号错误", e);
            ARE.addInfoError("mobileCodeLink.isSystemError", "手机号验证错误， 稍后再试");
            return null;
        }

        try {
            HttpResponse<String> response = Unirest.post("https://creditcard.cmbc.com.cn/fe/op_exchange_rate/cardProgressStep.gsp?COUT=1&FOUT=5")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Cookie", RB.getCookieStr())
                    .header("cache-control", "no-cache")
                    .header("Postman-Token", "4a3d9667-145b-4597-b11b-7b4380c35671")
                    .body("COUT=1&FOUT=10")
                    .asString();
            String body = response.getBody();
            log.info("ms responseBody", body);
            Map<String,Object> map = QudanJsonUtils.parseJSONToMap(body);
            String retCode = map.get("retCode").toString();
            if(Integer.valueOf(retCode) > 0){
                ARE.addInfoError("mobileCodeLink.isError", map.get("msg").toString());
                return null;
            }
            Map<String,Object> data  = (Map<String, Object>) map.get("data");
            List<Map<String,Object>> list = (List<Map<String, Object>>) data.get("list");
            for (Map<String,Object> vo : list){
                ProcessFromBank processFromBank = new ProcessFromBank();
                processFromBank.setName(RB.getName());
                processFromBank.setCardStatus(vo.get("MscSrc").toString());
                processFromBank.setJinjianDate(vo.get("sLostDate").toString());
                processFromBank.setCardCat(vo.get("Add11").toString());
                bfbs.add(processFromBank);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("民生银行 验证完手机获取银行返回信息", e);
            ARE.addInfoError("mobileCodeLink.isSystemError", "手机号验证错误， 稍后再试");
            return null;
        }
        return bfbs;
    }

 /*   public static void main(String[] args) {
        String code = "{\n" +
                "    \"retCode\": \"0000\",\n" +
                "    \"msg\": \"成功获取列表信息！\",\n" +
                "    \"data\": {\n" +
                "        \"FOUT\": \"1\",\n" +
                "        \"list\": [\n" +
                "            {\n" +
                "                \"sBusiDept\": \"9011345841\",\n" +
                "                \"Add11\": \"民生同道星座金卡\",\n" +
                "                \"MscSrc\": \"已产生卡片\",\n" +
                "                \"sLostDate\": \"2019-01-11\",\n" +
                "                \"CardNo\": \"6226********4531\",\n" +
                "                \"cMaFlag\": \"主卡\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        Map<String,Object> map = QudanJsonUtils.parseJSONToMap(code);
        String retCode = map.get("retCode").toString();
        Map<String,Object> data  = (Map<String, Object>) map.get("data");
        List<Map<String,Object>> list = (List<Map<String, Object>>) data.get("list");
        for (Map<String,Object> vo : list){
            ProcessFromBank processFromBank = new ProcessFromBank();
            processFromBank.setName("123");
            processFromBank.setCardStatus(vo.get("MscSrc").toString());
            processFromBank.setJinjianDate(vo.get("sLostDate").toString());
            processFromBank.setCardCat(vo.get("Add11").toString());
            System.out.println(processFromBank.toString());
        }
    }*/

    public List<ProcessFromBank> processGDJsoup(ApiResponseEntity ARE,  QueryBankRB RB){
        Document document = null;
        try {
            if(RB.getTest()){
                document = processGDJsoupDocTest(ARE, RB);
            } else {
                document = processGDJsoupDoc(ARE, RB);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解析光大银行文本错误", e);
            ARE.addInfoError("bankGet.isError", "获取查询信息错误！");
            return null;
        }
        List<ProcessFromBank> list = new ArrayList<ProcessFromBank>();
        try {
            Elements elements  = document.getElementsByClass("borderWrap2");
            Elements table = elements.select("table");
            if(table == null || table.size() == 0){
                log.error("解析光大银行文本错误", new RuntimeException(document.toString()));
                ARE.addInfoError("mobileCodeLink.isError", "手机验证码不正确");
            }
            for(Element element:table){
                if(element.text()!=null&& !"".equals(element.text())){
                    Elements es = element.select("tr");
                    for(Element tdelement:es){
                        if(tdelement.text().indexOf(RB.getName()) > -1){
                            ProcessFromBank bfb = new ProcessFromBank();
                            Elements tdes = tdelement.select("td");
                            for(int i = 0; i < tdes.size(); i++){
                                if(i == 0){
                                    bfb.setName(tdes.get(i).text());
                                }
                                if(i == 1){
                                    bfb.setCardCat(tdes.get(i).text());
                                }
                                if(i == 3){
                                    bfb.setJinjianDate(tdes.get(i).text());
                                }
                                if(i == 4){
                                    bfb.setCardStatus(tdes.get(i).text());
                                }
                            }
                            list.add(bfb);
                        }
                    }
                }
            }
        }catch (Exception ex){
            log.error("解析光大银行文本错误", ex);
            ARE.addInfoError("bankGet.isError", "手机验证码不正确");
        }
        return list;
    }

    public Document processGDJsoupDocTest(ApiResponseEntity ARE,  QueryBankRB RB) throws Exception{
        Connection.Response response = Jsoup.connect("http://qudanmedia.myhshop.top/msqudan/gd.html")
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .method(Connection.Method.GET)
                .followRedirects(true)
                .execute();
        return response.parse();
    }

    public Document processGDJsoupDoc(ApiResponseEntity ARE,  QueryBankRB RB) throws Exception{
        Connection.Response response = Jsoup.connect("https://xyk.cebbank.com/home/fz/card-app-status-query.htm")
                        .userAgent("Mozilla/5.0")
                        .header("Cookie", RB.getCookieStr())
                        .timeout(10 * 1000)
                        .method(Connection.Method.POST)
                        .data("name", RB.getName())
                        .data("id_no", RB.getIdno())
                        .data("ver_code", RB.getImgCode())
                        .data("activity_code", RB.getActivityCode())
                        .followRedirects(true)
                        .execute();
        return response.parse();
    }

    public Map<String,Object> verifyCodeTrigger(ApiResponseEntity ARE, QueryBankRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(RB.getBankId() == null){
            ARE.addInfoError("bankId.isEmpty", "bankId不能为空");
            return null;
        }
        BankSimple bankSimple = bankMapperSelf.selectSimpleByProductId(RB.getBankId());
        if(StringUtils.isNotBlank(bankSimple.getLogo())){
            bankSimple.setLogo(ComUtils.addPrefixToImg(bankSimple.getLogo(), config.getQiniuImageUrl()));
        }
        if(bankSimple == null){
            ARE.addInfoError("bankId.isNotExist", "银行信息不存在");
            return null;
        }
        if(StringUtils.isBlank(bankSimple.getVerifyCodeLink()) ){
            ARE.addInfoError("bankId.imgCodeLinkIsEmpty", "图形验证码的链接不存在");
            return null;
        }


        if(StringUtils.isNotBlank(bankSimple.getVerifyCodeLink()) && bankSimple.getVerifyCodeLink().indexOf("xyk.cebbank") > -1) { //光大
            try {
                Map<String,Object> objectMap  = HttpUtil.doGetWithCookies("https://xyk.cebbank.com/verify_code.jpg?" + RandomUtils.generateMixString(5) +"=");

                /*HttpResponse<InputStream> response = Unirest.get("https://xyk.cebbank.com/verify_code.jpg?" + RandomUtils.generateMixString(5) +"=")
                        .header("content-type", "application/x-www-form-urlencoded")
                        .header("x-requested-with", "XMLHttpRequest")
                        .header("cookie", "weblogic=3d07a8c0; JSESSIONID=0lENLdo6JK4SIC9cUTPazYxIqNsNjCkIzMk9wlpK-tSztcqomZaD!-228252945!-673098056; __v=1.2652440260958570500.1546409418.1546409418.1546409418.1; __l=770095; ALLYESID4=10EF888DC7270C7B")
                        .asBinary();*/
                InputStream inputStream = (InputStream) objectMap.get("input");
                byte[] bytes = null;
                bytes = ImageUtils.input2byte(inputStream);
                String imgKey =  new UploadToQiniu(config, "qudan", "img", "images/bank/imgcode", RandomUtils.generateMixString(12), bytes).upload();
                String url =  ComUtils.addPrefixToImg(imgKey, config.getQiniuImageUrl());
                data.put("imgCodeUrl",url);

                //List<String> cookieStr = response.getHeaders().get("Set-Cookie");
                data.put("cookieStr", objectMap.get("cookieStr").toString());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("触发验证码失败", e);
                ARE.addInfoError("imgCode.error", "获取验证码失败");
            }
        } else if(StringUtils.isNotBlank(bankSimple.getVerifyCodeLink()) && bankSimple.getVerifyCodeLink().indexOf("creditcard.cmbc") > -1){
            try {
                Map<String,Object> objectMap  = HttpUtil.doGetWithCookies("https://creditcard.cmbc.com.cn/fe/opencard/safeCode.gsp?rd=0.0" + RandomUtils.generateMixString(17) +"=");
                InputStream inputStream = (InputStream) objectMap.get("input");
                byte[] bytes = null;
                bytes = ImageUtils.input2byte(inputStream);
                String imgKey =  new UploadToQiniu(config, "qudan", "img", "images/bank/imgcode", RandomUtils.generateMixString(12), bytes).upload();
                String url =  ComUtils.addPrefixToImg(imgKey, config.getQiniuImageUrl());
                data.put("imgCodeUrl",url);
                data.put("cookieStr", objectMap.get("cookieStr").toString());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("触发验证码失败", e);
                ARE.addInfoError("imgCode.error", "获取验证码失败");
            }
        } else {
            ARE.addInfoError("imgCode.notNeed", "暂未对接该银行，请联系客服!");
        }

        return data;
    }

    public  Map<String,Object> mobileTrigger(ApiResponseEntity ARE, QueryBankRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(RB.getBankId() == null){
            ARE.addInfoError("bankId.isEmpty", "bankId不能为空");
            return null;
        }
        BankSimple bankSimple = bankMapperSelf.selectSimpleByProductId(RB.getBankId());
        if(StringUtils.isNotBlank(bankSimple.getLogo())){
            bankSimple.setLogo(ComUtils.addPrefixToImg(bankSimple.getLogo(), config.getQiniuImageUrl()));
        }
        if(bankSimple == null){
            ARE.addInfoError("bankId.isEmpty", "银行信息不存在");
            return null;
        }
        if(StringUtils.isBlank(bankSimple.getVerifyCodeLink())){
            ARE.addInfoError("bankId.mobileLinkBIsEmpty", "短信链接未配置");
            return null;
        }
        if(bankSimple.getVerifyCodeLink().indexOf("xyk.cebbank") > -1){ //光大
            if(StringUtils.isBlank(RB.getIdno())){
                ARE.addInfoError("idno.isEmpty", "身份证不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getName())){
                ARE.addInfoError("name.isEmpty", "姓名不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getCookieStr())){
                ARE.addInfoError("cookieStr.isEmpty", "cookieStr不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getImgCode())){
                ARE.addInfoError("imgCode.isEmpty", "imgCode不能为空");
                return null;
            }
            if(!triggerGDMobileVerify(ARE, RB)){
                return null;
            }
        } else if(bankSimple.getVerifyCodeLink().indexOf("creditcard.cmbc") > -1){
            if(StringUtils.isBlank(RB.getIdno())){
                ARE.addInfoError("idno.isEmpty", "身份证不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getName())){
                ARE.addInfoError("name.isEmpty", "姓名不能为空");
                return null;
            }
            if(StringUtils.isBlank(RB.getCookieStr())){
                ARE.addInfoError("cookieStr.isEmpty", "cookieStr不能为空");
                return null;
            }
        } else {
            ARE.addInfoError("imgCode.notNeed", "暂未对接该银行，请联系客服!");
        }
        return data;
    }

    public boolean triggerMSMobileVerify(ApiResponseEntity ARE, QueryBankRB RB){
        try {
            HttpResponse<String> response = Unirest.post("https://creditcard.cmbc.com.cn/fe/op_exchange_rate/verificationCode.gsp")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("Cookie", RB.getCookieStr())
                    .body("sKeyType=011&sCustId="+ RB.getIdno() +"&safeCode="+RB.getImgCode())
                    .asString();
            String info = response.getBody();
            log.info("返回消息!!!"+ info);
            Map<String,Object> map = QudanJsonUtils.parseJSONToMap(info);
            Object errorCode = map.get("errorCode");
            if(errorCode != null && Integer.valueOf(errorCode.toString()) > 0){
                ARE.addInfoError("imgCode.isError", map.get("errorMessage").toString());
                return false;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("触发验证码失败", ex);
            ARE.addInfoError("imgCode.isSystemError", "触发验证码失败");
            return false;
        }
        return true;
    }

    public boolean triggerGDMobileVerify(ApiResponseEntity ARE, QueryBankRB RB){
        try {
            HttpResponse<String> response = Unirest.post("https://xyk.cebbank.com/home/fz/application_get_activityCode.htm")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("Cookie", RB.getCookieStr())
                    .body("id_no="+ RB.getIdno() + "&ver_code="+ RB.getImgCode() +"&id_Type=A" + "&name="+RB.getName())
                    .asString();
            String info = response.getBody();
            log.info("返回消息!!!"+ info);
            Map<String,Object> map = QudanJsonUtils.parseJSONToMap(info);
            Object flag = map.get("flag");
            if(flag != null && flag.toString().equals("2")){
                ARE.addInfoError("imgCode.isError", map.get("msg").toString());
                return false;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("触发验证码失败", ex);
            ARE.addInfoError("imgCode.isSystemError", "触发验证码失败");
            return false;
        }
        return true;
    }

    public Map<String,Object> becomeVip(Integer userId, Integer vipId){
        Map<String,Object> data = Maps.newHashMap();

        Date date = new Date();
        User user = userMapperSelf.selectById(userId);
        VipConfig vipConfig = vipConfigMapper.selectByPrimaryKey(vipId);
        VipRecord vipRecord = null;
        if(user.getVipLevel() != null && user.getVipLevel() > 0) {
            vipRecord = vipMapperSelf.selectVipByUserId(userId);
        } else {
            vipRecord = new VipRecord();
        }
        boolean isVip = false;
        if(vipRecord.getEndTime() != null && vipRecord.getEndTime().compareTo(date) > 0){
            isVip = true;
        }

        TradeType tradeType = new TradeType();
        tradeType.setTradeType(QudanConstant.TRADE_TYPE.VIP_PAY.getType());
        tradeType.setIndirectType(null);
        tradeType.setPrice(vipConfig.getVipPrice());
        tradeType.setApplyId(null);
        tradeType.setCreateTime(date);
        tradeType.setModifyTime(date);
        tradeType.setSendStatus(2);
        tradeType.setStatus(2);
        tradeType.setBasePrice(vipConfig.getVipPrice());
        tradeType.setUserId(userId);
        tradeType.setAccount(null);
        tradeType.setVipLevel(vipConfig.getVipLevel().shortValue());
        tradeTypeMapper.insertSelective(tradeType);

        if(!isVip){
            vipRecord.setStartTime(date);
        }
        if(isVip && vipConfig.getVipLevel() < user.getVipLevel()){
            throw new RuntimeException("当期VIP等级已经超过需要购买的VIP等级");
        }
        if(isVip &&  vipConfig.getVipLevel() == user.getVipLevel()){
            vipRecord.setEndTime(DateUtil.getPerDate(vipRecord.getEndTime(), "d", vipConfig.getServiceDays()));
        } else {
            vipRecord.setEndTime(DateUtil.getPerDate(date, "d", vipConfig.getServiceDays()));
        }
        vipRecord.setUserId(userId);
        vipRecord.setTradeId(tradeType.getId());
        vipRecord.setVipConfigId(vipId);
        if(vipRecord.getId() == null){
            vipRecordMapper.insertSelective(vipRecord);
        } else {
            vipRecordMapper.updateByPrimaryKeySelective(vipRecord);
        }
        User user_update = new User();
        user_update.setId(userId);
        user_update.setVipLevel(vipConfig.getVipLevel());
        user_update.setVipName(vipConfig.getVipName());
        userMapperSelf.updateByPrimaryKeySelective(user_update);
        return data;
    }

    // 提现申请
    @Transactional
    public Map<String,Object> txRB(ApiResponseEntity ARE, TxRB txRB){
        Map<String,Object> data = Maps.newHashMap();
        Integer userId = ARE.getUserId();
        User user = userMapperSelf.selectById(userId);
        UserAccount account = userMapperSelf.selectAccountById(ARE.getUserId());
        if(StringUtils.isBlank(txRB.getAlipayNo())){
            ARE.addInfoError("alipayNo.isEmpty", "支付宝账号不能为空");
            return null;
        }
        if(user.getFinanceStatus() == null || user.getFinanceStatus() != 3){
            ARE.addInfoError("status.isEmpty", "未财务实名或者审核未通过");
            return null;
        }
        if(txRB.getTxPrice() == null || txRB.getTxPrice().compareTo(BigDecimal.ZERO) <= 0){
            ARE.addInfoError("price.isEmpty", "提现金额必须大于20");
            return null;
        }
        txRB.setTxPrice(txRB.getTxPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
        if(txRB.getTxPrice().compareTo(new BigDecimal("20")) < 0){
            ARE.addInfoError("price.overMax", "提现金额最少20");
            return null;
        }
        if(txRB.getTxPrice().compareTo(new BigDecimal("5000")) > 0){
            ARE.addInfoError("price.overMax", "提现金额不能超过5000");
            return null;
        }
        if(txRB.getTxPrice().compareTo(account.getAllowTx()) > 0){
            ARE.addInfoError("price.overLeft", "提现金额不能超过账户余额");
            return null;
        }
        Date date = new Date();
        Date start = DateFormatUtil.getMonthStart(date);
        Date end = DateFormatUtil.getMonthStart(date);
        int count = userMapperSelf.countTxByTime(start, end, userId);
        if(count > 3){
            ARE.addInfoError("count.overMax", "当月提现不能超过三次");
            return null;
        }
        TradeType tradeType = new TradeType();
        tradeType.setTradeType(QudanConstant.TRADE_TYPE.TI_XIAN.getType());
        tradeType.setIndirectType(null);
        tradeType.setPrice(txRB.getTxPrice());
        tradeType.setApplyId(null);
        tradeType.setCreateTime(date);
        tradeType.setModifyTime(date);
        tradeType.setSendStatus(1);
        tradeType.setStatus(1);
        tradeType.setBasePrice(txRB.getTxPrice());
        tradeType.setUserId(userId);
        tradeType.setAccount(account.getId());
        tradeType.setTxName(StringUtils.isNotBlank(txRB.getTxName())?txRB.getTxName():user.getRealname());
        tradeType.setTxAlipayNo(user.getAlipayNo());

        tradeTypeMapper.insertSelective(tradeType);
        account.setAllowTx(account.getAllowTx().subtract(tradeType.getPrice()));
        account.setTx(account.getTx().add(tradeType.getPrice()));
        userAccountMapper.updateByPrimaryKeySelective(account);
        data.put("id", tradeType.getId());
        return data;
    }
}
