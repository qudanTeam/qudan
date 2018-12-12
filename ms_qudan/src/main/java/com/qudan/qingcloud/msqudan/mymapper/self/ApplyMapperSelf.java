package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Apply;
import com.qudan.qingcloud.msqudan.mymapper.ApplyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ApplyMapperSelf extends ApplyMapper {

    @Select({
        "SELECT * FROM apply WHERE product_id = #{productId} AND user_id = #{userId}"
    })
    Apply selectApplyByUserIdAndProductId(@Param("productId")Integer productId, @Param("userId")Integer userId);
}