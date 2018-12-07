package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class VipRecord {
    private Long id;

    private Integer tradeId;

    private Integer vipConfigId;

    private Date startTime;

    private Integer userId;

    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getVipConfigId() {
        return vipConfigId;
    }

    public void setVipConfigId(Integer vipConfigId) {
        this.vipConfigId = vipConfigId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}