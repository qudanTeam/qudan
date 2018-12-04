package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.WeixinSceneRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WeixinQrMapperSelf {

    @Select({
        "SELECT scene_id FROM weixin_scene_record WHERE qr_type = #{type} ORDER BY scene_id DESC LIMIT 1 "
    })
    Integer getLastQrId(Integer type);

    @Select({
        "SELECT * FROM weixin_scene_record WHERE qr_type = 1 AND apply_user_id = #{userId} ORDER BY scene_id DESC LIMIT 1"
    })
    WeixinSceneRecord getUserEverQrId(Integer userId);


    @Select({
            "SELECT scene_id FROM weixin_scene_record WHERE ticket = #{ticket} AND apply_user_id = #{userId} ORDER BY scene_id DESC LIMIT 1 "
    })
    WeixinSceneRecord getQrRecordByTicket(@Param("ticket") String ticket, @Param("userId")Integer userId);
}
