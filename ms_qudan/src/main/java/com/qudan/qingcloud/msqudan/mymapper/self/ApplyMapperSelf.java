package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Apply;
import com.qudan.qingcloud.msqudan.mymapper.ApplyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApplyMapperSelf extends ApplyMapper {

    @Select({
        "SELECT * FROM apply WHERE product_id = #{productId} AND user_id = #{userId}"
    })
    Apply selectApplyByUserIdAndProductId(@Param("productId")Integer productId, @Param("userId")Integer userId);

    @Select({
        "SELECT * FROM apply WHERE name = #{username} AND id_no = #{idNo} LIMIT 1"
    })
    Apply selectApplyByUsernameAndIdNo(@Param("username")String username, @Param("idNo")String idNo, @Param("productId")Integer productId);

    @Select({
            "SELECT * FROM apply WHERE product_id = #{productId} AND id_no = #{idNo} AND status = 2 LIMIT 1"
    })
    Apply selectApplyIdNo(@Param("idNo")String idNo, @Param("productId")Integer productId);

    @Select({
        "SELECT id FROM apply WHERE apply_id_code like '%${ds}%' "
    })
    Integer selectLast5Apply(@Param("ds")String ds);

    @Select({
        "SELECT count(1) FROM apply"
    })
    Integer countApply();
}
