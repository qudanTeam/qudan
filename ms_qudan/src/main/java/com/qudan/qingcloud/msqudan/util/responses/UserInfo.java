package com.qudan.qingcloud.msqudan.util.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.util.responses.jackson.NullBigDecimalSerializer;

import java.math.BigDecimal;

public class UserInfo extends User {
    private Boolean isAgent;
    private Boolean isVip;
    @JsonSerialize(nullsUsing = NullBigDecimalSerializer.class)
    private BigDecimal blance; //可用余额
    @JsonSerialize(nullsUsing = NullBigDecimalSerializer.class)
    private BigDecimal allowTx; //可提现
    @JsonSerialize(nullsUsing = NullBigDecimalSerializer.class)
    private BigDecimal waitSettle; //待结算
    @JsonSerialize(nullsUsing = NullBigDecimalSerializer.class)
    private BigDecimal txGoing; //提现中
    private UserAgentVo agent;
    private UserVipVo vip;
    private String recommendUsername;


    public Boolean getIsAgent() {
        return isAgent;
    }

    public void setAgent(UserAgentVo agent) {
        this.agent = agent;
    }

    public void setIsAgent(Boolean agent) {
        isAgent = agent;
    }

    public Boolean getIsVip() {
        return isVip;
    }

    public void setVip(UserVipVo vip) {
        this.vip = vip;
    }

    public void setIsVip(Boolean vip) {
        isVip = vip;
    }

    public BigDecimal getBlance() {
        return blance;
    }

    public void setBlance(BigDecimal blance) {
        this.blance = blance;
    }

    public BigDecimal getAllowTx() {
        return allowTx;
    }

    public void setAllowTx(BigDecimal allowTx) {
        this.allowTx = allowTx;
    }

    public BigDecimal getWaitSettle() {
        return waitSettle;
    }

    public void setWaitSettle(BigDecimal waitSettle) {
        this.waitSettle = waitSettle;
    }

    public UserAgentVo getAgent() {
        return agent;
    }

    public UserVipVo getVip() {
        return vip;
    }

    public BigDecimal getTxGoing() {
        return txGoing;
    }

    public void setTxGoing(BigDecimal txGoing) {
        this.txGoing = txGoing;
    }

    public String getRecommendUsername() {
        return recommendUsername;
    }

    public void setRecommendUsername(String recommendUsername) {
        this.recommendUsername = recommendUsername;
    }
}
