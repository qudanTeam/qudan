package com.qudan.qingcloud.msqudan.controller;


import com.qudan.qingcloud.msqudan.service.Impl.UserServiceImpl;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.params.OrderParams;
import com.qudan.qingcloud.msqudan.util.requestBody.*;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/user/share/qrcodeurl")
    public ResponseEntity<Map<String, Object>> getShareCodeurl(@RequestBody  ShareRB shareRB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userService.getShareProductQrcodeUrl(ARE, shareRB));
        return ARE.createResponseEntity();
    }

  /*  @GetMapping("/user/share/qrcode")
    public ResponseEntity<Map<String, Object>> getShareCode(@RequestParam(value = "shareType",required = true)Integer shareType, @RequestParam(value = "pid",required = true)Integer pid) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userService.getShareProductQrcode(ARE, shareType,  pid));
        return ARE.createResponseEntity();
    }*/

    @PostMapping("/user/share/addcounturl")
    public ResponseEntity<Map<String, Object>> shareAddcounturl(@RequestBody ShareAddRB RB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        if(RB != null && StringUtils.isNotBlank(RB.getShareid())){
            ARE.setData(userService.addShareTime(ARE, RB.getShareid()));
        } else {
            ARE.addInfoError("shareid.isEmpty", "不存在的shareid");
        }
        return ARE.createResponseEntity();
    }

    @PostMapping("/user/realname")
    public ResponseEntity<Map<String, Object>> realname(@RequestBody UserRealnameRB RB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userService.realname(ARE, RB));
        return ARE.createResponseEntity();
    }

    @PostMapping("/user/finance/realname")
    public ResponseEntity<Map<String, Object>> financeRealname(@RequestBody UserRealnameRB RB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userService.financeRealname(ARE, RB));
        return ARE.createResponseEntity();
    }

    @PostMapping("/user/setpw")
    public ResponseEntity<Map<String, Object>> setpw(@RequestBody UserPwRB RB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userService.setpw(ARE, RB));
        return ARE.createResponseEntity();
    }
}
