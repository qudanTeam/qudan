package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.Category;

public class CategorySqlProvider {

    public String insertSelective(Category record) {
        BEGIN();
        INSERT_INTO("category");
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCategoryType() != null) {
            VALUES("category_type", "#{categoryType,jdbcType=INTEGER}");
        }
        
        if (record.getLogo() != null) {
            VALUES("logo", "#{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getGetLink() != null) {
            VALUES("get_link", "#{getLink,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Category record) {
        BEGIN();
        UPDATE("category");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCategoryType() != null) {
            SET("category_type = #{categoryType,jdbcType=INTEGER}");
        }
        
        if (record.getLogo() != null) {
            SET("logo = #{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getGetLink() != null) {
            SET("get_link = #{getLink,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}