package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.qudan.qingcloud.msqudan.config.QudanConstant;
import com.qudan.qingcloud.msqudan.entity.TradeType;
import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.entity.UserAccount;
import com.qudan.qingcloud.msqudan.mymapper.TradeTypeMapper;
import com.qudan.qingcloud.msqudan.mymapper.UserAccountMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.UserMapperSelf;
import com.qudan.qingcloud.msqudan.util.DateFormatUtil;
import com.qudan.qingcloud.msqudan.util.requestBody.TxRB;
import com.qudan.qingcloud.msqudan.util.requestBody.UserLoginRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Service
public class UserFinanceServiceImpl {

    @Autowired
    UserMapperSelf userMapperSelf;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TradeTypeMapper tradeTypeMapper;

    //TODO 商品搜索记录
    //TODO 记录商品搜索记录
    //TODO 成为VIP

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