package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.VipConfig;
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

public interface VipConfigMapper {
    @Delete({
        "delete from vip_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into vip_config (add_rate, vip_price, ",
        "start_time, service_days, ",
        "over_time, vip_logo, ",
        "create_time, modify_time, ",
        "isenable, version, ",
        "vip_name)",
        "values (#{addRate,jdbcType=DECIMAL}, #{vipPrice,jdbcType=DECIMAL}, ",
        "#{startTime,jdbcType=TIMESTAMP}, #{serviceDays,jdbcType=INTEGER}, ",
        "#{overTime,jdbcType=TIMESTAMP}, #{vipLogo,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{isenable,jdbcType=INTEGER}, #{version,jdbcType=INTEGER}, ",
        "#{vipName,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(VipConfig record);

    @InsertProvider(type=VipConfigSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(VipConfig record);

    @Select({
        "select",
        "id, add_rate, vip_price, start_time, service_days, over_time, vip_logo, create_time, ",
        "modify_time, isenable, version, vip_name",
        "from vip_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="add_rate", property="addRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="vip_price", property="vipPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="service_days", property="serviceDays", jdbcType=JdbcType.INTEGER),
        @Result(column="over_time", property="overTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="vip_logo", property="vipLogo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="isenable", property="isenable", jdbcType=JdbcType.INTEGER),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="vip_name", property="vipName", jdbcType=JdbcType.VARCHAR)
    })
    VipConfig selectByPrimaryKey(Integer id);

    @UpdateProvider(type=VipConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VipConfig record);

    @Update({
        "update vip_config",
        "set add_rate = #{addRate,jdbcType=DECIMAL},",
          "vip_price = #{vipPrice,jdbcType=DECIMAL},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "service_days = #{serviceDays,jdbcType=INTEGER},",
          "over_time = #{overTime,jdbcType=TIMESTAMP},",
          "vip_logo = #{vipLogo,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "isenable = #{isenable,jdbcType=INTEGER},",
          "version = #{version,jdbcType=INTEGER},",
          "vip_name = #{vipName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VipConfig record);
}