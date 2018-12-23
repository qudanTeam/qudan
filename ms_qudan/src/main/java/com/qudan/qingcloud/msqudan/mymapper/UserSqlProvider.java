package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.User;

public class UserSqlProvider {

    public String insertSelective(User record) {
        BEGIN();
        INSERT_INTO("user");
        
        if (record.getUsername() != null) {
            VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getUserface() != null) {
            VALUES("userface", "#{userface,jdbcType=VARCHAR}");
        }
        
        if (record.getIsenable() != null) {
            VALUES("isenable", "#{isenable,jdbcType=INTEGER}");
        }
        
        if (record.getRegisterMobile() != null) {
            VALUES("register_mobile", "#{registerMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getIdNo() != null) {
            VALUES("id_no", "#{idNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAlipayNo() != null) {
            VALUES("alipay_no", "#{alipayNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentLevel() != null) {
            VALUES("agent_level", "#{agentLevel,jdbcType=INTEGER}");
        }
        
        if (record.getRegisterTime() != null) {
            VALUES("register_time", "#{registerTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastLoginTime() != null) {
            VALUES("last_login_time", "#{lastLoginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getUserType() != null) {
            VALUES("user_type", "#{userType,jdbcType=INTEGER}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAgentId() != null) {
            VALUES("agent_id", "#{agentId,jdbcType=INTEGER}");
        }
        
        if (record.getRecommendInviteCode() != null) {
            VALUES("recommend_invite_code", "#{recommendInviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInviteCode() != null) {
            VALUES("invite_code", "#{inviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRecommendInviteId() != null) {
            VALUES("recommend_invite_id", "#{recommendInviteId,jdbcType=INTEGER}");
        }
        
        if (record.getVipName() != null) {
            VALUES("vip_name", "#{vipName,jdbcType=VARCHAR}");
        }
        
        if (record.getRealname() != null) {
            VALUES("realname", "#{realname,jdbcType=VARCHAR}");
        }
        
        if (record.getVipLevel() != null) {
            VALUES("vip_level", "#{vipLevel,jdbcType=INTEGER}");
        }
        
        if (record.getFinanceStatus() != null) {
            VALUES("finance_status", "#{financeStatus,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(User record) {
        BEGIN();
        UPDATE("user");
        
        if (record.getUsername() != null) {
            SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getUserface() != null) {
            SET("userface = #{userface,jdbcType=VARCHAR}");
        }
        
        if (record.getIsenable() != null) {
            SET("isenable = #{isenable,jdbcType=INTEGER}");
        }
        
        if (record.getRegisterMobile() != null) {
            SET("register_mobile = #{registerMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getIdNo() != null) {
            SET("id_no = #{idNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAlipayNo() != null) {
            SET("alipay_no = #{alipayNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentLevel() != null) {
            SET("agent_level = #{agentLevel,jdbcType=INTEGER}");
        }
        
        if (record.getRegisterTime() != null) {
            SET("register_time = #{registerTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastLoginTime() != null) {
            SET("last_login_time = #{lastLoginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getUserType() != null) {
            SET("user_type = #{userType,jdbcType=INTEGER}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAgentId() != null) {
            SET("agent_id = #{agentId,jdbcType=INTEGER}");
        }
        
        if (record.getRecommendInviteCode() != null) {
            SET("recommend_invite_code = #{recommendInviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInviteCode() != null) {
            SET("invite_code = #{inviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRecommendInviteId() != null) {
            SET("recommend_invite_id = #{recommendInviteId,jdbcType=INTEGER}");
        }
        
        if (record.getVipName() != null) {
            SET("vip_name = #{vipName,jdbcType=VARCHAR}");
        }
        
        if (record.getRealname() != null) {
            SET("realname = #{realname,jdbcType=VARCHAR}");
        }
        
        if (record.getVipLevel() != null) {
            SET("vip_level = #{vipLevel,jdbcType=INTEGER}");
        }
        
        if (record.getFinanceStatus() != null) {
            SET("finance_status = #{financeStatus,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}