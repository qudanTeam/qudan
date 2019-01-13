package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.BankQuery;

public class BankQuerySqlProvider {

    public String insertSelective(BankQuery record) {
        BEGIN();
        INSERT_INTO("bank_query");
        
        if (record.getCardCat() != null) {
            VALUES("card_cat", "#{cardCat,jdbcType=VARCHAR}");
        }
        
        if (record.getBankId() != null) {
            VALUES("bank_id", "#{bankId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getJinjianDate() != null) {
            VALUES("jinjian_date", "#{jinjianDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCardStatus() != null) {
            VALUES("card_status", "#{cardStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getBankName() != null) {
            VALUES("bank_name", "#{bankName,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(BankQuery record) {
        BEGIN();
        UPDATE("bank_query");
        
        if (record.getCardCat() != null) {
            SET("card_cat = #{cardCat,jdbcType=VARCHAR}");
        }
        
        if (record.getBankId() != null) {
            SET("bank_id = #{bankId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getJinjianDate() != null) {
            SET("jinjian_date = #{jinjianDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCardStatus() != null) {
            SET("card_status = #{cardStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getBankName() != null) {
            SET("bank_name = #{bankName,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}