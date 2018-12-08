package com.qudan.qingcloud.msqudan.controller;


import com.qudan.qingcloud.msqudan.service.Impl.UserServiceImpl;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.requestBody.ShareAddRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/msqudan/api")//窄化请求地址
public class UserInfoController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/user/info")
    public ResponseEntity<Map<String, Object>> userInfo() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userService.getUserInfo(ARE));
        return ARE.createResponseEntity();
    }

    @GetMapping("/user/share/qrcode")
    public ResponseEntity<Map<String, Object>> getShareCode(@RequestParam(value = "shareType",required = true)Integer shareType, @RequestParam(value = "pid",required = true)Integer pid) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userService.getShareProductQrcode(ARE, shareType,  pid));
        return ARE.createResponseEntity();
    }

    @PostMapping("/user/share/addcount")
    public ResponseEntity<Map<String, Object>> shareAddcount(@RequestBody ShareAddRB RB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        if(RB != null && StringUtils.isNotBlank(RB.getTicket())){
            ARE.setData(userService.addShareTime(ARE, RB.getTicket()));
        } else {
            ARE.addInfoError("ticket.isEmpty", "不存在的ticket");
        }
        return ARE.createResponseEntity();
    }
}
