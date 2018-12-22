package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.UserShareQrCode;

public class UserShareQrCodeSqlProvider {

    public String insertSelective(UserShareQrCode record) {
        BEGIN();
        INSERT_INTO("user_share_qr_code");
        
        if (record.getShareType() != null) {
            VALUES("share_type", "#{shareType,jdbcType=INTEGER}");
        }
        
        if (record.getImgUrl() != null) {
            VALUES("img_url", "#{imgUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getPid() != null) {
            VALUES("pid", "#{pid,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getAddressUrl() != null) {
            VALUES("address_url", "#{addressUrl,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserShareQrCode record) {
        BEGIN();
        UPDATE("user_share_qr_code");
        
        if (record.getShareType() != null) {
            SET("share_type = #{shareType,jdbcType=INTEGER}");
        }
        
        if (record.getImgUrl() != null) {
            SET("img_url = #{imgUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getPid() != null) {
            SET("pid = #{pid,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getAddressUrl() != null) {
            SET("address_url = #{addressUrl,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}