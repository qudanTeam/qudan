package com.qudan.qingcloud.msqudan.controller;

import com.qudan.qingcloud.msqudan.entity.Apply;
import com.qudan.qingcloud.msqudan.service.Impl.ApplyServiceImpl;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.requestBody.ApplyRB;
import com.qudan.qingcloud.msqudan.util.requestBody.UserRealnameRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class ApplyController {

    @Autowired
    ApplyServiceImpl applyService;

    @PostMapping("user/apply/card")
    public ResponseEntity<Map<String, Object>> cardApply(@RequestBody ApplyRB RB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(applyService.cardApply(ARE, RB));
        return ARE.createResponseEntity();
    }

    @PostMapping("user/apply/loan")
    public ResponseEntity<Map<String, Object>> loanApply(@RequestBody ApplyRB RB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(applyService.loanApply(ARE, RB));
        return ARE.createResponseEntity();
    }

    @GetMapping("/settle/trigger")
    public ResponseEntity<Map<String, Object>> loanApply(@Param("applyid")Integer applyId) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(applyService.settlement(ARE, applyId));
        return ARE.createResponseEntity();
    }

}
