package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.WeixinSceneRecord;

public class WeixinSceneRecordSqlProvider {

    public String insertSelective(WeixinSceneRecord record) {
        BEGIN();
        INSERT_INTO("weixin_scene_record");
        
        if (record.getType() != null) {
            VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getParams() != null) {
            VALUES("params", "#{params,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExpireTime() != null) {
            VALUES("expire_time", "#{expireTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExpireSeconds() != null) {
            VALUES("expire_seconds", "#{expireSeconds,jdbcType=INTEGER}");
        }
        
        if (record.getTicket() != null) {
            VALUES("ticket", "#{ticket,jdbcType=VARCHAR}");
        }
        
        if (record.getSceneId() != null) {
            VALUES("scene_id", "#{sceneId,jdbcType=INTEGER}");
        }
        
        if (record.getQrType() != null) {
            VALUES("qr_type", "#{qrType,jdbcType=INTEGER}");
        }
        
        if (record.getApplyUserId() != null) {
            VALUES("apply_user_id", "#{applyUserId,jdbcType=INTEGER}");
        }
        
        if (record.getQrAddress() != null) {
            VALUES("qr_address", "#{qrAddress,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(WeixinSceneRecord record) {
        BEGIN();
        UPDATE("weixin_scene_record");
        
        if (record.getType() != null) {
            SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getParams() != null) {
            SET("params = #{params,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExpireTime() != null) {
            SET("expire_time = #{expireTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExpireSeconds() != null) {
            SET("expire_seconds = #{expireSeconds,jdbcType=INTEGER}");
        }
        
        if (record.getTicket() != null) {
            SET("ticket = #{ticket,jdbcType=VARCHAR}");
        }
        
        if (record.getSceneId() != null) {
            SET("scene_id = #{sceneId,jdbcType=INTEGER}");
        }
        
        if (record.getQrType() != null) {
            SET("qr_type = #{qrType,jdbcType=INTEGER}");
        }
        
        if (record.getApplyUserId() != null) {
            SET("apply_user_id = #{applyUserId,jdbcType=INTEGER}");
        }
        
        if (record.getQrAddress() != null) {
            SET("qr_address = #{qrAddress,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}