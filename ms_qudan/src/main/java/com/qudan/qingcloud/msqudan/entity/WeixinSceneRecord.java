package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class WeixinSceneRecord {
    private Integer id;

    private String type;

    private String params;

    private Date createTime;

    private Date expireTime;

    private Integer expireSeconds;

    private String ticket;

    private Integer sceneId;

    private Integer qrType;

    private Integer applyUserId;

    private String qrAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getQrType() {
        return qrType;
    }

    public void setQrType(Integer qrType) {
        this.qrType = qrType;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getQrAddress() {
        return qrAddress;
    }

    public void setQrAddress(String qrAddress) {
        this.qrAddress = qrAddress == null ? null : qrAddress.trim();
    }
}