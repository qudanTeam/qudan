package com.qudan.qingcloud.msqudan.service;

import com.qudan.qingcloud.msqudan.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByPage(Customer customer);
}
