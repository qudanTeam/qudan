package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.UserAccount;
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

public interface UserAccountMapper {
    @Delete({
        "delete from user_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_account (user_id, blance, ",
        "allow_tx, tx, create_time, ",
        "modify_time)",
        "values (#{userId,jdbcType=INTEGER}, #{blance,jdbcType=DECIMAL}, ",
        "#{allowTx,jdbcType=DECIMAL}, #{tx,jdbcType=DECIMAL}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(UserAccount record);

    @InsertProvider(type=UserAccountSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(UserAccount record);

    @Select({
        "select",
        "id, user_id, blance, allow_tx, tx, create_time, modify_time",
        "from user_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="blance", property="blance", jdbcType=JdbcType.DECIMAL),
        @Result(column="allow_tx", property="allowTx", jdbcType=JdbcType.DECIMAL),
        @Result(column="tx", property="tx", jdbcType=JdbcType.DECIMAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserAccount selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserAccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccount record);

    @Update({
        "update user_account",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "blance = #{blance,jdbcType=DECIMAL},",
          "allow_tx = #{allowTx,jdbcType=DECIMAL},",
          "tx = #{tx,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserAccount record);
}