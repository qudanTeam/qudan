package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.WeixinSceneScanLog;

public class WeixinSceneScanLogSqlProvider {

    public String insertSelective(WeixinSceneScanLog record) {
        BEGIN();
        INSERT_INTO("weixin_scene_scan_log");
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getOpenId() != null) {
            VALUES("open_id", "#{openId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSceneId() != null) {
            VALUES("scene_id", "#{sceneId,jdbcType=INTEGER}");
        }
        
        if (record.getQrType() != null) {
            VALUES("qr_type", "#{qrType,jdbcType=SMALLINT}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(WeixinSceneScanLog record) {
        BEGIN();
        UPDATE("weixin_scene_scan_log");
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getOpenId() != null) {
            SET("open_id = #{openId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSceneId() != null) {
            SET("scene_id = #{sceneId,jdbcType=INTEGER}");
        }
        
        if (record.getQrType() != null) {
            SET("qr_type = #{qrType,jdbcType=SMALLINT}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}