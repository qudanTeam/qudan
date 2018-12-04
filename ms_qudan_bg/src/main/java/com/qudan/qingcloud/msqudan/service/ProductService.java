package com.qudan.qingcloud.msqudan.service;

import com.qudan.qingcloud.msqudan.entity.web.ProductCategory;

import java.util.List;

public interface ProductService {
    List<ProductCategory> findByPage(ProductCategory productCategory);

}
