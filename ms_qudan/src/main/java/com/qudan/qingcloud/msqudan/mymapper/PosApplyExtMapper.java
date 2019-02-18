package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.PosApplyExt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PosApplyExtMapper {
    @Delete({
        "delete from pos_apply_ext",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into pos_apply_ext (express_name, express_no, ",
        "create_time, apply_id, ",
        "user_id, apply_mobile, ",
        "product_id, pos_no, ",
        "pay_type, pay_price, ",
        "pay_deposit, address, ",
        "region, receiver, ",
        "receiver_mobile, modify_time, ",
        "deposit_status, apply_name, ",
        "invite_code, deliver_status, ",
        "pay_order_no, reback_alipay_account)",
        "values (#{expressName,jdbcType=VARCHAR}, #{expressNo,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{applyId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER}, #{applyMobile,jdbcType=VARCHAR}, ",
        "#{productId,jdbcType=INTEGER}, #{posNo,jdbcType=VARCHAR}, ",
        "#{payType,jdbcType=INTEGER}, #{payPrice,jdbcType=DECIMAL}, ",
        "#{payDeposit,jdbcType=DECIMAL}, #{address,jdbcType=VARCHAR}, ",
        "#{region,jdbcType=VARCHAR}, #{receiver,jdbcType=VARCHAR}, ",
        "#{receiverMobile,jdbcType=VARCHAR}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{depositStatus,jdbcType=INTEGER}, #{applyName,jdbcType=VARCHAR}, ",
        "#{inviteCode,jdbcType=VARCHAR}, #{deliverStatus,jdbcType=INTEGER}, ",
        "#{payOrderNo,jdbcType=VARCHAR}, #{rebackAlipayAccount,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(PosApplyExt record);

    @InsertProvider(type=PosApplyExtSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(PosApplyExt record);

    @Select({
        "select",
        "id, express_name, express_no, create_time, apply_id, user_id, apply_mobile, ",
        "product_id, pos_no, pay_type, pay_price, pay_deposit, address, region, receiver, ",
        "receiver_mobile, modify_time, deposit_status, apply_name, invite_code, deliver_status, ",
        "pay_order_no, reback_alipay_account",
        "from pos_apply_ext",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="express_name", property="expressName", jdbcType=JdbcType.VARCHAR),
        @Result(column="express_no", property="expressNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="apply_id", property="applyId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_mobile", property="applyMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="pos_no", property="posNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_price", property="payPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_deposit", property="payDeposit", jdbcType=JdbcType.DECIMAL),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="region", property="region", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_mobile", property="receiverMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deposit_status", property="depositStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_name", property="applyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="invite_code", property="inviteCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="deliver_status", property="deliverStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_order_no", property="payOrderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="reback_alipay_account", property="rebackAlipayAccount", jdbcType=JdbcType.VARCHAR)
    })
    PosApplyExt selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PosApplyExtSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PosApplyExt record);

    @Update({
        "update pos_apply_ext",
        "set express_name = #{expressName,jdbcType=VARCHAR},",
          "express_no = #{expressNo,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "apply_id = #{applyId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "apply_mobile = #{applyMobile,jdbcType=VARCHAR},",
          "product_id = #{productId,jdbcType=INTEGER},",
          "pos_no = #{posNo,jdbcType=VARCHAR},",
          "pay_type = #{payType,jdbcType=INTEGER},",
          "pay_price = #{payPrice,jdbcType=DECIMAL},",
          "pay_deposit = #{payDeposit,jdbcType=DECIMAL},",
          "address = #{address,jdbcType=VARCHAR},",
          "region = #{region,jdbcType=VARCHAR},",
          "receiver = #{receiver,jdbcType=VARCHAR},",
          "receiver_mobile = #{receiverMobile,jdbcType=VARCHAR},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "deposit_status = #{depositStatus,jdbcType=INTEGER},",
          "apply_name = #{applyName,jdbcType=VARCHAR},",
          "invite_code = #{inviteCode,jdbcType=VARCHAR},",
          "deliver_status = #{deliverStatus,jdbcType=INTEGER},",
          "pay_order_no = #{payOrderNo,jdbcType=VARCHAR},",
          "reback_alipay_account = #{rebackAlipayAccount,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PosApplyExt record);
}