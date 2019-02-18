package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.TradeType;

public class TradeTypeSqlProvider {

    public String insertSelective(TradeType record) {
        BEGIN();
        INSERT_INTO("trade_type");
        
        if (record.getTradeType() != null) {
            VALUES("trade_type", "#{tradeType,jdbcType=INTEGER}");
        }
        
        if (record.getApplyId() != null) {
            VALUES("apply_id", "#{applyId,jdbcType=INTEGER}");
        }
        
        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getAccount() != null) {
            VALUES("account", "#{account,jdbcType=INTEGER}");
        }
        
        if (record.getIndirectType() != null) {
            VALUES("indirect_type", "#{indirectType,jdbcType=INTEGER}");
        }
        
        if (record.getSendStatus() != null) {
            VALUES("send_status", "#{sendStatus,jdbcType=INTEGER}");
        }
        
        if (record.getAuditTime() != null) {
            VALUES("audit_time", "#{auditTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSendTime() != null) {
            VALUES("send_time", "#{sendTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVipRate() != null) {
            VALUES("vip_rate", "#{vipRate,jdbcType=DECIMAL}");
        }
        
        if (record.getVipLevel() != null) {
            VALUES("vip_level", "#{vipLevel,jdbcType=SMALLINT}");
        }
        
        if (record.getBasePrice() != null) {
            VALUES("base_price", "#{basePrice,jdbcType=DECIMAL}");
        }
        
        if (record.getRelationUserId() != null) {
            VALUES("relation_user_id", "#{relationUserId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getVipPrice() != null) {
            VALUES("vip_price", "#{vipPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getRejectReason() != null) {
            VALUES("reject_reason", "#{rejectReason,jdbcType=VARCHAR}");
        }
        
        if (record.getTxName() != null) {
            VALUES("tx_name", "#{txName,jdbcType=VARCHAR}");
        }
        
        if (record.getTxAlipayNo() != null) {
            VALUES("tx_alipay_no", "#{txAlipayNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentLevel() != null) {
            VALUES("agent_level", "#{agentLevel,jdbcType=INTEGER}");
        }
        
        if (record.getAgentRate() != null) {
            VALUES("agent_rate", "#{agentRate,jdbcType=DECIMAL}");
        }
        
        if (record.getRemark() != null) {
            VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getProductId() != null) {
            VALUES("product_id", "#{productId,jdbcType=INTEGER}");
        }
        
        if (record.getPlatformPrice() != null) {
            VALUES("platform_price", "#{platformPrice,jdbcType=DECIMAL}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(TradeType record) {
        BEGIN();
        UPDATE("trade_type");
        
        if (record.getTradeType() != null) {
            SET("trade_type = #{tradeType,jdbcType=INTEGER}");
        }
        
        if (record.getApplyId() != null) {
            SET("apply_id = #{applyId,jdbcType=INTEGER}");
        }
        
        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getAccount() != null) {
            SET("account = #{account,jdbcType=INTEGER}");
        }
        
        if (record.getIndirectType() != null) {
            SET("indirect_type = #{indirectType,jdbcType=INTEGER}");
        }
        
        if (record.getSendStatus() != null) {
            SET("send_status = #{sendStatus,jdbcType=INTEGER}");
        }
        
        if (record.getAuditTime() != null) {
            SET("audit_time = #{auditTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSendTime() != null) {
            SET("send_time = #{sendTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVipRate() != null) {
            SET("vip_rate = #{vipRate,jdbcType=DECIMAL}");
        }
        
        if (record.getVipLevel() != null) {
            SET("vip_level = #{vipLevel,jdbcType=SMALLINT}");
        }
        
        if (record.getBasePrice() != null) {
            SET("base_price = #{basePrice,jdbcType=DECIMAL}");
        }
        
        if (record.getRelationUserId() != null) {
            SET("relation_user_id = #{relationUserId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getVipPrice() != null) {
            SET("vip_price = #{vipPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getRejectReason() != null) {
            SET("reject_reason = #{rejectReason,jdbcType=VARCHAR}");
        }
        
        if (record.getTxName() != null) {
            SET("tx_name = #{txName,jdbcType=VARCHAR}");
        }
        
        if (record.getTxAlipayNo() != null) {
            SET("tx_alipay_no = #{txAlipayNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentLevel() != null) {
            SET("agent_level = #{agentLevel,jdbcType=INTEGER}");
        }
        
        if (record.getAgentRate() != null) {
            SET("agent_rate = #{agentRate,jdbcType=DECIMAL}");
        }
        
        if (record.getRemark() != null) {
            SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getProductId() != null) {
            SET("product_id = #{productId,jdbcType=INTEGER}");
        }
        
        if (record.getPlatformPrice() != null) {
            SET("platform_price = #{platformPrice,jdbcType=DECIMAL}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}