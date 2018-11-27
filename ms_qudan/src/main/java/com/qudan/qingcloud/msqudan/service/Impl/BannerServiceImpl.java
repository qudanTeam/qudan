package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.entity.Banner;
import com.qudan.qingcloud.msqudan.mymapper.BannerMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.BannerMapperSelf;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl  {

    @Autowired
    BannerMapperSelf bannerMapperSelf;


    @HystrixCommand
    public Map<String,Object>  getBanners(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        List<Banner> list = bannerMapperSelf.banners();
        data.put("banners", list);
        return data;
    }


    @HystrixCommand
    public Map<String,Object> exam(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        return data;
    }
}
