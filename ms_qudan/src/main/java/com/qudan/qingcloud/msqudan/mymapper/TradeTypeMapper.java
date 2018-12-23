package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.TradeType;
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

public interface TradeTypeMapper {
    @Delete({
        "delete from trade_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into trade_type (trade_type, apply_id, ",
        "price, create_time, ",
        "modify_time, status, ",
        "account, indirect_type, ",
        "send_status, audit_time, ",
        "send_time, vip_rate, ",
        "vip_level, base_price, ",
        "relation_user_id, user_id, ",
        "vip_price, reject_reason, ",
        "tx_name, tx_alipay_no, ",
        "agent_level, agent_rate)",
        "values (#{tradeType,jdbcType=INTEGER}, #{applyId,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DECIMAL}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER}, ",
        "#{account,jdbcType=INTEGER}, #{indirectType,jdbcType=INTEGER}, ",
        "#{sendStatus,jdbcType=INTEGER}, #{auditTime,jdbcType=TIMESTAMP}, ",
        "#{sendTime,jdbcType=TIMESTAMP}, #{vipRate,jdbcType=DECIMAL}, ",
        "#{vipLevel,jdbcType=SMALLINT}, #{basePrice,jdbcType=DECIMAL}, ",
        "#{relationUserId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{vipPrice,jdbcType=DECIMAL}, #{rejectReason,jdbcType=VARCHAR}, ",
        "#{txName,jdbcType=VARCHAR}, #{txAlipayNo,jdbcType=VARCHAR}, ",
        "#{agentLevel,jdbcType=INTEGER}, #{agentRate,jdbcType=DECIMAL})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(TradeType record);

    @InsertProvider(type=TradeTypeSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(TradeType record);

    @Select({
        "select",
        "id, trade_type, apply_id, price, create_time, modify_time, status, account, ",
        "indirect_type, send_status, audit_time, send_time, vip_rate, vip_level, base_price, ",
        "relation_user_id, user_id, vip_price, reject_reason, tx_name, tx_alipay_no, ",
        "agent_level, agent_rate",
        "from trade_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_id", property="applyId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="account", property="account", jdbcType=JdbcType.INTEGER),
        @Result(column="indirect_type", property="indirectType", jdbcType=JdbcType.INTEGER),
        @Result(column="send_status", property="sendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="audit_time", property="auditTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="send_time", property="sendTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="vip_rate", property="vipRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.SMALLINT),
        @Result(column="base_price", property="basePrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="relation_user_id", property="relationUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="vip_price", property="vipPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="reject_reason", property="rejectReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_name", property="txName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tx_alipay_no", property="txAlipayNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="agent_level", property="agentLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="agent_rate", property="agentRate", jdbcType=JdbcType.DECIMAL)
    })
    TradeType selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TradeTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TradeType record);

    @Update({
        "update trade_type",
        "set trade_type = #{tradeType,jdbcType=INTEGER},",
          "apply_id = #{applyId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER},",
          "account = #{account,jdbcType=INTEGER},",
          "indirect_type = #{indirectType,jdbcType=INTEGER},",
          "send_status = #{sendStatus,jdbcType=INTEGER},",
          "audit_time = #{auditTime,jdbcType=TIMESTAMP},",
          "send_time = #{sendTime,jdbcType=TIMESTAMP},",
          "vip_rate = #{vipRate,jdbcType=DECIMAL},",
          "vip_level = #{vipLevel,jdbcType=SMALLINT},",
          "base_price = #{basePrice,jdbcType=DECIMAL},",
          "relation_user_id = #{relationUserId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "vip_price = #{vipPrice,jdbcType=DECIMAL},",
          "reject_reason = #{rejectReason,jdbcType=VARCHAR},",
          "tx_name = #{txName,jdbcType=VARCHAR},",
          "tx_alipay_no = #{txAlipayNo,jdbcType=VARCHAR},",
          "agent_level = #{agentLevel,jdbcType=INTEGER},",
          "agent_rate = #{agentRate,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TradeType record);
}