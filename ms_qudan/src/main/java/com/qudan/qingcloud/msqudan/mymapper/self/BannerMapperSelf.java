package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Banner;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BannerMapperSelf {

    @Select({
        "SELECT * FROM banner ORDER BY sort_val, id DESC"
    })
    List<Banner> banners();
}
