package com.qudan.qingcloud.msqudan.controller;

import com.qudan.qingcloud.msqudan.service.Impl.CharacterServiceImpl;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class CharacterController {

    @Autowired
    CharacterServiceImpl characterService;

    @GetMapping("/vips")
    public ResponseEntity<Map<String, Object>> vips() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(characterService.vips(ARE));
        return ARE.createResponseEntity();
    }


    @GetMapping("/agents")
    public ResponseEntity<Map<String, Object>> agents() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(characterService.agents(ARE));
        return ARE.createResponseEntity();
    }
}
