package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.VipConfig;
import com.qudan.qingcloud.msqudan.entity.VipRecord;
import com.qudan.qingcloud.msqudan.mymapper.VipConfigMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VipMapperSelf extends VipConfigMapper{

    @Select({
        "SELECT * FROM vip_record WHERE user_id = #{userId} ORDER BY ID DESC LIMIT 1"
    })
    VipRecord selectVipById(@Param("userId") Integer userId);

    @Select({
        "SELECT * FROM vip_config ORDER BY vip_price ASC"
    })
    List<VipConfig> selectVips();
}
