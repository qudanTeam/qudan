package com.qudan.qingcloud.msqudan.util.responses;

import java.math.BigDecimal;

public class RankVo {
    private BigDecimal revenue;
    private String name;
    private String logo;

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public RankVo(BigDecimal revenue, String name, String logo) {
        this.revenue = revenue;
        this.name = name;
        this.logo = logo;
    }
}
