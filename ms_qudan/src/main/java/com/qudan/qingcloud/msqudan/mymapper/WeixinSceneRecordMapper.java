package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.WeixinSceneRecord;
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

public interface WeixinSceneRecordMapper {
    @Delete({
        "delete from weixin_scene_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into weixin_scene_record (type, params, ",
        "create_time, expire_time, ",
        "expire_seconds, ticket, ",
        "scene_id, qr_type, ",
        "apply_user_id, qr_address)",
        "values (#{type,jdbcType=VARCHAR}, #{params,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{expireTime,jdbcType=TIMESTAMP}, ",
        "#{expireSeconds,jdbcType=INTEGER}, #{ticket,jdbcType=VARCHAR}, ",
        "#{sceneId,jdbcType=INTEGER}, #{qrType,jdbcType=INTEGER}, ",
        "#{applyUserId,jdbcType=INTEGER}, #{qrAddress,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(WeixinSceneRecord record);

    @InsertProvider(type=WeixinSceneRecordSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(WeixinSceneRecord record);

    @Select({
        "select",
        "id, type, params, create_time, expire_time, expire_seconds, ticket, scene_id, ",
        "qr_type, apply_user_id, qr_address",
        "from weixin_scene_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="expire_time", property="expireTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="expire_seconds", property="expireSeconds", jdbcType=JdbcType.INTEGER),
        @Result(column="ticket", property="ticket", jdbcType=JdbcType.VARCHAR),
        @Result(column="scene_id", property="sceneId", jdbcType=JdbcType.INTEGER),
        @Result(column="qr_type", property="qrType", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_user_id", property="applyUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="qr_address", property="qrAddress", jdbcType=JdbcType.VARCHAR)
    })
    WeixinSceneRecord selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeixinSceneRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeixinSceneRecord record);

    @Update({
        "update weixin_scene_record",
        "set type = #{type,jdbcType=VARCHAR},",
          "params = #{params,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "expire_time = #{expireTime,jdbcType=TIMESTAMP},",
          "expire_seconds = #{expireSeconds,jdbcType=INTEGER},",
          "ticket = #{ticket,jdbcType=VARCHAR},",
          "scene_id = #{sceneId,jdbcType=INTEGER},",
          "qr_type = #{qrType,jdbcType=INTEGER},",
          "apply_user_id = #{applyUserId,jdbcType=INTEGER},",
          "qr_address = #{qrAddress,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeixinSceneRecord record);
}