package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.util.responses.BankSimple;
import com.qudan.qingcloud.msqudan.util.responses.ProductSimple;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BankMapperSelf {

    @Select({
        "SELECT * FROM category WHERE category_type = #{type} ORDER BY id"
    })
    List<Category> categories(@Param("type")Integer type);


    @Select({
            "SELECT ",
            "C.id bankId,",
            "C.get_link getLink,",
            "C.need_verify_code needVerifyCode,",
            "C.need_mobile_verify_code needMobileVerifyCode,",
            "C.verify_code_link verifyCodeLink, ",
            "C.mobile_verify_code_link mobileVerifyCodeLink",
            "FROM category C",
            "WHERE C.id = #{catId}",
    })
    BankSimple selectSimpleByProductId(@Param("catId")Integer catId);
}
