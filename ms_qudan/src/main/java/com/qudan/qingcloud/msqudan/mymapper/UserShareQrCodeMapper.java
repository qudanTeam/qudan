package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.UserShareQrCode;
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

public interface UserShareQrCodeMapper {
    @Delete({
        "delete from user_share_qr_code",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_share_qr_code (share_type, img_url, ",
        "pid, create_time, ",
        "user_id, address_url)",
        "values (#{shareType,jdbcType=INTEGER}, #{imgUrl,jdbcType=VARCHAR}, ",
        "#{pid,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{userId,jdbcType=INTEGER}, #{addressUrl,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(UserShareQrCode record);

    @InsertProvider(type=UserShareQrCodeSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(UserShareQrCode record);

    @Select({
        "select",
        "id, share_type, img_url, pid, create_time, user_id, address_url",
        "from user_share_qr_code",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="share_type", property="shareType", jdbcType=JdbcType.INTEGER),
        @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="address_url", property="addressUrl", jdbcType=JdbcType.VARCHAR)
    })
    UserShareQrCode selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserShareQrCodeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserShareQrCode record);

    @Update({
        "update user_share_qr_code",
        "set share_type = #{shareType,jdbcType=INTEGER},",
          "img_url = #{imgUrl,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "address_url = #{addressUrl,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserShareQrCode record);
}