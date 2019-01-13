package com.qudan.qingcloud.msqudan.util.responses;

public class ProductSimple {
    private Integer hasLink;
    private Integer bankId;
    private String bankName;
    private Integer productId;
    private String getLink;
    private Integer needVerifyCode;
    private Integer needMobileVerifyCode;
    private String verifyCodeLink;
    private String mobileVerifyCodeLink;
    private String productLink;
    private String logo;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getGetLink() {
        return getLink;
    }

    public void setGetLink(String getLink) {
        this.getLink = getLink;
    }

    public Integer getNeedVerifyCode() {
        return needVerifyCode;
    }

    public void setNeedVerifyCode(Integer needVerifyCode) {
        this.needVerifyCode = needVerifyCode;
    }

    public Integer getNeedMobileVerifyCode() {
        return needMobileVerifyCode;
    }

    public void setNeedMobileVerifyCode(Integer needMobileVerifyCode) {
        this.needMobileVerifyCode = needMobileVerifyCode;
    }

    public String getVerifyCodeLink() {
        return verifyCodeLink;
    }

    public void setVerifyCodeLink(String verifyCodeLink) {
        this.verifyCodeLink = verifyCodeLink;
    }

    public String getMobileVerifyCodeLink() {
        return mobileVerifyCodeLink;
    }

    public void setMobileVerifyCodeLink(String mobileVerifyCodeLink) {
        this.mobileVerifyCodeLink = mobileVerifyCodeLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getHasLink() {
        return hasLink;
    }

    public void setHasLink(Integer hasLink) {
        this.hasLink = hasLink;
    }
}
