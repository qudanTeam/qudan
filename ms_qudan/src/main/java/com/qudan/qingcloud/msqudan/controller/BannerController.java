package com.qudan.qingcloud.msqudan.controller;

import com.qudan.qingcloud.msqudan.service.Impl.BannerServiceImpl;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class BannerController {

    @Autowired
    BannerServiceImpl bannerService;

    @GetMapping("/banners")
    public ResponseEntity<Map<String, Object>> banners() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(bannerService.getBanners(ARE));
        return ARE.createResponseEntity();
    }


    @GetMapping("/exam")
    public ResponseEntity<Map<String, Object>> exam() {
        ApiResponseEntity ARE = new ApiResponseEntity();

        return ARE.createResponseEntity();
    }
}
