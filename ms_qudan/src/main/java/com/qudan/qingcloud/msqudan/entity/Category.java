package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class Category {
    private Integer id;

    private String name;

    private Date createTime;

    private Date modifyTime;

    private Integer categoryType;

    private String logo;

    private String getLink;

    private Byte needVerifyCode;

    private Byte needMobileVerifyCode;

    private String verifyCodeLink;

    private String mobileVerifyCodeLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getGetLink() {
        return getLink;
    }

    public void setGetLink(String getLink) {
        this.getLink = getLink == null ? null : getLink.trim();
    }

    public Byte getNeedVerifyCode() {
        return needVerifyCode;
    }

    public void setNeedVerifyCode(Byte needVerifyCode) {
        this.needVerifyCode = needVerifyCode;
    }

    public Byte getNeedMobileVerifyCode() {
        return needMobileVerifyCode;
    }

    public void setNeedMobileVerifyCode(Byte needMobileVerifyCode) {
        this.needMobileVerifyCode = needMobileVerifyCode;
    }

    public String getVerifyCodeLink() {
        return verifyCodeLink;
    }

    public void setVerifyCodeLink(String verifyCodeLink) {
        this.verifyCodeLink = verifyCodeLink == null ? null : verifyCodeLink.trim();
    }

    public String getMobileVerifyCodeLink() {
        return mobileVerifyCodeLink;
    }

    public void setMobileVerifyCodeLink(String mobileVerifyCodeLink) {
        this.mobileVerifyCodeLink = mobileVerifyCodeLink == null ? null : mobileVerifyCodeLink.trim();
    }
}