package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.entity.web.ProductCategory;
import com.qudan.qingcloud.msqudan.mapper.ProductMapper;
import com.qudan.qingcloud.msqudan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    @HystrixCommand
    public List<ProductCategory> findByPage(ProductCategory productCategory) {
        PageHelper.startPage(productCategory.getPageNum(),productCategory.getPageSize());
        return  productMapper.selectByPage(productCategory);
    }
}
