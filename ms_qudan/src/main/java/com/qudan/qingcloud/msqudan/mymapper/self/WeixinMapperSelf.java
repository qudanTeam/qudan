package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.WeixinBinding;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WeixinMapperSelf {

    @Select({
        "SELECT * FROM weixin_binding WHERE openid = #{openid} LIMIT 1"
    })
    WeixinBinding selectBindingByOpenId(@Param("openid")String openid);

    @Select({
            "SELECT * FROM weixin_binding WHERE user_id = #{userId} LIMIT 1"
    })
    WeixinBinding selectBindingByUserId(@Param("userId")Integer userId);
}
