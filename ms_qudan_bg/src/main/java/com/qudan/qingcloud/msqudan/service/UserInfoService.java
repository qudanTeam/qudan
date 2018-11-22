package com.qudan.qingcloud.msqudan.service;

import com.qudan.qingcloud.msqudan.util.YHResult;

import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/24.
 */
public interface UserInfoService {

    /**
     * 根据用户名查询用户信息
     */
    YHResult getUserInfoByUsername(Map<String, String> params);


    /**
     * 根据用户名称查询用所拥有的角色
     */
    YHResult getUserAndRoleInfoByUsername(Map<String, String> params);

    /**
     * 获取所有的后台api接口
     */
    YHResult getAllApi();

    /**
     * 根据apiid获取api所拥有的访问角色权限
     */
    YHResult getApiRoleByApiId(Map<String, Object> params);

    /**
     * 前端用户登录
     */
    YHResult appLogin(Map<String, String> params);

}
