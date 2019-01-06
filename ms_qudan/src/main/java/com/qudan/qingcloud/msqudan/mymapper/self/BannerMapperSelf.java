package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Banner;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BannerMapperSelf {

    @Select({
        "<script>",
            "SELECT * FROM banner WHERE ",
            "is_show = 1 ",
            "<if test=\"position != null\">AND position = #{position} </if>",
            "ORDER BY sort_val, id DESC",
        "</script>",
    })
    List<Banner> banners(@Param("position")Integer position);
}
