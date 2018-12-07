package com.qudan.qingcloud.msqudan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class VipConfig {
    private Integer id;

    private BigDecimal addRate;

    private BigDecimal vipPrice;

    private Date startTime;

    private Integer serviceDays;

    private Date overTime;

    private String vipLogo;

    private Date createTime;

    private Date modifyTime;

    private Integer isenable;

    private Integer version;

    private String vipName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAddRate() {
        return addRate;
    }

    public void setAddRate(BigDecimal addRate) {
        this.addRate = addRate;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getServiceDays() {
        return serviceDays;
    }

    public void setServiceDays(Integer serviceDays) {
        this.serviceDays = serviceDays;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getVipLogo() {
        return vipLogo;
    }

    public void setVipLogo(String vipLogo) {
        this.vipLogo = vipLogo == null ? null : vipLogo.trim();
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

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName == null ? null : vipName.trim();
    }
}