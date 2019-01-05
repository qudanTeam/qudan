package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class Agent {
    private Integer id;

    private Integer userId;

    private Integer level;

    private Date beignAgentTime;

    private Date createTime;

    private Date modifyTime;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getBeignAgentTime() {
        return beignAgentTime;
    }

    public void setBeignAgentTime(Date beignAgentTime) {
        this.beignAgentTime = beignAgentTime;
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
}