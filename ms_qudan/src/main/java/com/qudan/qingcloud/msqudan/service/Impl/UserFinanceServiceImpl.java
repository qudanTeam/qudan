package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.qudan.qingcloud.msqudan.config.QudanConstant;
import com.qudan.qingcloud.msqudan.controller.UserController;
import com.qudan.qingcloud.msqudan.entity.*;
import com.qudan.qingcloud.msqudan.mymapper.TradeTypeMapper;
import com.qudan.qingcloud.msqudan.mymapper.UserAccountMapper;
import com.qudan.qingcloud.msqudan.mymapper.VipConfigMapper;
import com.qudan.qingcloud.msqudan.mymapper.VipRecordMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.BankMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.UserMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.VipMapperSelf;
import com.qudan.qingcloud.msqudan.util.DateFormatUtil;
import com.qudan.qingcloud.msqudan.util.DateUtil;
import com.qudan.qingcloud.msqudan.util.requestBody.QueryBankRB;
import com.qudan.qingcloud.msqudan.util.requestBody.TxRB;
import com.qudan.qingcloud.msqudan.util.requestBody.UserLoginRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.BankSimple;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
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
        if(bankSimple == null){
            ARE.addInfoError("bankId.isNotExist", "银行信息不存在");
            return null;
        }
        if(bankSimple.getVerifyCodeLink().indexOf("xyk.cebbank") > -1) { //光大
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
        }
        data.put("status", 3);
        return data;
    }

    public  Map<String,Object> mobileTrigger(ApiResponseEntity ARE, QueryBankRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(RB.getBankId() == null){
            ARE.addInfoError("bankId.isEmpty", "bankId不能为空");
            return null;
        }
        BankSimple bankSimple = bankMapperSelf.selectSimpleByProductId(RB.getBankId());
        if(bankSimple == null){
            ARE.addInfoError("bankId.isEmpty", "银行信息不存在");
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
            if(!triggerGDMobileVerify(RB)){
                ARE.addInfoError("mobileCode.error", "验证码获取错误");
                return null;
            }
        }
        return data;
    }

    public boolean triggerGDMobileVerify(QueryBankRB RB){
        try {
            HttpResponse<String> response = Unirest.post("https://xyk.cebbank.com/home/fz/application_get_activityCode.htm")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("Cookie", RB.getCookieStr())
                    .body("id_no="+ RB.getIdno() + "&ver_code="+ RB.getImgCode() +"&id_Type=A" + "&name="+RB.getName())
                    .asString();
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("触发验证码失败", ex);
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
            ARE.addInfoError("price.isEmpty", "提现金额必须大于0");
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
            ARE.addInfoError("count.overMax", "当前提现不能超过三次");
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
        return data;
    }
}
