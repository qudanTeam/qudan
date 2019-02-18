package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.PosApplyExt;

public class PosApplyExtSqlProvider {

    public String insertSelective(PosApplyExt record) {
        BEGIN();
        INSERT_INTO("pos_apply_ext");
        
        if (record.getExpressName() != null) {
            VALUES("express_name", "#{expressName,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressNo() != null) {
            VALUES("express_no", "#{expressNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getApplyId() != null) {
            VALUES("apply_id", "#{applyId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getApplyMobile() != null) {
            VALUES("apply_mobile", "#{applyMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getProductId() != null) {
            VALUES("product_id", "#{productId,jdbcType=INTEGER}");
        }
        
        if (record.getPosNo() != null) {
            VALUES("pos_no", "#{posNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayType() != null) {
            VALUES("pay_type", "#{payType,jdbcType=INTEGER}");
        }
        
        if (record.getPayPrice() != null) {
            VALUES("pay_price", "#{payPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getPayDeposit() != null) {
            VALUES("pay_deposit", "#{payDeposit,jdbcType=DECIMAL}");
        }
        
        if (record.getAddress() != null) {
            VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getRegion() != null) {
            VALUES("region", "#{region,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            VALUES("receiver", "#{receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiverMobile() != null) {
            VALUES("receiver_mobile", "#{receiverMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDepositStatus() != null) {
            VALUES("deposit_status", "#{depositStatus,jdbcType=INTEGER}");
        }
        
        if (record.getApplyName() != null) {
            VALUES("apply_name", "#{applyName,jdbcType=VARCHAR}");
        }
        
        if (record.getInviteCode() != null) {
            VALUES("invite_code", "#{inviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getDeliverStatus() != null) {
            VALUES("deliver_status", "#{deliverStatus,jdbcType=INTEGER}");
        }
        
        if (record.getPayOrderNo() != null) {
            VALUES("pay_order_no", "#{payOrderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getRebackAlipayAccount() != null) {
            VALUES("reback_alipay_account", "#{rebackAlipayAccount,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(PosApplyExt record) {
        BEGIN();
        UPDATE("pos_apply_ext");
        
        if (record.getExpressName() != null) {
            SET("express_name = #{expressName,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressNo() != null) {
            SET("express_no = #{expressNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getApplyId() != null) {
            SET("apply_id = #{applyId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getApplyMobile() != null) {
            SET("apply_mobile = #{applyMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getProductId() != null) {
            SET("product_id = #{productId,jdbcType=INTEGER}");
        }
        
        if (record.getPosNo() != null) {
            SET("pos_no = #{posNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayType() != null) {
            SET("pay_type = #{payType,jdbcType=INTEGER}");
        }
        
        if (record.getPayPrice() != null) {
            SET("pay_price = #{payPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getPayDeposit() != null) {
            SET("pay_deposit = #{payDeposit,jdbcType=DECIMAL}");
        }
        
        if (record.getAddress() != null) {
            SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getRegion() != null) {
            SET("region = #{region,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            SET("receiver = #{receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiverMobile() != null) {
            SET("receiver_mobile = #{receiverMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDepositStatus() != null) {
            SET("deposit_status = #{depositStatus,jdbcType=INTEGER}");
        }
        
        if (record.getApplyName() != null) {
            SET("apply_name = #{applyName,jdbcType=VARCHAR}");
        }
        
        if (record.getInviteCode() != null) {
            SET("invite_code = #{inviteCode,jdbcType=VARCHAR}");
        }
        
        if (record.getDeliverStatus() != null) {
            SET("deliver_status = #{deliverStatus,jdbcType=INTEGER}");
        }
        
        if (record.getPayOrderNo() != null) {
            SET("pay_order_no = #{payOrderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getRebackAlipayAccount() != null) {
            SET("reback_alipay_account = #{rebackAlipayAccount,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}