package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.entity.ProductConfig;
import com.qudan.qingcloud.msqudan.entity.ShareManager;
import com.qudan.qingcloud.msqudan.util.responses.HotProductVo;
import com.qudan.qingcloud.msqudan.util.responses.ProductListVo;
import com.qudan.qingcloud.msqudan.mymapper.ProductMapper;
import com.qudan.qingcloud.msqudan.util.responses.ProductSimple;
import io.swagger.models.auth.In;
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
            "pro.commission commission,",
            "SUM(trade.price) salaryAmount",
        "FROM product pro",
        "LEFT JOIN apply ON apply.product_id = pro.id",
        "LEFT JOIN trade_type trade ON trade.apply_id = apply.id",
        "WHERE ",
            "pro.is_hot = 1 AND pro.is_show = 1 AND pro.is_shelf = 1",
            "AND (apply.id is null " +
                    "or (",
                        "apply.id is not null ",
                        "AND apply. STATUS != 3",
                        "AND apply.official_status !=3",
                    ")",
                ")",
            "AND pro.product_type = #{type}",
            "GROUP BY",
            "pro.id, pro.product_name , pro.logo, pro.base_salary, pro.sort_val"
    })
    List<HotProductVo> getHotProduct(@Param("type")Integer type);

    @Select({
        "SELECT count(1) FROM user_share_qr_code WHERE pid = #{pid}"
    })
    int getRecommendCt(@Param("pid")Integer pid);

    @Select({
        "<script>",
            "SELECT ",
                "pro.id productId,",
                "pro.product_name productName,",
                "pro.product_type productType,",
                "pro.logo logo,",
                "pro.special_tag specialTag,",
                "pro.special_txt specialTxt,",
                "pro.base_salary baseSalary,",
                "pro.amount_line amountLine,",
                "pro.allow_rate allowRate ,",
                "pro.apply_num applyNum ,",
                "pro.expire_unit expireUnit,",
                "pro.expire_begin expireBegin,",
                "pro.expire_end expireEnd,",
                "pro.day_rate dayRate,",
                "pro.commission commission,",
                "pro.product_poster productPoster,",
                "pro.month_rate monthRate",
            "FROM product pro",
            "WHERE ",
            "pro.is_hot = 1 AND pro.is_show = 1 AND pro.is_shelf = 1",
            "<if test=\"type != null\">",
                "AND pro.product_type = #{type}",
            "</if>",
            "<if test=\"keyword != null\">",
                "AND  pro.product_name LIKE '%${keyword}%'",
            "</if>",
            "ORDER BY pro.create_time ASC",
        "</script>",
    })
    List<ProductListVo> getProductList(@Param("type")Integer type, @Param("keyword")String keyword);


    @Select({
            "SELECT * FROM product_config WHERE  product_id = #{productId}"
    })
   List<ProductConfig> getProductConfig(@Param("productId")Integer productId);

    @Select({
        "SELECT * FROM share_manager WHERE is_show = 1 AND product_id = #{productId}"
    })
    List<ShareManager> getShareManagerList(@Param("productId")Integer productId);

    @Select({
        "SELECT CAT.* FROM category CAT",
        "LEFT JOIN product_category_relation RELA on RELA.category_id = CAT.id",
        "LEFT JOIN product PRO ON PRO.id = RELA.product_id",
        "WHERE PRO.id = #{productId} AND PRO.id IS NOT NULL"
    })
    List<Category> selectCatByProductId(@Param("productId")Integer productId);

    @Select({
            "SELECT ",
            "C.id bankId,",
            "p.id productId,",
            "p.product_link productLink,",
            "C.get_link getLink,",
            "C.need_verify_code needVerifyCode,",
            "C.need_mobile_verify_code needMobileVerifyCode,",
            "C.verify_code_link verifyCodeLink, ",
            "C.mobile_verify_code_link mobileVerifyCodeLink",
            "FROM product  p ",
            "LEFT JOIN product_category_relation PCR ON PCR.product_id = p.id",
            "LEFT JOIN category C ON C.id = PCR.category_id",
            "WHERE p.id = #{productId}",
    })
    ProductSimple selectSimpleByProductId(@Param("productId")Integer productId);
}
