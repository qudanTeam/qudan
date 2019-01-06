package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.Category;
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

public interface CategoryMapper {
    @Delete({
        "delete from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into category (name, create_time, ",
        "modify_time, category_type, ",
        "logo, get_link, need_verify_code, ",
        "need_mobile_verify_code, verify_code_link, ",
        "mobile_verify_code_link)",
        "values (#{name,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{categoryType,jdbcType=INTEGER}, ",
        "#{logo,jdbcType=VARCHAR}, #{getLink,jdbcType=VARCHAR}, #{needVerifyCode,jdbcType=TINYINT}, ",
        "#{needMobileVerifyCode,jdbcType=TINYINT}, #{verifyCodeLink,jdbcType=VARCHAR}, ",
        "#{mobileVerifyCodeLink,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Category record);

    @InsertProvider(type=CategorySqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(Category record);

    @Select({
        "select",
        "id, name, create_time, modify_time, category_type, logo, get_link, need_verify_code, ",
        "need_mobile_verify_code, verify_code_link, mobile_verify_code_link",
        "from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category_type", property="categoryType", jdbcType=JdbcType.INTEGER),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="get_link", property="getLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="need_verify_code", property="needVerifyCode", jdbcType=JdbcType.TINYINT),
        @Result(column="need_mobile_verify_code", property="needMobileVerifyCode", jdbcType=JdbcType.TINYINT),
        @Result(column="verify_code_link", property="verifyCodeLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile_verify_code_link", property="mobileVerifyCodeLink", jdbcType=JdbcType.VARCHAR)
    })
    Category selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Category record);

    @Update({
        "update category",
        "set name = #{name,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "category_type = #{categoryType,jdbcType=INTEGER},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "get_link = #{getLink,jdbcType=VARCHAR},",
          "need_verify_code = #{needVerifyCode,jdbcType=TINYINT},",
          "need_mobile_verify_code = #{needMobileVerifyCode,jdbcType=TINYINT},",
          "verify_code_link = #{verifyCodeLink,jdbcType=VARCHAR},",
          "mobile_verify_code_link = #{mobileVerifyCodeLink,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);
}