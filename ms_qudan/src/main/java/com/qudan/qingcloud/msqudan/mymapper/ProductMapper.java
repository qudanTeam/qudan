package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.Product;
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

public interface ProductMapper {
    @Delete({
        "delete from product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into product (product_name, logo, ",
        "product_type, is_hot, ",
        "is_show, customer, ",
        "is_in_shop, is_shelf, ",
        "create_time, modify_time, ",
        "commission, sort_val, ",
        "bg_category, amount_line, ",
        "progress_query_img, allow_rate, ",
        "apply_num, apply_condition, ",
        "apply_tp_img, day_rate, ",
        "month_rate, a_begin, ",
        "a_limit, b_begin, ",
        "b_limit, c_start, ",
        "c_limit, a_level_reward, ",
        "b_level_reward, c_level_reward, ",
        "base_salary, month_salary, ",
        "salary, salary_desc, ",
        "month_salary_desc, second_summary, ",
        "third_summary, detail_header_img, ",
        "card_long_img, product_show_img, ",
        "burundian, settlement_type, ",
        "expire_unit, how_settle, ",
        "expire_begin, expire_end, ",
        "commission_standard, share_title, ",
        "card_progress_img, base_right, ",
        "preferential, special_tag, ",
        "special_txt, unit)",
        "values (#{productName,jdbcType=VARCHAR}, #{logo,jdbcType=VARCHAR}, ",
        "#{productType,jdbcType=INTEGER}, #{isHot,jdbcType=INTEGER}, ",
        "#{isShow,jdbcType=INTEGER}, #{customer,jdbcType=INTEGER}, ",
        "#{isInShop,jdbcType=INTEGER}, #{isShelf,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{commission,jdbcType=DECIMAL}, #{sortVal,jdbcType=INTEGER}, ",
        "#{bgCategory,jdbcType=VARCHAR}, #{amountLine,jdbcType=INTEGER}, ",
        "#{progressQueryImg,jdbcType=VARCHAR}, #{allowRate,jdbcType=DECIMAL}, ",
        "#{applyNum,jdbcType=INTEGER}, #{applyCondition,jdbcType=VARCHAR}, ",
        "#{applyTpImg,jdbcType=VARCHAR}, #{dayRate,jdbcType=DECIMAL}, ",
        "#{monthRate,jdbcType=DECIMAL}, #{aBegin,jdbcType=INTEGER}, ",
        "#{aLimit,jdbcType=INTEGER}, #{bBegin,jdbcType=INTEGER}, ",
        "#{bLimit,jdbcType=INTEGER}, #{cStart,jdbcType=INTEGER}, ",
        "#{cLimit,jdbcType=VARCHAR}, #{aLevelReward,jdbcType=DECIMAL}, ",
        "#{bLevelReward,jdbcType=DECIMAL}, #{cLevelReward,jdbcType=DECIMAL}, ",
        "#{baseSalary,jdbcType=DECIMAL}, #{monthSalary,jdbcType=VARCHAR}, ",
        "#{salary,jdbcType=VARCHAR}, #{salaryDesc,jdbcType=VARCHAR}, ",
        "#{monthSalaryDesc,jdbcType=VARCHAR}, #{secondSummary,jdbcType=VARCHAR}, ",
        "#{thirdSummary,jdbcType=VARCHAR}, #{detailHeaderImg,jdbcType=VARCHAR}, ",
        "#{cardLongImg,jdbcType=VARCHAR}, #{productShowImg,jdbcType=VARCHAR}, ",
        "#{burundian,jdbcType=VARCHAR}, #{settlementType,jdbcType=INTEGER}, ",
        "#{expireUnit,jdbcType=VARCHAR}, #{howSettle,jdbcType=VARCHAR}, ",
        "#{expireBegin,jdbcType=INTEGER}, #{expireEnd,jdbcType=INTEGER}, ",
        "#{commissionStandard,jdbcType=VARCHAR}, #{shareTitle,jdbcType=VARCHAR}, ",
        "#{cardProgressImg,jdbcType=VARCHAR}, #{baseRight,jdbcType=VARCHAR}, ",
        "#{preferential,jdbcType=VARCHAR}, #{specialTag,jdbcType=VARCHAR}, ",
        "#{specialTxt,jdbcType=VARCHAR}, #{unit,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Product record);

    @InsertProvider(type=ProductSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(Product record);

    @Select({
        "select",
        "id, product_name, logo, product_type, is_hot, is_show, customer, is_in_shop, ",
        "is_shelf, create_time, modify_time, commission, sort_val, bg_category, amount_line, ",
        "progress_query_img, allow_rate, apply_num, apply_condition, apply_tp_img, day_rate, ",
        "month_rate, a_begin, a_limit, b_begin, b_limit, c_start, c_limit, a_level_reward, ",
        "b_level_reward, c_level_reward, base_salary, month_salary, salary, salary_desc, ",
        "month_salary_desc, second_summary, third_summary, detail_header_img, card_long_img, ",
        "product_show_img, burundian, settlement_type, expire_unit, how_settle, expire_begin, ",
        "expire_end, commission_standard, share_title, card_progress_img, base_right, ",
        "preferential, special_tag, special_txt, unit",
        "from product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_type", property="productType", jdbcType=JdbcType.INTEGER),
        @Result(column="is_hot", property="isHot", jdbcType=JdbcType.INTEGER),
        @Result(column="is_show", property="isShow", jdbcType=JdbcType.INTEGER),
        @Result(column="customer", property="customer", jdbcType=JdbcType.INTEGER),
        @Result(column="is_in_shop", property="isInShop", jdbcType=JdbcType.INTEGER),
        @Result(column="is_shelf", property="isShelf", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="commission", property="commission", jdbcType=JdbcType.DECIMAL),
        @Result(column="sort_val", property="sortVal", jdbcType=JdbcType.INTEGER),
        @Result(column="bg_category", property="bgCategory", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount_line", property="amountLine", jdbcType=JdbcType.INTEGER),
        @Result(column="progress_query_img", property="progressQueryImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="allow_rate", property="allowRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="apply_num", property="applyNum", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_condition", property="applyCondition", jdbcType=JdbcType.VARCHAR),
        @Result(column="apply_tp_img", property="applyTpImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="day_rate", property="dayRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="month_rate", property="monthRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="a_begin", property="aBegin", jdbcType=JdbcType.INTEGER),
        @Result(column="a_limit", property="aLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="b_begin", property="bBegin", jdbcType=JdbcType.INTEGER),
        @Result(column="b_limit", property="bLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="c_start", property="cStart", jdbcType=JdbcType.INTEGER),
        @Result(column="c_limit", property="cLimit", jdbcType=JdbcType.VARCHAR),
        @Result(column="a_level_reward", property="aLevelReward", jdbcType=JdbcType.DECIMAL),
        @Result(column="b_level_reward", property="bLevelReward", jdbcType=JdbcType.DECIMAL),
        @Result(column="c_level_reward", property="cLevelReward", jdbcType=JdbcType.DECIMAL),
        @Result(column="base_salary", property="baseSalary", jdbcType=JdbcType.DECIMAL),
        @Result(column="month_salary", property="monthSalary", jdbcType=JdbcType.VARCHAR),
        @Result(column="salary", property="salary", jdbcType=JdbcType.VARCHAR),
        @Result(column="salary_desc", property="salaryDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="month_salary_desc", property="monthSalaryDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="second_summary", property="secondSummary", jdbcType=JdbcType.VARCHAR),
        @Result(column="third_summary", property="thirdSummary", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_header_img", property="detailHeaderImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_long_img", property="cardLongImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_show_img", property="productShowImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="burundian", property="burundian", jdbcType=JdbcType.VARCHAR),
        @Result(column="settlement_type", property="settlementType", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_unit", property="expireUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="how_settle", property="howSettle", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire_begin", property="expireBegin", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_end", property="expireEnd", jdbcType=JdbcType.INTEGER),
        @Result(column="commission_standard", property="commissionStandard", jdbcType=JdbcType.VARCHAR),
        @Result(column="share_title", property="shareTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_progress_img", property="cardProgressImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="base_right", property="baseRight", jdbcType=JdbcType.VARCHAR),
        @Result(column="preferential", property="preferential", jdbcType=JdbcType.VARCHAR),
        @Result(column="special_tag", property="specialTag", jdbcType=JdbcType.VARCHAR),
        @Result(column="special_txt", property="specialTxt", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR)
    })
    Product selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Product record);

    @Update({
        "update product",
        "set product_name = #{productName,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "product_type = #{productType,jdbcType=INTEGER},",
          "is_hot = #{isHot,jdbcType=INTEGER},",
          "is_show = #{isShow,jdbcType=INTEGER},",
          "customer = #{customer,jdbcType=INTEGER},",
          "is_in_shop = #{isInShop,jdbcType=INTEGER},",
          "is_shelf = #{isShelf,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "commission = #{commission,jdbcType=DECIMAL},",
          "sort_val = #{sortVal,jdbcType=INTEGER},",
          "bg_category = #{bgCategory,jdbcType=VARCHAR},",
          "amount_line = #{amountLine,jdbcType=INTEGER},",
          "progress_query_img = #{progressQueryImg,jdbcType=VARCHAR},",
          "allow_rate = #{allowRate,jdbcType=DECIMAL},",
          "apply_num = #{applyNum,jdbcType=INTEGER},",
          "apply_condition = #{applyCondition,jdbcType=VARCHAR},",
          "apply_tp_img = #{applyTpImg,jdbcType=VARCHAR},",
          "day_rate = #{dayRate,jdbcType=DECIMAL},",
          "month_rate = #{monthRate,jdbcType=DECIMAL},",
          "a_begin = #{aBegin,jdbcType=INTEGER},",
          "a_limit = #{aLimit,jdbcType=INTEGER},",
          "b_begin = #{bBegin,jdbcType=INTEGER},",
          "b_limit = #{bLimit,jdbcType=INTEGER},",
          "c_start = #{cStart,jdbcType=INTEGER},",
          "c_limit = #{cLimit,jdbcType=VARCHAR},",
          "a_level_reward = #{aLevelReward,jdbcType=DECIMAL},",
          "b_level_reward = #{bLevelReward,jdbcType=DECIMAL},",
          "c_level_reward = #{cLevelReward,jdbcType=DECIMAL},",
          "base_salary = #{baseSalary,jdbcType=DECIMAL},",
          "month_salary = #{monthSalary,jdbcType=VARCHAR},",
          "salary = #{salary,jdbcType=VARCHAR},",
          "salary_desc = #{salaryDesc,jdbcType=VARCHAR},",
          "month_salary_desc = #{monthSalaryDesc,jdbcType=VARCHAR},",
          "second_summary = #{secondSummary,jdbcType=VARCHAR},",
          "third_summary = #{thirdSummary,jdbcType=VARCHAR},",
          "detail_header_img = #{detailHeaderImg,jdbcType=VARCHAR},",
          "card_long_img = #{cardLongImg,jdbcType=VARCHAR},",
          "product_show_img = #{productShowImg,jdbcType=VARCHAR},",
          "burundian = #{burundian,jdbcType=VARCHAR},",
          "settlement_type = #{settlementType,jdbcType=INTEGER},",
          "expire_unit = #{expireUnit,jdbcType=VARCHAR},",
          "how_settle = #{howSettle,jdbcType=VARCHAR},",
          "expire_begin = #{expireBegin,jdbcType=INTEGER},",
          "expire_end = #{expireEnd,jdbcType=INTEGER},",
          "commission_standard = #{commissionStandard,jdbcType=VARCHAR},",
          "share_title = #{shareTitle,jdbcType=VARCHAR},",
          "card_progress_img = #{cardProgressImg,jdbcType=VARCHAR},",
          "base_right = #{baseRight,jdbcType=VARCHAR},",
          "preferential = #{preferential,jdbcType=VARCHAR},",
          "special_tag = #{specialTag,jdbcType=VARCHAR},",
          "special_txt = #{specialTxt,jdbcType=VARCHAR},",
          "unit = #{unit,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);
}