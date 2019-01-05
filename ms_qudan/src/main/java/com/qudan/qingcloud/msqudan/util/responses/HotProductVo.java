package com.qudan.qingcloud.msqudan.util.responses;

import java.math.BigDecimal;

public class HotProductVo {
    private Integer productId;
    private String productName;
    private Integer productType;
    private String logo;
    private Integer sortVal;
    private BigDecimal salary;
    private Integer recommendCount;
    private BigDecimal salaryAmount;
    private BigDecimal commission;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(Integer recommendCount) {
        this.recommendCount = recommendCount;
    }

    public BigDecimal getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(BigDecimal salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
