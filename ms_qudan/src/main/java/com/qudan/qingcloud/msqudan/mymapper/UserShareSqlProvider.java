package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.UserShare;

public class UserShareSqlProvider {

    public String insertSelective(UserShare record) {
        BEGIN();
        INSERT_INTO("user_share");
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getWeixinSceneId() != null) {
            VALUES("weixin_scene_id", "#{weixinSceneId,jdbcType=INTEGER}");
        }
        
        if (record.getShareTime() != null) {
            VALUES("share_time", "#{shareTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserShare record) {
        BEGIN();
        UPDATE("user_share");
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getWeixinSceneId() != null) {
            SET("weixin_scene_id = #{weixinSceneId,jdbcType=INTEGER}");
        }
        
        if (record.getShareTime() != null) {
            SET("share_time = #{shareTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}