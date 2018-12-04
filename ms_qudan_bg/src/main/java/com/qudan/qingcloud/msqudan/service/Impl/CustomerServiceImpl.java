package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.entity.Customer;
import com.qudan.qingcloud.msqudan.mapper.CustomerMapper;
import com.qudan.qingcloud.msqudan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    @HystrixCommand
    public List<Customer> findByPage(Customer customer) {
        PageHelper.startPage(customer.getPageNum(),customer.getPageSize());

        return  customerMapper.selectByPage(customer);
    }
}
