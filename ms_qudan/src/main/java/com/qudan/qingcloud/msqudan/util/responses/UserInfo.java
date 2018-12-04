package com.qudan.qingcloud.msqudan.util.responses;

import com.qudan.qingcloud.msqudan.entity.User;

import java.math.BigDecimal;

public class UserInfo extends User {
    private Boolean isAgent;
    private Boolean isVip;
    private UserAgentVo agent;
    private UserVipVo vip;
    private BigDecimal blance;

    public Boolean getAgent() {
        return isAgent;
    }

    public void setAgent(UserAgentVo agent) {
        this.agent = agent;
    }

    public void setAgent(Boolean agent) {
        isAgent = agent;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(UserVipVo vip) {
        this.vip = vip;
    }

    public BigDecimal getBlance() {
        return blance;
    }

    public void setBlance(BigDecimal blance) {
        this.blance = blance;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }
}
