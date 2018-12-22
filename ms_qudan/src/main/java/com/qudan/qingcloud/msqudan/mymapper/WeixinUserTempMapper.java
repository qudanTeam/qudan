package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.WeixinUserTemp;
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

public interface WeixinUserTempMapper {
    @Delete({
        "delete from weixin_user_temp",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into weixin_user_temp (openid, unionid, ",
        "nickname, head_img_url, ",
        "sex, country, province, ",
        "language)",
        "values (#{openid,jdbcType=VARCHAR}, #{unionid,jdbcType=VARCHAR}, ",
        "#{nickname,jdbcType=VARCHAR}, #{headImgUrl,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
        "#{language,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(WeixinUserTemp record);

    @InsertProvider(type=WeixinUserTempSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(WeixinUserTemp record);

    @Select({
        "select",
        "id, openid, unionid, nickname, head_img_url, sex, country, province, language",
        "from weixin_user_temp",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="unionid", property="unionid", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_img_url", property="headImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR)
    })
    WeixinUserTemp selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeixinUserTempSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeixinUserTemp record);

    @Update({
        "update weixin_user_temp",
        "set openid = #{openid,jdbcType=VARCHAR},",
          "unionid = #{unionid,jdbcType=VARCHAR},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "head_img_url = #{headImgUrl,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "country = #{country,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "language = #{language,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeixinUserTemp record);
}