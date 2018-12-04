package com.qudan.qingcloud.msqudan.controller;

import com.github.pagehelper.PageInfo;
import com.qudan.qingcloud.msqudan.entity.Product;
import com.qudan.qingcloud.msqudan.entity.VipConfig;
import com.qudan.qingcloud.msqudan.entity.web.ProductCategory;
import com.qudan.qingcloud.msqudan.service.ProductService;
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
@Api(value = "msqudanbg", description = "商品管理")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页条件查询商品列表
     * @param productCategory
     * @return
     */
    @PostMapping("/findbypage")
    public ResponseResult<PageInfo<ProductCategory>> findByPage(@RequestBody ProductCategory productCategory){
        List<ProductCategory> productCategories = productService.findByPage(productCategory);
        return new ResponseResult<>(new PageInfo<>(productCategories));
    }


}
