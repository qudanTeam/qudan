package com.qudan.qingcloud.msqudan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TradeType {
    private Integer id;

    private Integer tradeType;

    private Integer applyId;

    private BigDecimal price;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    private Integer account;

    private Integer indirectType;

    private Integer sendStatus;

    private Date auditTime;

    private Date sendTime;

    private BigDecimal vipRate;

    private Short vipLevel;

    private BigDecimal basePrice;

    private Integer relationUserId;

    private Integer userId;

    private BigDecimal vipPrice;

    private String rejectReason;

    private String txName;

    private String txAlipayNo;

    private Integer agentLevel;

    private BigDecimal agentRate;

    private String remark;

    private Integer productId;

    private BigDecimal platformPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getIndirectType() {
        return indirectType;
    }

    public void setIndirectType(Integer indirectType) {
        this.indirectType = indirectType;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public BigDecimal getVipRate() {
        return vipRate;
    }

    public void setVipRate(BigDecimal vipRate) {
        this.vipRate = vipRate;
    }

    public Short getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Short vipLevel) {
        this.vipLevel = vipLevel;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getRelationUserId() {
        return relationUserId;
    }

    public void setRelationUserId(Integer relationUserId) {
        this.relationUserId = relationUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public String getTxName() {
        return txName;
    }

    public void setTxName(String txName) {
        this.txName = txName == null ? null : txName.trim();
    }

    public String getTxAlipayNo() {
        return txAlipayNo;
    }

    public void setTxAlipayNo(String txAlipayNo) {
        this.txAlipayNo = txAlipayNo == null ? null : txAlipayNo.trim();
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public BigDecimal getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(BigDecimal agentRate) {
        this.agentRate = agentRate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(BigDecimal platformPrice) {
        this.platformPrice = platformPrice;
    }
}