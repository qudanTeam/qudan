package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.UserAccount;

public class UserAccountSqlProvider {

    public String insertSelective(UserAccount record) {
        BEGIN();
        INSERT_INTO("user_account");
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getBlance() != null) {
            VALUES("blance", "#{blance,jdbcType=DECIMAL}");
        }
        
        if (record.getAllowTx() != null) {
            VALUES("allow_tx", "#{allowTx,jdbcType=DECIMAL}");
        }
        
        if (record.getTx() != null) {
            VALUES("tx", "#{tx,jdbcType=DECIMAL}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserAccount record) {
        BEGIN();
        UPDATE("user_account");
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getBlance() != null) {
            SET("blance = #{blance,jdbcType=DECIMAL}");
        }
        
        if (record.getAllowTx() != null) {
            SET("allow_tx = #{allowTx,jdbcType=DECIMAL}");
        }
        
        if (record.getTx() != null) {
            SET("tx = #{tx,jdbcType=DECIMAL}");
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