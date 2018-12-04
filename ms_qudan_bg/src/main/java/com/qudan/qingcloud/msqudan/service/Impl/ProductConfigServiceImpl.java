package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.entity.ProductConfig;
import com.qudan.qingcloud.msqudan.mapper.ProductConfigMapper;
import com.qudan.qingcloud.msqudan.service.ProductConfigService;
import com.qudan.qingcloud.msqudan.util.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductConfigServiceImpl implements ProductConfigService {

    @Autowired
    private ProductConfigMapper productConfigMapper;

    @Override
    @HystrixCommand
    public int add(ProductConfig productConfig) {
        AssertUtils.notNull(productConfig,"添加对象不能为空");
        return productConfigMapper.insert(productConfig);
    }

    @Override
    @HystrixCommand
    public int update(ProductConfig productConfig) {
        AssertUtils.notNull(productConfig,"修改对象不能为空");
        AssertUtils.greaterThanZero(productConfig.getId(),"修改对象ID不能为空");
        return productConfigMapper.update(productConfig);
    }

    @Override
    @HystrixCommand
    public List<ProductConfig> findByPage(ProductConfig productConfig) {
        PageHelper.startPage(productConfig.getPageNum(),productConfig.getPageSize());
        return productConfigMapper.selectByPage();
    }

    @Override
    @HystrixCommand
    public int delete(Integer id) {
        AssertUtils.greaterThanZero(id,"ID不能为空");
        return productConfigMapper.deleteByPrimaryKey(id);
    }
}
