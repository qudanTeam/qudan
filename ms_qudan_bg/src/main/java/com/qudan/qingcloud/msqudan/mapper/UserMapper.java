package com.qudan.qingcloud.msqudan.mapper;

import com.qudan.qingcloud.msqudan.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    List<User> selectByPage(User user);
}