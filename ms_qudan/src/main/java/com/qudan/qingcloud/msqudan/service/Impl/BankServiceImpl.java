package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.mymapper.BannerMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.BankMapperSelf;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BankServiceImpl {

    @Autowired
    BankMapperSelf bankMapperSelf;

    @Autowired
    CommonConfig config;

    @HystrixCommand
    public Map<String,Object> banks(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        List<Category> banks = bankMapperSelf.categories(1);
        for (Category category : banks){
            category.setLogo(config.getQiniuImageUrl() + category.getLogo());
        }
        data.put("banks", banks);
        return data;
    }
}
