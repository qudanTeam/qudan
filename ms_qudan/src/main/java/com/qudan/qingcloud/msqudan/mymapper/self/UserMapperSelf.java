package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.mymapper.UserMapper;
import com.qudan.qingcloud.msqudan.util.responses.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserMapperSelf extends UserMapper {

    @Select({
        "SELECT * FROM user WHERE register_mobile = #{mobile}"
    })
    User selectUserByMobile(String mobile);

    @Select({
        "SELECT * FROM user WHERE id = #{id}"
    })
    UserInfo selectById(Integer id);

}
