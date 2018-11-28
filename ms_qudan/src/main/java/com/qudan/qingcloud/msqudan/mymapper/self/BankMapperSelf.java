package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Category;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BankMapperSelf {

    @Select({
        "SELECT * FROM category WHERE category_type = #{type} ORDER BY id"
    })
    List<Category> categories(@Param("type")Integer type);
}
