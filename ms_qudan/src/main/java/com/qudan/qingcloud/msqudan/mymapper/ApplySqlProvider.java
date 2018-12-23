package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.Apply;

public class ApplySqlProvider {

    public String insertSelective(Apply record) {
        BEGIN();
        INSERT_INTO("apply");
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getProductId() != null) {
            VALUES("product_id", "#{productId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMobile() != null) {
            VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIdNo() != null) {
            VALUES("id_no", "#{idNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getOfficialStatus() != null) {
            VALUES("official_status", "#{officialStatus,jdbcType=INTEGER}");
        }
        
        if (record.getLastOfficialQuery() != null) {
            VALUES("last_official_query", "#{lastOfficialQuery,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRejectReason() != null) {
            VALUES("reject_reason", "#{rejectReason,jdbcType=VARCHAR}");
        }
        
        if (record.getSalaryStatus() != null) {
            VALUES("salary_status", "#{salaryStatus,jdbcType=INTEGER}");
        }
        
        if (record.getInviteCode() != null) {
            VALUES("invite_code", "#{inviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getApplyIdCode() != null) {
            VALUES("apply_id_code", "#{applyIdCode,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficialLimit() != null) {
            VALUES("official_limit", "#{officialLimit,jdbcType=DECIMAL}");
        }
        
        if (record.getOfficialExpire() != null) {
            VALUES("official_expire", "#{officialExpire,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficialTime() != null) {
            VALUES("official_time", "#{officialTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Apply record) {
        BEGIN();
        UPDATE("apply");
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getProductId() != null) {
            SET("product_id = #{productId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMobile() != null) {
            SET("mobile = #{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIdNo() != null) {
            SET("id_no = #{idNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getOfficialStatus() != null) {
            SET("official_status = #{officialStatus,jdbcType=INTEGER}");
        }
        
        if (record.getLastOfficialQuery() != null) {
            SET("last_official_query = #{lastOfficialQuery,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRejectReason() != null) {
            SET("reject_reason = #{rejectReason,jdbcType=VARCHAR}");
        }
        
        if (record.getSalaryStatus() != null) {
            SET("salary_status = #{salaryStatus,jdbcType=INTEGER}");
        }
        
        if (record.getInviteCode() != null) {
            SET("invite_code = #{inviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getApplyIdCode() != null) {
            SET("apply_id_code = #{applyIdCode,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficialLimit() != null) {
            SET("official_limit = #{officialLimit,jdbcType=DECIMAL}");
        }
        
        if (record.getOfficialExpire() != null) {
            SET("official_expire = #{officialExpire,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficialTime() != null) {
            SET("official_time = #{officialTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}