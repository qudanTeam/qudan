package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class WeixinSceneScanLog {
    private Integer id;

    private Long userId;

    private String openId;

    private Date createTime;

    private Integer sceneId;

    private Short qrType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public Short getQrType() {
        return qrType;
    }

    public void setQrType(Short qrType) {
        this.qrType = qrType;
    }
}