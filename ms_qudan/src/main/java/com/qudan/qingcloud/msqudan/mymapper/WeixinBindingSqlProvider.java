package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.WeixinBinding;

public class WeixinBindingSqlProvider {

    public String insertSelective(WeixinBinding record) {
        BEGIN();
        INSERT_INTO("weixin_binding");
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getOpenid() != null) {
            VALUES("openid", "#{openid,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionid() != null) {
            VALUES("unionid", "#{unionid,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatName() != null) {
            VALUES("wechat_name", "#{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatLogo() != null) {
            VALUES("wechat_logo", "#{wechatLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(WeixinBinding record) {
        BEGIN();
        UPDATE("weixin_binding");
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getOpenid() != null) {
            SET("openid = #{openid,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionid() != null) {
            SET("unionid = #{unionid,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatName() != null) {
            SET("wechat_name = #{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatLogo() != null) {
            SET("wechat_logo = #{wechatLogo,jdbcType=VARCHAR}");
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