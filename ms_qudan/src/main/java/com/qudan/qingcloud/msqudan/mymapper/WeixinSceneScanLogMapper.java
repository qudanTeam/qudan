package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.WeixinSceneScanLog;
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

public interface WeixinSceneScanLogMapper {
    @Delete({
        "delete from weixin_scene_scan_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into weixin_scene_scan_log (user_id, open_id, ",
        "create_time, scene_id, ",
        "qr_type)",
        "values (#{userId,jdbcType=BIGINT}, #{openId,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{sceneId,jdbcType=INTEGER}, ",
        "#{qrType,jdbcType=SMALLINT})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(WeixinSceneScanLog record);

    @InsertProvider(type=WeixinSceneScanLogSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(WeixinSceneScanLog record);

    @Select({
        "select",
        "id, user_id, open_id, create_time, scene_id, qr_type",
        "from weixin_scene_scan_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="scene_id", property="sceneId", jdbcType=JdbcType.INTEGER),
        @Result(column="qr_type", property="qrType", jdbcType=JdbcType.SMALLINT)
    })
    WeixinSceneScanLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeixinSceneScanLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeixinSceneScanLog record);

    @Update({
        "update weixin_scene_scan_log",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "open_id = #{openId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "scene_id = #{sceneId,jdbcType=INTEGER},",
          "qr_type = #{qrType,jdbcType=SMALLINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeixinSceneScanLog record);
}