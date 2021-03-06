package com.qudan.qingcloud.msqudan.controller;

import com.qudan.qingcloud.msqudan.service.Impl.BankServiceImpl;
import com.qudan.qingcloud.msqudan.service.Impl.BannerServiceImpl;
import com.qudan.qingcloud.msqudan.service.Impl.UserFinanceServiceImpl;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class BankController {

    @Autowired
    BankServiceImpl bankService;

    @Autowired
    UserFinanceServiceImpl userFinanceService;

    @GetMapping("/banks")
    public ResponseEntity<Map<String, Object>> banners() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(bankService.banks(ARE));
        return ARE.createResponseEntity();
    }

    @GetMapping("test/vip")
    public ResponseEntity<Map<String, Object>> testVip(@RequestParam("user_id")Integer user_id, @RequestParam("vip_id")Integer vipId) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        userFinanceService.becomeVip(user_id, vipId);
        return ARE.createResponseEntity();
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<Map<String, Object>> simpleProduct(@PathVariable("id")Integer id,
                                                             HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(bankService.bankDetail(ARE, id));
        return ARE.createResponseEntity();
    }
}
