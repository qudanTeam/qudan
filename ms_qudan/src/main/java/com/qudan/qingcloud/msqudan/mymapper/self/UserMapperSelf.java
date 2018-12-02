package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapperSelf {

    @Select({
        "SELECT * FROM user WHERE register_mobile = #{mobile}"
    })
    User selectUserByMobile(String mobile);
}
