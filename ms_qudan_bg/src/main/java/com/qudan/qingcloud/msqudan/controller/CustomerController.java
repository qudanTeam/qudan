package com.qudan.qingcloud.msqudan.controller;

import com.github.pagehelper.PageInfo;
import com.qudan.qingcloud.msqudan.entity.Customer;
import com.qudan.qingcloud.msqudan.service.CustomerService;
import com.qudan.qingcloud.msqudan.util.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/msqudanbg/customer")
@Api(value = "msqudanbg", description = "用户列表-2B")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 分页条件查询用户列表
     * @param customer 广告主对象
     * @return
     */
    @PostMapping("/findbypage")
    public ResponseResult<PageInfo<Customer>> findByPage(@RequestBody Customer customer){
        List<Customer> customers = customerService.findByPage(customer);
        return new ResponseResult<>(new PageInfo<>(customers));
    }
}
