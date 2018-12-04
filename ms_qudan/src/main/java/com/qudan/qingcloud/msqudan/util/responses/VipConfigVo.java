package com.qudan.qingcloud.msqudan.util.responses;

import java.math.BigDecimal;

public class VipConfigVo {
    private Integer vipName;
    private BigDecimal vipPrice;
    private BigDecimal addRate;
    private Integer serviceDay;
    private String vipLogo;

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getAddRate() {
        return addRate;
    }

    public void setAddRate(BigDecimal addRate) {
        this.addRate = addRate;
    }

    public Integer getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(Integer serviceDay) {
        this.serviceDay = serviceDay;
    }

    public String getVipLogo() {
        return vipLogo;
    }

    public void setVipLogo(String vipLogo) {
        this.vipLogo = vipLogo;
    }
}
