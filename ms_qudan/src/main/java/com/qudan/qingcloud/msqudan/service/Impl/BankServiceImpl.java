package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.mymapper.BannerMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.BankMapperSelf;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BankServiceImpl {

    @Autowired
    BankMapperSelf bankMapperSelf;

    @HystrixCommand
    public Map<String,Object> banks(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        data.put("banks", bankMapperSelf.categories(1));
        return data;
    }
}
