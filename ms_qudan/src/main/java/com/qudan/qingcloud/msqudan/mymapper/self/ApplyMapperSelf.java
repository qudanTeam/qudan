package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Apply;
import com.qudan.qingcloud.msqudan.entity.PayOrder;
import com.qudan.qingcloud.msqudan.mymapper.ApplyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select({
        "SELECT * FROM pay_order where ext_id = #{extId} ORDER BY sid DESC LIMIT 1 "
    })
    PayOrder getPosOrderStatus(@Param("extId")Integer extId);

    @Select({
        "SELECT * FROM pay_order WHERE ext_id = #{extId} AND order_status = 1"
    })
    PayOrder existAlreadyPayPosOrder(@Param("extId")Integer extId);

    @Update({
        "UPDATE pay_order SET order_status = 1 WHERE order_id = #{orderId}"
    })
    int updatePayOrderStatus(@Param("orderId")String orderId);
}
