package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.Message;
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

public interface MessageMapper {
    @Delete({
        "delete from message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into message (msg_logo, msg_title, ",
        "msg_content, msg_link, ",
        "create_time, user_id, ",
        "is_user_delete, modify_time)",
        "values (#{msgLogo,jdbcType=VARCHAR}, #{msgTitle,jdbcType=VARCHAR}, ",
        "#{msgContent,jdbcType=VARCHAR}, #{msgLink,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{userId,jdbcType=INTEGER}, ",
        "#{isUserDelete,jdbcType=INTEGER}, #{modifyTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Message record);

    @InsertProvider(type=MessageSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(Message record);

    @Select({
        "select",
        "id, msg_logo, msg_title, msg_content, msg_link, create_time, user_id, is_user_delete, ",
        "modify_time",
        "from message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="msg_logo", property="msgLogo", jdbcType=JdbcType.VARCHAR),
        @Result(column="msg_title", property="msgTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="msg_content", property="msgContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="msg_link", property="msgLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_user_delete", property="isUserDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Message selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Message record);

    @Update({
        "update message",
        "set msg_logo = #{msgLogo,jdbcType=VARCHAR},",
          "msg_title = #{msgTitle,jdbcType=VARCHAR},",
          "msg_content = #{msgContent,jdbcType=VARCHAR},",
          "msg_link = #{msgLink,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "is_user_delete = #{isUserDelete,jdbcType=INTEGER},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Message record);
}