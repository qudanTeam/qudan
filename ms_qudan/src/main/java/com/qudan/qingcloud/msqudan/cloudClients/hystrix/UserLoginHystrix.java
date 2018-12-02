package com.qudan.qingcloud.msqudan.cloudClients.hystrix;

import com.qudan.qingcloud.msqudan.cloudClients.UserLoginClient;
import com.qudan.qingcloud.msqudan.controller.UserController;
import com.qudan.qingcloud.msqudan.util.YHResult;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginHystrix implements UserLoginClient {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Override
    public YHResult appLogin(String username, Integer userId) {

        log.info("UserLoginHystrix  已执行!");
        return null;
    }
}
