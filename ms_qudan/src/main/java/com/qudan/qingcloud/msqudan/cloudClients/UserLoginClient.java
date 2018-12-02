package com.qudan.qingcloud.msqudan.cloudClients;


import com.qudan.qingcloud.msqudan.cloudClients.hystrix.UserLoginHystrix;
import com.qudan.qingcloud.msqudan.util.YHResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "qudan-zuul",configuration = FeignConfig.class ,fallback = UserLoginHystrix.class
)
public interface UserLoginClient {

    @RequestMapping(method = RequestMethod.POST, value = "/qudanZuul/user/login")
    YHResult appLogin(@RequestParam("username") String username, @RequestParam("userId") Integer userId);
}
