package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.SmsSendRecord;
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

public interface SmsSendRecordMapper {
    @Delete({
        "delete from sms_send_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sms_send_record (code, send_type, ",
        "mobile, create_time, ",
        "invalid_time, is_valid)",
        "values (#{code,jdbcType=VARCHAR}, #{sendType,jdbcType=INTEGER}, ",
        "#{mobile,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{invalidTime,jdbcType=TIMESTAMP}, #{isValid,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(SmsSendRecord record);

    @InsertProvider(type=SmsSendRecordSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(SmsSendRecord record);

    @Select({
        "select",
        "id, code, send_type, mobile, create_time, invalid_time, is_valid",
        "from sms_send_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="send_type", property="sendType", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="invalid_time", property="invalidTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.INTEGER)
    })
    SmsSendRecord selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SmsSendRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsSendRecord record);

    @Update({
        "update sms_send_record",
        "set code = #{code,jdbcType=VARCHAR},",
          "send_type = #{sendType,jdbcType=INTEGER},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "invalid_time = #{invalidTime,jdbcType=TIMESTAMP},",
          "is_valid = #{isValid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SmsSendRecord record);
}