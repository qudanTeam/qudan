package com.qudan.qingcloud.msqudan.entity;

import com.qudan.qingcloud.msqudan.util.PageUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表实体
 */
public class User extends PageUtils implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String userface;

    private Integer isenable;

    private String registerMobile;

    private String idNo;

    private String alipayNo;

    private Integer agentLevel;

    private Integer registerTime;

    private Date lastLoginTime;

    private Integer status;

    private Integer userType;

    private Date modifyTime;

    private Integer agentId;

    private String recommendInviteCode;

    private String inviteCode;

    private Long recommendInviteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface == null ? null : userface.trim();
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    public String getRegisterMobile() {
        return registerMobile;
    }

    public void setRegisterMobile(String registerMobile) {
        this.registerMobile = registerMobile == null ? null : registerMobile.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo == null ? null : alipayNo.trim();
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Integer registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getRecommendInviteCode() {
        return recommendInviteCode;
    }

    public void setRecommendInviteCode(String recommendInviteCode) {
        this.recommendInviteCode = recommendInviteCode == null ? null : recommendInviteCode.trim();
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public Long getRecommendInviteId() {
        return recommendInviteId;
    }

    public void setRecommendInviteId(Long recommendInviteId) {
        this.recommendInviteId = recommendInviteId;
    }
}