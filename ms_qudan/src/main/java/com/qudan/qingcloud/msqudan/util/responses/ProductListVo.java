package com.qudan.qingcloud.msqudan.util.responses;

import java.math.BigDecimal;

public class ProductListVo {
    private Integer productId;
    private String productName;
    private Integer productType;
    private String logo;
    private BigDecimal baseSalary;
    private BigDecimal commission;
    private String specialTag;
    private String specialTxt;
    private Integer amountLine;
    private BigDecimal allowRate;
    private Integer applyNum;
    private String expireUnit;
    private Integer expireBegin;
    private Integer expireEnd;

    private BigDecimal dayRate;
    private BigDecimal monthRate;

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

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getSpecialTag() {
        return specialTag;
    }

    public void setSpecialTag(String specialTag) {
        this.specialTag = specialTag;
    }

    public String getSpecialTxt() {
        return specialTxt;
    }

    public void setSpecialTxt(String specialTxt) {
        this.specialTxt = specialTxt;
    }

    public Integer getAmountLine() {
        return amountLine;
    }

    public void setAmountLine(Integer amountLine) {
        this.amountLine = amountLine;
    }

    public BigDecimal getAllowRate() {
        return allowRate;
    }

    public void setAllowRate(BigDecimal allowRate) {
        this.allowRate = allowRate;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getExpireUnit() {
        return expireUnit;
    }

    public void setExpireUnit(String expireUnit) {
        this.expireUnit = expireUnit;
    }

    public Integer getExpireBegin() {
        return expireBegin;
    }

    public void setExpireBegin(Integer expireBegin) {
        this.expireBegin = expireBegin;
    }

    public Integer getExpireEnd() {
        return expireEnd;
    }

    public void setExpireEnd(Integer expireEnd) {
        this.expireEnd = expireEnd;
    }

    public BigDecimal getDayRate() {
        return dayRate;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    public BigDecimal getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(BigDecimal monthRate) {
        this.monthRate = monthRate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
