package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class Apply {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Date createTime;

    private Date modifyTime;

    private String mobile;

    private String name;

    private String idNo;

    private Integer status;

    private Integer officialStatus;

    private Date lastOfficialQuery;

    private String rejectReason;

    private Integer salaryStatus;

    private String inviteCode;

    private String applyIdCode;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOfficialStatus() {
        return officialStatus;
    }

    public void setOfficialStatus(Integer officialStatus) {
        this.officialStatus = officialStatus;
    }

    public Date getLastOfficialQuery() {
        return lastOfficialQuery;
    }

    public void setLastOfficialQuery(Date lastOfficialQuery) {
        this.lastOfficialQuery = lastOfficialQuery;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public Integer getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(Integer salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public String getApplyIdCode() {
        return applyIdCode;
    }

    public void setApplyIdCode(String applyIdCode) {
        this.applyIdCode = applyIdCode == null ? null : applyIdCode.trim();
    }
}