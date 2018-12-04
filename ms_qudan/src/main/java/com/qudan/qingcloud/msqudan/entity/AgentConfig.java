package com.qudan.qingcloud.msqudan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AgentConfig {
    private Integer id;

    private Integer level;

    private BigDecimal directRate;

    private BigDecimal relatedRate;

    private Integer inviteLimit;

    private Integer taskDoneLimit;

    private Integer shareLimit;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getDirectRate() {
        return directRate;
    }

    public void setDirectRate(BigDecimal directRate) {
        this.directRate = directRate;
    }

    public BigDecimal getRelatedRate() {
        return relatedRate;
    }

    public void setRelatedRate(BigDecimal relatedRate) {
        this.relatedRate = relatedRate;
    }

    public Integer getInviteLimit() {
        return inviteLimit;
    }

    public void setInviteLimit(Integer inviteLimit) {
        this.inviteLimit = inviteLimit;
    }

    public Integer getTaskDoneLimit() {
        return taskDoneLimit;
    }

    public void setTaskDoneLimit(Integer taskDoneLimit) {
        this.taskDoneLimit = taskDoneLimit;
    }

    public Integer getShareLimit() {
        return shareLimit;
    }

    public void setShareLimit(Integer shareLimit) {
        this.shareLimit = shareLimit;
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