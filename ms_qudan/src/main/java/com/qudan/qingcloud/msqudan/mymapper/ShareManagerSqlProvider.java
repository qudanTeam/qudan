package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.ShareManager;

public class ShareManagerSqlProvider {

    public String insertSelective(ShareManager record) {
        BEGIN();
        INSERT_INTO("share_manager");
        
        if (record.getProductId() != null) {
            VALUES("product_id", "#{productId,jdbcType=INTEGER}");
        }
        
        if (record.getIsShow() != null) {
            VALUES("is_show", "#{isShow,jdbcType=INTEGER}");
        }
        
        if (record.getShareImg() != null) {
            VALUES("share_img", "#{shareImg,jdbcType=VARCHAR}");
        }
        
        if (record.getModifyTiime() != null) {
            VALUES("modify_tiime", "#{modifyTiime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSortVal() != null) {
            VALUES("sort_val", "#{sortVal,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(ShareManager record) {
        BEGIN();
        UPDATE("share_manager");
        
        if (record.getProductId() != null) {
            SET("product_id = #{productId,jdbcType=INTEGER}");
        }
        
        if (record.getIsShow() != null) {
            SET("is_show = #{isShow,jdbcType=INTEGER}");
        }
        
        if (record.getShareImg() != null) {
            SET("share_img = #{shareImg,jdbcType=VARCHAR}");
        }
        
        if (record.getModifyTiime() != null) {
            SET("modify_tiime = #{modifyTiime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSortVal() != null) {
            SET("sort_val = #{sortVal,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}