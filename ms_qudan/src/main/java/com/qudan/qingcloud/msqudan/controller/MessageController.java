package com.qudan.qingcloud.msqudan.controller;

import com.qudan.qingcloud.msqudan.service.Impl.MessageServiceImpl;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class MessageController {

    @Autowired
    public MessageServiceImpl messageService;


    @GetMapping("/user/messages")
    public ResponseEntity<Map<String, Object>> messages(HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        if(ComUtils.validPage(ARE, request)) {
            Integer userId = LocalUserHelper.getUserId();
            ARE.setUserId(userId);
            ARE.setData(messageService.messages(ARE, ComUtils.getPage(request), ComUtils.getPerPage(request)));
        }
        return ARE.createResponseEntity();
    }

    @GetMapping("/user/message/{id}")
    public ResponseEntity<Map<String, Object>> message(@PathVariable("id")Integer id) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(messageService.messageGet(ARE, id));
        return ARE.createResponseEntity();
    }

    @DeleteMapping("/user/message/{id}")
    public ResponseEntity<Map<String, Object>> messageDelete(@PathVariable("id")Integer id) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        messageService.messageDelete(ARE, id);
        return ARE.createResponseEntity();
    }

    @PostMapping("/user/message/clear")
    public ResponseEntity<Map<String, Object>> messageClear(HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        messageService.messageClear(ARE);
        return ARE.createResponseEntity();
    }
}
