package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.User;
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

public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (username, password, ",
        "userface, isenable, ",
        "register_mobile, id_no, ",
        "alipay_no, agent_level, ",
        "register_time, last_login_time, ",
        "status, user_type, ",
        "modify_time, agent_id, ",
        "recommend_invite_code, invite_code, ",
        "recommend_invite_id, vip_name)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{userface,jdbcType=VARCHAR}, #{isenable,jdbcType=INTEGER}, ",
        "#{registerMobile,jdbcType=VARCHAR}, #{idNo,jdbcType=VARCHAR}, ",
        "#{alipayNo,jdbcType=VARCHAR}, #{agentLevel,jdbcType=INTEGER}, ",
        "#{registerTime,jdbcType=TIMESTAMP}, #{lastLoginTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER}, #{userType,jdbcType=INTEGER}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{agentId,jdbcType=INTEGER}, ",
        "#{recommendInviteCode,jdbcType=VARCHAR}, #{inviteCode,jdbcType=VARCHAR}, ",
        "#{recommendInviteId,jdbcType=BIGINT}, #{vipName,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(User record);

    @Select({
        "select",
        "id, username, password, userface, isenable, register_mobile, id_no, alipay_no, ",
        "agent_level, register_time, last_login_time, status, user_type, modify_time, ",
        "agent_id, recommend_invite_code, invite_code, recommend_invite_id, vip_name",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="userface", property="userface", jdbcType=JdbcType.VARCHAR),
        @Result(column="isenable", property="isenable", jdbcType=JdbcType.INTEGER),
        @Result(column="register_mobile", property="registerMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="id_no", property="idNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="alipay_no", property="alipayNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="agent_level", property="agentLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_login_time", property="lastLoginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="user_type", property="userType", jdbcType=JdbcType.INTEGER),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="agent_id", property="agentId", jdbcType=JdbcType.INTEGER),
        @Result(column="recommend_invite_code", property="recommendInviteCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="invite_code", property="inviteCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_invite_id", property="recommendInviteId", jdbcType=JdbcType.BIGINT),
        @Result(column="vip_name", property="vipName", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "userface = #{userface,jdbcType=VARCHAR},",
          "isenable = #{isenable,jdbcType=INTEGER},",
          "register_mobile = #{registerMobile,jdbcType=VARCHAR},",
          "id_no = #{idNo,jdbcType=VARCHAR},",
          "alipay_no = #{alipayNo,jdbcType=VARCHAR},",
          "agent_level = #{agentLevel,jdbcType=INTEGER},",
          "register_time = #{registerTime,jdbcType=TIMESTAMP},",
          "last_login_time = #{lastLoginTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER},",
          "user_type = #{userType,jdbcType=INTEGER},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "agent_id = #{agentId,jdbcType=INTEGER},",
          "recommend_invite_code = #{recommendInviteCode,jdbcType=VARCHAR},",
          "invite_code = #{inviteCode,jdbcType=VARCHAR},",
          "recommend_invite_id = #{recommendInviteId,jdbcType=BIGINT},",
          "vip_name = #{vipName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}