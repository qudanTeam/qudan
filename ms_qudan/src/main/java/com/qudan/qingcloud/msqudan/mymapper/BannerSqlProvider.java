package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.Banner;

public class BannerSqlProvider {

    public String insertSelective(Banner record) {
        BEGIN();
        INSERT_INTO("banner");
        
        if (record.getTitle() != null) {
            VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getLink() != null) {
            VALUES("link", "#{link,jdbcType=VARCHAR}");
        }
        
        if (record.getImg() != null) {
            VALUES("img", "#{img,jdbcType=VARCHAR}");
        }
        
        if (record.getSortVal() != null) {
            VALUES("sort_val", "#{sortVal,jdbcType=INTEGER}");
        }
        
        if (record.getIsShow() != null) {
            VALUES("is_show", "#{isShow,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTiime() != null) {
            VALUES("modify_tiime", "#{modifyTiime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Banner record) {
        BEGIN();
        UPDATE("banner");
        
        if (record.getTitle() != null) {
            SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getLink() != null) {
            SET("link = #{link,jdbcType=VARCHAR}");
        }
        
        if (record.getImg() != null) {
            SET("img = #{img,jdbcType=VARCHAR}");
        }
        
        if (record.getSortVal() != null) {
            SET("sort_val = #{sortVal,jdbcType=INTEGER}");
        }
        
        if (record.getIsShow() != null) {
            SET("is_show = #{isShow,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTiime() != null) {
            SET("modify_tiime = #{modifyTiime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}