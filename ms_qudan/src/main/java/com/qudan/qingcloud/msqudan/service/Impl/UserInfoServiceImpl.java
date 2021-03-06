package com.qudan.qingcloud.msqudan.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.service.UserInfoService;
import com.qudan.qingcloud.msqudan.util.YHResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/24.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);


    @Override
    @HystrixCommand
    public YHResult getUserInfoByUsername(Map<String, String> params) {

        return null;
    }

    /**
     * 根据用户查询用户所拥有的角色信息
     * @param params
     * @return
     */
    @Override
    @HystrixCommand
    public YHResult getUserAndRoleInfoByUsername(Map<String, String> params) {

        return null;
    }

    /**
     * 获取后台的所有api接口
     * @return
     */
    @Override
    @HystrixCommand
    public YHResult getAllApi() {
        return null;
    }


    /**
     * 根据apiid获取api所拥有的访问角色权限
     * @param params
     * @return
     */
    @Override
    @HystrixCommand
    public YHResult getApiRoleByApiId(Map<String, Object> params) {

        return null;
    }

    @Override
    @HystrixCommand
    public YHResult appLogin(Map<String, String> params) {

        return null;
    }
}
