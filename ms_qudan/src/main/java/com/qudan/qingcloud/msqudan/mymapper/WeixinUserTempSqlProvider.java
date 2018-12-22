package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.WeixinUserTemp;

public class WeixinUserTempSqlProvider {

    public String insertSelective(WeixinUserTemp record) {
        BEGIN();
        INSERT_INTO("weixin_user_temp");
        
        if (record.getOpenid() != null) {
            VALUES("openid", "#{openid,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionid() != null) {
            VALUES("unionid", "#{unionid,jdbcType=VARCHAR}");
        }
        
        if (record.getNickname() != null) {
            VALUES("nickname", "#{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getHeadImgUrl() != null) {
            VALUES("head_img_url", "#{headImgUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            VALUES("sex", "#{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            VALUES("country", "#{country,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            VALUES("province", "#{province,jdbcType=VARCHAR}");
        }
        
        if (record.getLanguage() != null) {
            VALUES("language", "#{language,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(WeixinUserTemp record) {
        BEGIN();
        UPDATE("weixin_user_temp");
        
        if (record.getOpenid() != null) {
            SET("openid = #{openid,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionid() != null) {
            SET("unionid = #{unionid,jdbcType=VARCHAR}");
        }
        
        if (record.getNickname() != null) {
            SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getHeadImgUrl() != null) {
            SET("head_img_url = #{headImgUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            SET("sex = #{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            SET("country = #{country,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            SET("province = #{province,jdbcType=VARCHAR}");
        }
        
        if (record.getLanguage() != null) {
            SET("language = #{language,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}