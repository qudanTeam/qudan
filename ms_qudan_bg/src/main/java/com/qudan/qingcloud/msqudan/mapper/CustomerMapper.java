package com.qudan.qingcloud.msqudan.mapper;

import com.qudan.qingcloud.msqudan.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CustomerMapper {

    Customer selectByPrimaryKey(Integer id);

    List<Customer> selectByPage(Customer customer);

}