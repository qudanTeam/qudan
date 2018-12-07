package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.VipRecord;
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

public interface VipRecordMapper {
    @Delete({
        "delete from vip_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into vip_record (trade_id, vip_config_id, ",
        "start_time, user_id, ",
        "end_time)",
        "values (#{tradeId,jdbcType=INTEGER}, #{vipConfigId,jdbcType=INTEGER}, ",
        "#{startTime,jdbcType=TIMESTAMP}, #{userId,jdbcType=INTEGER}, ",
        "#{endTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(VipRecord record);

    @InsertProvider(type=VipRecordSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(VipRecord record);

    @Select({
        "select",
        "id, trade_id, vip_config_id, start_time, user_id, end_time",
        "from vip_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="trade_id", property="tradeId", jdbcType=JdbcType.INTEGER),
        @Result(column="vip_config_id", property="vipConfigId", jdbcType=JdbcType.INTEGER),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP)
    })
    VipRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=VipRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VipRecord record);

    @Update({
        "update vip_record",
        "set trade_id = #{tradeId,jdbcType=INTEGER},",
          "vip_config_id = #{vipConfigId,jdbcType=INTEGER},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "end_time = #{endTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(VipRecord record);
}