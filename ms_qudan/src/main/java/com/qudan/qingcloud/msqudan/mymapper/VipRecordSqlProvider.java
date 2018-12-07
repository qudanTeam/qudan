package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.VipRecord;

public class VipRecordSqlProvider {

    public String insertSelective(VipRecord record) {
        BEGIN();
        INSERT_INTO("vip_record");
        
        if (record.getTradeId() != null) {
            VALUES("trade_id", "#{tradeId,jdbcType=INTEGER}");
        }
        
        if (record.getVipConfigId() != null) {
            VALUES("vip_config_id", "#{vipConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getStartTime() != null) {
            VALUES("start_time", "#{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getEndTime() != null) {
            VALUES("end_time", "#{endTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(VipRecord record) {
        BEGIN();
        UPDATE("vip_record");
        
        if (record.getTradeId() != null) {
            SET("trade_id = #{tradeId,jdbcType=INTEGER}");
        }
        
        if (record.getVipConfigId() != null) {
            SET("vip_config_id = #{vipConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getStartTime() != null) {
            SET("start_time = #{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getEndTime() != null) {
            SET("end_time = #{endTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }
}