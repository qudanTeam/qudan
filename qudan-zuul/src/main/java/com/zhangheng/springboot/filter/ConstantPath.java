package com.zhangheng.springboot.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/23.
 */
@Component
public class ConstantPath {

    @Value("${login.response.status}")
    public  String LOGIN_RESPONSE_STATUS;//登录状态

    @Value("${login.path}")
    public String LOGIN_PATH;//登录接口路径

    @Value("${login.response.result}")
    public String LOGIN_RESPONSE_RESULT;//登录结果

    @Value("${login.username}")
    public String LOGIN_USERNAME;//登录用户名


}
