package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.entity.ShareManager;
import com.qudan.qingcloud.msqudan.entity.self.response.HotProductVo;
import com.qudan.qingcloud.msqudan.entity.self.response.ProductListVo;
import com.qudan.qingcloud.msqudan.mymapper.ProductMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapperSelf extends ProductMapper{

    @Select({
        "SELECT",
            "pro.id productId,",
            "pro.product_name productName,",
            "pro.product_type productType,",
            "pro.logo logo,",
            "pro.base_salary baseSalary,",
            "pro.sort_val sortVal,",
            "SUM(trade.price) salaryAmount",
        "FROM product pro",
        "LEFT JOIN apply ON apply.product_id = pro.id",
        "LEFT JOIN trade_type ON trade.apply_id = apply.id",
        "WHERE ",
            "pro.is_hot = 1 AND pro.is_show = 1 AND pro.is_shelf = 1",
            "AND apply.status != 3 AND apply.official_status != 3",
            "AND pro.product_type = #{type}",
            "GROUP BY",
            "pro.id, pro.product_name , pro.logo, pro.base_salary, pro.sort_val"
    })
    List<HotProductVo> getHotProduct(@Param("type")Integer type);


    @Select({
        "<script>",
            "SELECT ",
                "pro.id productId,",
                "pro.product_name productName,",
                "pro.product_type productType,",
                "pro.logo logo,",
                "pro.special_tag specialTag,",
                "pro.specialTxt specialTxt,",
                "pro.base_salary baseSalary,",
                "pro.amount_line amountLine,",
                "pro.allow_rate allowRate ,",
                "pro.apply_num applyNum ,",
                "pro.expire_unit expireUnit,",
                "pro.expire_begin expireBegin,",
                "pro.expire_end expireEnd,",
                "pro.day_rate dayRate,",
                "pro.month_rate monthRate",
            "FROM product pro",
            "WHERE ",
            "pro.is_hot = 1 AND pro.is_show = 1 AND pro.is_shelf = 1",
            "<if test=\"type != null\">",
                "pro.product_type = #{type}",
            "</if>",
            "<if test=\"keyword != null\">",
                "pro.product_name LIKE '%${keyword}%'",
            "</if>",
            "ORDER BY pro.create_time ASC",
        "</script>",
    })
    List<ProductListVo> getProductList(@Param("type")Integer type, @Param("String")String keyword);


    @Select({
        "SELECT * FROM share_manager WHERE is_show = AND product_id = #{productId}"
    })
    List<ShareManager> getShareManagerList(@Param("productId")Integer productId);

    @Select({
        "SELECT CAT.* FROM category CAT",
        "LEFT JOIN product_category_relation RELA on RELA.category_id = CAT.id",
        "LEFT JOIN product PRO ON PRO.id = RELA.id",
        "WHERE PRO.id = #{productId} AND PRO.id IS NOT NULL"
    })
    List<Category> selectCatByProductId(@Param("productId")Integer productId);
}
