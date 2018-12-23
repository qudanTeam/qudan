package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.Apply;
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

public interface ApplyMapper {
    @Delete({
        "delete from apply",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into apply (user_id, product_id, ",
        "create_time, modify_time, ",
        "mobile, name, id_no, ",
        "status, official_status, ",
        "last_official_query, reject_reason, ",
        "salary_status, invite_code, ",
        "apply_id_code, official_limit, ",
        "official_expire, official_time)",
        "values (#{userId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{mobile,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{idNo,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{officialStatus,jdbcType=INTEGER}, ",
        "#{lastOfficialQuery,jdbcType=TIMESTAMP}, #{rejectReason,jdbcType=VARCHAR}, ",
        "#{salaryStatus,jdbcType=INTEGER}, #{inviteCode,jdbcType=VARCHAR}, ",
        "#{applyIdCode,jdbcType=VARCHAR}, #{officialLimit,jdbcType=DECIMAL}, ",
        "#{officialExpire,jdbcType=VARCHAR}, #{officialTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Apply record);

    @InsertProvider(type=ApplySqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(Apply record);

    @Select({
        "select",
        "id, user_id, product_id, create_time, modify_time, mobile, name, id_no, status, ",
        "official_status, last_official_query, reject_reason, salary_status, invite_code, ",
        "apply_id_code, official_limit, official_expire, official_time",
        "from apply",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="id_no", property="idNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="official_status", property="officialStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="last_official_query", property="lastOfficialQuery", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="reject_reason", property="rejectReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="salary_status", property="salaryStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="invite_code", property="inviteCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="apply_id_code", property="applyIdCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="official_limit", property="officialLimit", jdbcType=JdbcType.DECIMAL),
        @Result(column="official_expire", property="officialExpire", jdbcType=JdbcType.VARCHAR),
        @Result(column="official_time", property="officialTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Apply selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ApplySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Apply record);

    @Update({
        "update apply",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "product_id = #{productId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "id_no = #{idNo,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "official_status = #{officialStatus,jdbcType=INTEGER},",
          "last_official_query = #{lastOfficialQuery,jdbcType=TIMESTAMP},",
          "reject_reason = #{rejectReason,jdbcType=VARCHAR},",
          "salary_status = #{salaryStatus,jdbcType=INTEGER},",
          "invite_code = #{inviteCode,jdbcType=VARCHAR},",
          "apply_id_code = #{applyIdCode,jdbcType=VARCHAR},",
          "official_limit = #{officialLimit,jdbcType=DECIMAL},",
          "official_expire = #{officialExpire,jdbcType=VARCHAR},",
          "official_time = #{officialTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Apply record);
}