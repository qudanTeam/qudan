package com.qudan.qingcloud.msqudan.controller;


import com.qudan.qingcloud.msqudan.service.Impl.BankServiceImpl;
import com.qudan.qingcloud.msqudan.service.Impl.UserFinanceServiceImpl;
import com.qudan.qingcloud.msqudan.service.Impl.UserServiceImpl;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.params.OrderParams;
import com.qudan.qingcloud.msqudan.util.requestBody.QueryBankRB;
import com.qudan.qingcloud.msqudan.util.requestBody.TxRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/msqudan/api")//窄化请求地址
public class UserCenterController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserFinanceServiceImpl userFinanceService;


    @GetMapping("/user/verify/trigger")
    public ResponseEntity<Map<String, Object>> mobileTrigger(@RequestBody QueryBankRB RB,
                                                       HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        userFinanceService.mobileTrigger(ARE, RB);
        return ARE.createResponseEntity();
    }

    /**
     * 收入记录
     * sendStatus 1-待结算，2已结算
     * @return
     */
    @GetMapping("/user/revenues")
    public ResponseEntity<Map<String, Object>> revenue(@RequestParam(value = "send_status", required = false) Integer sendStatus,
                                                       HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        if(ComUtils.validPage(ARE, request)){
            Integer userId = LocalUserHelper.getUserId();
            ARE.setUserId(userId);
            ARE.setData(userService.revenueRecords(ARE, sendStatus, ComUtils.getPage(request), ComUtils.getPerPage(request)));
        }
        return ARE.createResponseEntity();
    }

    /**
     * 我的订单
     * @return
     */
    @GetMapping("/user/orders")
    public ResponseEntity<Map<String, Object>> order(OrderParams orderParams, HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        if(ComUtils.validPage(ARE, request)) {
            Integer userId = LocalUserHelper.getUserId();
            ARE.setUserId(userId);
            ARE.setData(userService.orders(ARE, orderParams, ComUtils.getPage(request), ComUtils.getPerPage(request)));
        }
        return ARE.createResponseEntity();
    }

    /**
     * @param status 1-待审核，2-已审核，3-已拒绝
     * @param request
     * @return
     */
    @GetMapping("/user/txrecords")
    public ResponseEntity<Map<String, Object>> txrecords(@RequestParam(value = "status", required = false) Integer status,
                                                         HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        if(ComUtils.validPage(ARE, request)){
            Integer userId = LocalUserHelper.getUserId();
            ARE.setUserId(userId);
            ARE.setData(userService.txrecords(ARE, status, ComUtils.getPage(request), ComUtils.getPerPage(request)));
        }
        return ARE.createResponseEntity();
    }



    /**
     * 团队佣金
     * @param ym 月份必须是yyyy-MM的格式
     * @param request
     * @return
     */
    @GetMapping("/user/team")
    public ResponseEntity<Map<String, Object>> team(@RequestParam(value = "ym", required = false) String ym,
                                                    HttpServletRequest request) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        if(ComUtils.validPage(ARE, request)){
            Integer userId = LocalUserHelper.getUserId();
            ARE.setUserId(userId);
            ARE.setData(userService.team(ARE, ym, ComUtils.getPage(request), ComUtils.getPerPage(request)));
        }
        return ARE.createResponseEntity();
    }

    @PostMapping("user/tx")
    public ResponseEntity<Map<String, Object>> team(@RequestBody TxRB txRB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        Integer userId = LocalUserHelper.getUserId();
        ARE.setUserId(userId);
        ARE.setData(userFinanceService.txRB(ARE, txRB));
        return ARE.createResponseEntity();
    }
}
