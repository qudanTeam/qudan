package com.qudan.qingcloud.msqudan.mymapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select({
        "SELECT username FROM user"
    })
    List<String> selectUserById();
}
