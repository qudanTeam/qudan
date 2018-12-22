package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.UserShare;
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

public interface UserShareMapper {
    @Delete({
        "delete from user_share",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_share (user_id, weixin_scene_id, ",
        "share_time, qr_code_id)",
        "values (#{userId,jdbcType=INTEGER}, #{weixinSceneId,jdbcType=INTEGER}, ",
        "#{shareTime,jdbcType=TIMESTAMP}, #{qrCodeId,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(UserShare record);

    @InsertProvider(type=UserShareSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(UserShare record);

    @Select({
        "select",
        "id, user_id, weixin_scene_id, share_time, qr_code_id",
        "from user_share",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="weixin_scene_id", property="weixinSceneId", jdbcType=JdbcType.INTEGER),
        @Result(column="share_time", property="shareTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="qr_code_id", property="qrCodeId", jdbcType=JdbcType.INTEGER)
    })
    UserShare selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserShareSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserShare record);

    @Update({
        "update user_share",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "weixin_scene_id = #{weixinSceneId,jdbcType=INTEGER},",
          "share_time = #{shareTime,jdbcType=TIMESTAMP},",
          "qr_code_id = #{qrCodeId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserShare record);
}