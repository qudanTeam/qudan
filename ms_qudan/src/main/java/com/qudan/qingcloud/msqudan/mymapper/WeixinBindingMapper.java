package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.WeixinBinding;
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

public interface WeixinBindingMapper {
    @Delete({
        "delete from weixin_binding",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into weixin_binding (user_id, openid, ",
        "unionid, wechat_name, ",
        "wechat_logo, create_time, ",
        "modify_time)",
        "values (#{userId,jdbcType=INTEGER}, #{openid,jdbcType=VARCHAR}, ",
        "#{unionid,jdbcType=VARCHAR}, #{wechatName,jdbcType=VARCHAR}, ",
        "#{wechatLogo,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(WeixinBinding record);

    @InsertProvider(type=WeixinBindingSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(WeixinBinding record);

    @Select({
        "select",
        "id, user_id, openid, unionid, wechat_name, wechat_logo, create_time, modify_time",
        "from weixin_binding",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="unionid", property="unionid", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_name", property="wechatName", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_logo", property="wechatLogo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    WeixinBinding selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeixinBindingSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeixinBinding record);

    @Update({
        "update weixin_binding",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "openid = #{openid,jdbcType=VARCHAR},",
          "unionid = #{unionid,jdbcType=VARCHAR},",
          "wechat_name = #{wechatName,jdbcType=VARCHAR},",
          "wechat_logo = #{wechatLogo,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeixinBinding record);
}