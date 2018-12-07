package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.VipConfig;

public class VipConfigSqlProvider {

    public String insertSelective(VipConfig record) {
        BEGIN();
        INSERT_INTO("vip_config");
        
        if (record.getId() != null) {
            VALUES("\" id\"", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAddRate() != null) {
            VALUES("add_rate", "#{addRate,jdbcType=DECIMAL}");
        }
        
        if (record.getVipPrice() != null) {
            VALUES("vip_price", "#{vipPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getStartTime() != null) {
            VALUES("start_time", "#{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getServiceDays() != null) {
            VALUES("service_days", "#{serviceDays,jdbcType=INTEGER}");
        }
        
        if (record.getOverTime() != null) {
            VALUES("over_time", "#{overTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVipLogo() != null) {
            VALUES("vip_logo", "#{vipLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsenable() != null) {
            VALUES("isenable", "#{isenable,jdbcType=INTEGER}");
        }
        
        if (record.getVersion() != null) {
            VALUES("version", "#{version,jdbcType=INTEGER}");
        }
        
        if (record.getVipName() != null) {
            VALUES("vip_name", "#{vipName,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(VipConfig record) {
        BEGIN();
        UPDATE("vip_config");
        
        if (record.getAddRate() != null) {
            SET("add_rate = #{addRate,jdbcType=DECIMAL}");
        }
        
        if (record.getVipPrice() != null) {
            SET("vip_price = #{vipPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getStartTime() != null) {
            SET("start_time = #{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getServiceDays() != null) {
            SET("service_days = #{serviceDays,jdbcType=INTEGER}");
        }
        
        if (record.getOverTime() != null) {
            SET("over_time = #{overTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVipLogo() != null) {
            SET("vip_logo = #{vipLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsenable() != null) {
            SET("isenable = #{isenable,jdbcType=INTEGER}");
        }
        
        if (record.getVersion() != null) {
            SET("version = #{version,jdbcType=INTEGER}");
        }
        
        if (record.getVipName() != null) {
            SET("vip_name = #{vipName,jdbcType=VARCHAR}");
        }
        
        WHERE("\" id\" = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}