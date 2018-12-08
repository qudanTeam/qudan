package com.qudan.qingcloud.msqudan.util.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qudan.qingcloud.msqudan.util.responses.jackson.NullBigDecimalSerializer;

import java.math.BigDecimal;

public class UserVipVo {
    private String vipName;//VIP名字
    @JsonSerialize(nullsUsing = NullBigDecimalSerializer.class)
    private BigDecimal vipRate;//VIP加成比列
    private String vipExpireDate;//VIP过期时间
    @JsonSerialize(nullsUsing = NullBigDecimalSerializer.class)
    private BigDecimal vipRevenue; //VIP多赚的钱

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public BigDecimal getVipRate() {
        return vipRate;
    }

    public void setVipRate(BigDecimal vipRate) {
        this.vipRate = vipRate;
    }

    public String getVipExpireDate() {
        return vipExpireDate;
    }

    public void setVipExpireDate(String vipExpireDate) {
        this.vipExpireDate = vipExpireDate;
    }

    public BigDecimal getVipRevenue() {
        return vipRevenue;
    }

    public void setVipRevenue(BigDecimal vipRevenue) {
        this.vipRevenue = vipRevenue;
    }
}
