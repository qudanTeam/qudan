package com.qudan.qingcloud.msqudan.service;

import com.qudan.qingcloud.msqudan.entity.ProductConfig;

import java.util.List;

public interface ProductConfigService {
    int add(ProductConfig productConfig);
    int update(ProductConfig productConfig);
    List<ProductConfig> findByPage(ProductConfig productConfig);
    int delete(Integer id);
}
