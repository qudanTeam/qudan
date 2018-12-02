package com.qudan.qingcloud.msqudan.cloudClients.hystrix;

import com.qudan.qingcloud.msqudan.cloudClients.UserLoginClient;
import com.qudan.qingcloud.msqudan.util.YHResult;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginHystrix implements UserLoginClient {

    @Override
    public YHResult appLogin(String username, Integer userId) {
        return null;
    }
}
