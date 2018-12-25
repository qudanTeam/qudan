package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class Message {
    private Integer id;

    private String msgLogo;

    private String msgTitle;

    private String msgContent;

    private String msgLink;

    private Date createTime;

    private Integer userId;

    private Integer isUserDelete;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgLogo() {
        return msgLogo;
    }

    public void setMsgLogo(String msgLogo) {
        this.msgLogo = msgLogo == null ? null : msgLogo.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getMsgLink() {
        return msgLink;
    }

    public void setMsgLink(String msgLink) {
        this.msgLink = msgLink == null ? null : msgLink.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsUserDelete() {
        return isUserDelete;
    }

    public void setIsUserDelete(Integer isUserDelete) {
        this.isUserDelete = isUserDelete;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}