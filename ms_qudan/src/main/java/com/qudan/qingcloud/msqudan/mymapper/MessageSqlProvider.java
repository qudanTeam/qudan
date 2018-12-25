package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.Message;

public class MessageSqlProvider {

    public String insertSelective(Message record) {
        BEGIN();
        INSERT_INTO("message");
        
        if (record.getMsgLogo() != null) {
            VALUES("msg_logo", "#{msgLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getMsgTitle() != null) {
            VALUES("msg_title", "#{msgTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getMsgContent() != null) {
            VALUES("msg_content", "#{msgContent,jdbcType=VARCHAR}");
        }
        
        if (record.getMsgLink() != null) {
            VALUES("msg_link", "#{msgLink,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getIsUserDelete() != null) {
            VALUES("is_user_delete", "#{isUserDelete,jdbcType=INTEGER}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Message record) {
        BEGIN();
        UPDATE("message");
        
        if (record.getMsgLogo() != null) {
            SET("msg_logo = #{msgLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getMsgTitle() != null) {
            SET("msg_title = #{msgTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getMsgContent() != null) {
            SET("msg_content = #{msgContent,jdbcType=VARCHAR}");
        }
        
        if (record.getMsgLink() != null) {
            SET("msg_link = #{msgLink,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getIsUserDelete() != null) {
            SET("is_user_delete = #{isUserDelete,jdbcType=INTEGER}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}