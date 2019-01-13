package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.BankQuery;
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

public interface BankQueryMapper {
    @Delete({
        "delete from bank_query",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into bank_query (card_cat, bank_id, ",
        "name, jinjian_date, ",
        "card_status, status, ",
        "bank_name)",
        "values (#{cardCat,jdbcType=VARCHAR}, #{bankId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{jinjianDate,jdbcType=VARCHAR}, ",
        "#{cardStatus,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{bankName,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(BankQuery record);

    @InsertProvider(type=BankQuerySqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(BankQuery record);

    @Select({
        "select",
        "id, card_cat, bank_id, name, jinjian_date, card_status, status, bank_name",
        "from bank_query",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="card_cat", property="cardCat", jdbcType=JdbcType.VARCHAR),
        @Result(column="bank_id", property="bankId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="jinjian_date", property="jinjianDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_status", property="cardStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="bank_name", property="bankName", jdbcType=JdbcType.VARCHAR)
    })
    BankQuery selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BankQuerySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BankQuery record);

    @Update({
        "update bank_query",
        "set card_cat = #{cardCat,jdbcType=VARCHAR},",
          "bank_id = #{bankId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "jinjian_date = #{jinjianDate,jdbcType=VARCHAR},",
          "card_status = #{cardStatus,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "bank_name = #{bankName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BankQuery record);
}