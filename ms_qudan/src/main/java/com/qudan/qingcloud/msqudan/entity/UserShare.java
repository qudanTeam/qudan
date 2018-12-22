package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class UserShare {
    private Integer id;

    private Integer userId;

    private Integer weixinSceneId;

    private Date shareTime;

    private Integer qrCodeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWeixinSceneId() {
        return weixinSceneId;
    }

    public void setWeixinSceneId(Integer weixinSceneId) {
        this.weixinSceneId = weixinSceneId;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Integer getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(Integer qrCodeId) {
        this.qrCodeId = qrCodeId;
    }
}