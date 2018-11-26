package com.qudan.qingcloud.msqudan.mapper;

import com.qudan.qingcloud.msqudan.util.YHMapper;
import com.qudan.qingcloud.msqudan.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/24.
 */

public interface UserMapper extends YHMapper<User> {

    //根据用户名查询用户信息(包括角色)
    Map<String,Object> getUserInfoByUsername(@Param("username") String username);

   //根据用户名查询用户所拥有的角色
    List<Map<String,Object>> getUserAndRoleInfoByUsername(@Param("username") String username);

    //根据用户名查询用户信息
//     @Select("SELECT * FROM User WHERE username = #{username}")

    //获取所有的后台接口
    List<Map<String,Object>> getAllApi();

    //根据apiid获取api所拥有的访问角色权限
    List<Map<String,Object>> getApiRoleByApiId(@Param("apiid") Integer apiid);

    //前端用户登录接口
    Map<String,Object> appLogin(@Param("username") String username,@Param("password") String password);

}
