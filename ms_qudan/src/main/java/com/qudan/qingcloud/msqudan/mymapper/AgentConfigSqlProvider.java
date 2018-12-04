package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.AgentConfig;

public class AgentConfigSqlProvider {

    public String insertSelective(AgentConfig record) {
        BEGIN();
        INSERT_INTO("agent_config");
        
        if (record.getLevel() != null) {
            VALUES("level", "#{level,jdbcType=INTEGER}");
        }
        
        if (record.getDirectRate() != null) {
            VALUES("direct_rate", "#{directRate,jdbcType=DECIMAL}");
        }
        
        if (record.getRelatedRate() != null) {
            VALUES("related_rate", "#{relatedRate,jdbcType=DECIMAL}");
        }
        
        if (record.getInviteLimit() != null) {
            VALUES("invite_limit", "#{inviteLimit,jdbcType=INTEGER}");
        }
        
        if (record.getTaskDoneLimit() != null) {
            VALUES("task_done_limit", "#{taskDoneLimit,jdbcType=INTEGER}");
        }
        
        if (record.getShareLimit() != null) {
            VALUES("share_limit", "#{shareLimit,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(AgentConfig record) {
        BEGIN();
        UPDATE("agent_config");
        
        if (record.getLevel() != null) {
            SET("level = #{level,jdbcType=INTEGER}");
        }
        
        if (record.getDirectRate() != null) {
            SET("direct_rate = #{directRate,jdbcType=DECIMAL}");
        }
        
        if (record.getRelatedRate() != null) {
            SET("related_rate = #{relatedRate,jdbcType=DECIMAL}");
        }
        
        if (record.getInviteLimit() != null) {
            SET("invite_limit = #{inviteLimit,jdbcType=INTEGER}");
        }
        
        if (record.getTaskDoneLimit() != null) {
            SET("task_done_limit = #{taskDoneLimit,jdbcType=INTEGER}");
        }
        
        if (record.getShareLimit() != null) {
            SET("share_limit = #{shareLimit,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}