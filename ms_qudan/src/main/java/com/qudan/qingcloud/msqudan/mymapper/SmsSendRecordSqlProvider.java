package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.SmsSendRecord;

public class SmsSendRecordSqlProvider {

    public String insertSelective(SmsSendRecord record) {
        BEGIN();
        INSERT_INTO("sms_send_record");
        
        if (record.getCode() != null) {
            VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getSendType() != null) {
            VALUES("send_type", "#{sendType,jdbcType=INTEGER}");
        }
        
        if (record.getMobile() != null) {
            VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInvalidTime() != null) {
            VALUES("invalid_time", "#{invalidTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsValid() != null) {
            VALUES("is_valid", "#{isValid,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(SmsSendRecord record) {
        BEGIN();
        UPDATE("sms_send_record");
        
        if (record.getCode() != null) {
            SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getSendType() != null) {
            SET("send_type = #{sendType,jdbcType=INTEGER}");
        }
        
        if (record.getMobile() != null) {
            SET("mobile = #{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInvalidTime() != null) {
            SET("invalid_time = #{invalidTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsValid() != null) {
            SET("is_valid = #{isValid,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}