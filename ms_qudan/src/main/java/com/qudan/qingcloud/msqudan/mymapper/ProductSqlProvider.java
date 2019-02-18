package com.qudan.qingcloud.msqudan.mymapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.qudan.qingcloud.msqudan.entity.Product;

public class ProductSqlProvider {

    public String insertSelective(Product record) {
        BEGIN();
        INSERT_INTO("product");
        
        if (record.getProductName() != null) {
            VALUES("product_name", "#{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getLogo() != null) {
            VALUES("logo", "#{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getProductType() != null) {
            VALUES("product_type", "#{productType,jdbcType=INTEGER}");
        }
        
        if (record.getIsHot() != null) {
            VALUES("is_hot", "#{isHot,jdbcType=INTEGER}");
        }
        
        if (record.getIsShow() != null) {
            VALUES("is_show", "#{isShow,jdbcType=INTEGER}");
        }
        
        if (record.getCustomer() != null) {
            VALUES("customer", "#{customer,jdbcType=INTEGER}");
        }
        
        if (record.getIsInShop() != null) {
            VALUES("is_in_shop", "#{isInShop,jdbcType=INTEGER}");
        }
        
        if (record.getIsShelf() != null) {
            VALUES("is_shelf", "#{isShelf,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCommission() != null) {
            VALUES("commission", "#{commission,jdbcType=DECIMAL}");
        }
        
        if (record.getSortVal() != null) {
            VALUES("sort_val", "#{sortVal,jdbcType=INTEGER}");
        }
        
        if (record.getBgCategory() != null) {
            VALUES("bg_category", "#{bgCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getAmountLine() != null) {
            VALUES("amount_line", "#{amountLine,jdbcType=INTEGER}");
        }
        
        if (record.getProgressQueryImg() != null) {
            VALUES("progress_query_img", "#{progressQueryImg,jdbcType=VARCHAR}");
        }
        
        if (record.getAllowRate() != null) {
            VALUES("allow_rate", "#{allowRate,jdbcType=DECIMAL}");
        }
        
        if (record.getApplyNum() != null) {
            VALUES("apply_num", "#{applyNum,jdbcType=INTEGER}");
        }
        
        if (record.getApplyCondition() != null) {
            VALUES("apply_condition", "#{applyCondition,jdbcType=VARCHAR}");
        }
        
        if (record.getApplyTpImg() != null) {
            VALUES("apply_tp_img", "#{applyTpImg,jdbcType=VARCHAR}");
        }
        
        if (record.getDayRate() != null) {
            VALUES("day_rate", "#{dayRate,jdbcType=DECIMAL}");
        }
        
        if (record.getMonthRate() != null) {
            VALUES("month_rate", "#{monthRate,jdbcType=DECIMAL}");
        }
        
        if (record.getaBegin() != null) {
            VALUES("a_begin", "#{aBegin,jdbcType=INTEGER}");
        }
        
        if (record.getaLimit() != null) {
            VALUES("a_limit", "#{aLimit,jdbcType=INTEGER}");
        }
        
        if (record.getbBegin() != null) {
            VALUES("b_begin", "#{bBegin,jdbcType=INTEGER}");
        }
        
        if (record.getbLimit() != null) {
            VALUES("b_limit", "#{bLimit,jdbcType=INTEGER}");
        }
        
        if (record.getcStart() != null) {
            VALUES("c_start", "#{cStart,jdbcType=INTEGER}");
        }
        
        if (record.getcLimit() != null) {
            VALUES("c_limit", "#{cLimit,jdbcType=INTEGER}");
        }
        
        if (record.getaLevelReward() != null) {
            VALUES("a_level_reward", "#{aLevelReward,jdbcType=DECIMAL}");
        }
        
        if (record.getbLevelReward() != null) {
            VALUES("b_level_reward", "#{bLevelReward,jdbcType=DECIMAL}");
        }
        
        if (record.getcLevelReward() != null) {
            VALUES("c_level_reward", "#{cLevelReward,jdbcType=DECIMAL}");
        }
        
        if (record.getBaseSalary() != null) {
            VALUES("base_salary", "#{baseSalary,jdbcType=DECIMAL}");
        }
        
        if (record.getMonthSalary() != null) {
            VALUES("month_salary", "#{monthSalary,jdbcType=VARCHAR}");
        }
        
        if (record.getSalary() != null) {
            VALUES("salary", "#{salary,jdbcType=VARCHAR}");
        }
        
        if (record.getSalaryDesc() != null) {
            VALUES("salary_desc", "#{salaryDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getMonthSalaryDesc() != null) {
            VALUES("month_salary_desc", "#{monthSalaryDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getSecondSummary() != null) {
            VALUES("second_summary", "#{secondSummary,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdSummary() != null) {
            VALUES("third_summary", "#{thirdSummary,jdbcType=VARCHAR}");
        }
        
        if (record.getDetailHeaderImg() != null) {
            VALUES("detail_header_img", "#{detailHeaderImg,jdbcType=VARCHAR}");
        }
        
        if (record.getCardLongImg() != null) {
            VALUES("card_long_img", "#{cardLongImg,jdbcType=VARCHAR}");
        }
        
        if (record.getProductShowImg() != null) {
            VALUES("product_show_img", "#{productShowImg,jdbcType=VARCHAR}");
        }
        
        if (record.getBurundian() != null) {
            VALUES("burundian", "#{burundian,jdbcType=VARCHAR}");
        }
        
        if (record.getSettlementType() != null) {
            VALUES("settlement_type", "#{settlementType,jdbcType=INTEGER}");
        }
        
        if (record.getExpireUnit() != null) {
            VALUES("expire_unit", "#{expireUnit,jdbcType=VARCHAR}");
        }
        
        if (record.getHowSettle() != null) {
            VALUES("how_settle", "#{howSettle,jdbcType=VARCHAR}");
        }
        
        if (record.getExpireBegin() != null) {
            VALUES("expire_begin", "#{expireBegin,jdbcType=INTEGER}");
        }
        
        if (record.getExpireEnd() != null) {
            VALUES("expire_end", "#{expireEnd,jdbcType=INTEGER}");
        }
        
        if (record.getCommissionStandard() != null) {
            VALUES("commission_standard", "#{commissionStandard,jdbcType=VARCHAR}");
        }
        
        if (record.getShareTitle() != null) {
            VALUES("share_title", "#{shareTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getCardProgressImg() != null) {
            VALUES("card_progress_img", "#{cardProgressImg,jdbcType=VARCHAR}");
        }
        
        if (record.getBaseRight() != null) {
            VALUES("base_right", "#{baseRight,jdbcType=VARCHAR}");
        }
        
        if (record.getPreferential() != null) {
            VALUES("preferential", "#{preferential,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecialTag() != null) {
            VALUES("special_tag", "#{specialTag,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecialTxt() != null) {
            VALUES("special_txt", "#{specialTxt,jdbcType=VARCHAR}");
        }
        
        if (record.getUnit() != null) {
            VALUES("unit", "#{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getJlUnite() != null) {
            VALUES("jl_unite", "#{jlUnite,jdbcType=VARCHAR}");
        }
        
        if (record.getProductProfitPrice() != null) {
            VALUES("product_profit_price", "#{productProfitPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getProductLink() != null) {
            VALUES("product_link", "#{productLink,jdbcType=VARCHAR}");
        }
        
        if (record.getCardKind() != null) {
            VALUES("card_kind", "#{cardKind,jdbcType=TINYINT}");
        }
        
        if (record.getProductPoster() != null) {
            VALUES("product_poster", "#{productPoster,jdbcType=VARCHAR}");
        }
        
        if (record.getLoanLimit() != null) {
            VALUES("loan_limit", "#{loanLimit,jdbcType=INTEGER}");
        }
        
        if (record.getShareLogo() != null) {
            VALUES("share_logo", "#{shareLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getShareContent() != null) {
            VALUES("share_content", "#{shareContent,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatformAward() != null) {
            VALUES("platform_award", "#{platformAward,jdbcType=DECIMAL}");
        }
        
        if (record.getPosPrice() != null) {
            VALUES("pos_price", "#{posPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getPosDeposit() != null) {
            VALUES("pos_deposit", "#{posDeposit,jdbcType=DECIMAL}");
        }
        
        if (record.getBenefitsB() != null) {
            VALUES("benefits_b", "#{benefitsB,jdbcType=VARCHAR}");
        }
        
        if (record.getBenefitsC() != null) {
            VALUES("benefits_c", "#{benefitsC,jdbcType=VARCHAR}");
        }
        
        if (record.getRequireCondition() != null) {
            VALUES("require_condition", "#{requireCondition,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Product record) {
        BEGIN();
        UPDATE("product");
        
        if (record.getProductName() != null) {
            SET("product_name = #{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getLogo() != null) {
            SET("logo = #{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getProductType() != null) {
            SET("product_type = #{productType,jdbcType=INTEGER}");
        }
        
        if (record.getIsHot() != null) {
            SET("is_hot = #{isHot,jdbcType=INTEGER}");
        }
        
        if (record.getIsShow() != null) {
            SET("is_show = #{isShow,jdbcType=INTEGER}");
        }
        
        if (record.getCustomer() != null) {
            SET("customer = #{customer,jdbcType=INTEGER}");
        }
        
        if (record.getIsInShop() != null) {
            SET("is_in_shop = #{isInShop,jdbcType=INTEGER}");
        }
        
        if (record.getIsShelf() != null) {
            SET("is_shelf = #{isShelf,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCommission() != null) {
            SET("commission = #{commission,jdbcType=DECIMAL}");
        }
        
        if (record.getSortVal() != null) {
            SET("sort_val = #{sortVal,jdbcType=INTEGER}");
        }
        
        if (record.getBgCategory() != null) {
            SET("bg_category = #{bgCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getAmountLine() != null) {
            SET("amount_line = #{amountLine,jdbcType=INTEGER}");
        }
        
        if (record.getProgressQueryImg() != null) {
            SET("progress_query_img = #{progressQueryImg,jdbcType=VARCHAR}");
        }
        
        if (record.getAllowRate() != null) {
            SET("allow_rate = #{allowRate,jdbcType=DECIMAL}");
        }
        
        if (record.getApplyNum() != null) {
            SET("apply_num = #{applyNum,jdbcType=INTEGER}");
        }
        
        if (record.getApplyCondition() != null) {
            SET("apply_condition = #{applyCondition,jdbcType=VARCHAR}");
        }
        
        if (record.getApplyTpImg() != null) {
            SET("apply_tp_img = #{applyTpImg,jdbcType=VARCHAR}");
        }
        
        if (record.getDayRate() != null) {
            SET("day_rate = #{dayRate,jdbcType=DECIMAL}");
        }
        
        if (record.getMonthRate() != null) {
            SET("month_rate = #{monthRate,jdbcType=DECIMAL}");
        }
        
        if (record.getaBegin() != null) {
            SET("a_begin = #{aBegin,jdbcType=INTEGER}");
        }
        
        if (record.getaLimit() != null) {
            SET("a_limit = #{aLimit,jdbcType=INTEGER}");
        }
        
        if (record.getbBegin() != null) {
            SET("b_begin = #{bBegin,jdbcType=INTEGER}");
        }
        
        if (record.getbLimit() != null) {
            SET("b_limit = #{bLimit,jdbcType=INTEGER}");
        }
        
        if (record.getcStart() != null) {
            SET("c_start = #{cStart,jdbcType=INTEGER}");
        }
        
        if (record.getcLimit() != null) {
            SET("c_limit = #{cLimit,jdbcType=INTEGER}");
        }
        
        if (record.getaLevelReward() != null) {
            SET("a_level_reward = #{aLevelReward,jdbcType=DECIMAL}");
        }
        
        if (record.getbLevelReward() != null) {
            SET("b_level_reward = #{bLevelReward,jdbcType=DECIMAL}");
        }
        
        if (record.getcLevelReward() != null) {
            SET("c_level_reward = #{cLevelReward,jdbcType=DECIMAL}");
        }
        
        if (record.getBaseSalary() != null) {
            SET("base_salary = #{baseSalary,jdbcType=DECIMAL}");
        }
        
        if (record.getMonthSalary() != null) {
            SET("month_salary = #{monthSalary,jdbcType=VARCHAR}");
        }
        
        if (record.getSalary() != null) {
            SET("salary = #{salary,jdbcType=VARCHAR}");
        }
        
        if (record.getSalaryDesc() != null) {
            SET("salary_desc = #{salaryDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getMonthSalaryDesc() != null) {
            SET("month_salary_desc = #{monthSalaryDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getSecondSummary() != null) {
            SET("second_summary = #{secondSummary,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdSummary() != null) {
            SET("third_summary = #{thirdSummary,jdbcType=VARCHAR}");
        }
        
        if (record.getDetailHeaderImg() != null) {
            SET("detail_header_img = #{detailHeaderImg,jdbcType=VARCHAR}");
        }
        
        if (record.getCardLongImg() != null) {
            SET("card_long_img = #{cardLongImg,jdbcType=VARCHAR}");
        }
        
        if (record.getProductShowImg() != null) {
            SET("product_show_img = #{productShowImg,jdbcType=VARCHAR}");
        }
        
        if (record.getBurundian() != null) {
            SET("burundian = #{burundian,jdbcType=VARCHAR}");
        }
        
        if (record.getSettlementType() != null) {
            SET("settlement_type = #{settlementType,jdbcType=INTEGER}");
        }
        
        if (record.getExpireUnit() != null) {
            SET("expire_unit = #{expireUnit,jdbcType=VARCHAR}");
        }
        
        if (record.getHowSettle() != null) {
            SET("how_settle = #{howSettle,jdbcType=VARCHAR}");
        }
        
        if (record.getExpireBegin() != null) {
            SET("expire_begin = #{expireBegin,jdbcType=INTEGER}");
        }
        
        if (record.getExpireEnd() != null) {
            SET("expire_end = #{expireEnd,jdbcType=INTEGER}");
        }
        
        if (record.getCommissionStandard() != null) {
            SET("commission_standard = #{commissionStandard,jdbcType=VARCHAR}");
        }
        
        if (record.getShareTitle() != null) {
            SET("share_title = #{shareTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getCardProgressImg() != null) {
            SET("card_progress_img = #{cardProgressImg,jdbcType=VARCHAR}");
        }
        
        if (record.getBaseRight() != null) {
            SET("base_right = #{baseRight,jdbcType=VARCHAR}");
        }
        
        if (record.getPreferential() != null) {
            SET("preferential = #{preferential,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecialTag() != null) {
            SET("special_tag = #{specialTag,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecialTxt() != null) {
            SET("special_txt = #{specialTxt,jdbcType=VARCHAR}");
        }
        
        if (record.getUnit() != null) {
            SET("unit = #{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getJlUnite() != null) {
            SET("jl_unite = #{jlUnite,jdbcType=VARCHAR}");
        }
        
        if (record.getProductProfitPrice() != null) {
            SET("product_profit_price = #{productProfitPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getProductLink() != null) {
            SET("product_link = #{productLink,jdbcType=VARCHAR}");
        }
        
        if (record.getCardKind() != null) {
            SET("card_kind = #{cardKind,jdbcType=TINYINT}");
        }
        
        if (record.getProductPoster() != null) {
            SET("product_poster = #{productPoster,jdbcType=VARCHAR}");
        }
        
        if (record.getLoanLimit() != null) {
            SET("loan_limit = #{loanLimit,jdbcType=INTEGER}");
        }
        
        if (record.getShareLogo() != null) {
            SET("share_logo = #{shareLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getShareContent() != null) {
            SET("share_content = #{shareContent,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatformAward() != null) {
            SET("platform_award = #{platformAward,jdbcType=DECIMAL}");
        }
        
        if (record.getPosPrice() != null) {
            SET("pos_price = #{posPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getPosDeposit() != null) {
            SET("pos_deposit = #{posDeposit,jdbcType=DECIMAL}");
        }
        
        if (record.getBenefitsB() != null) {
            SET("benefits_b = #{benefitsB,jdbcType=VARCHAR}");
        }
        
        if (record.getBenefitsC() != null) {
            SET("benefits_c = #{benefitsC,jdbcType=VARCHAR}");
        }
        
        if (record.getRequireCondition() != null) {
            SET("require_condition = #{requireCondition,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}