package com.qudan.qingcloud.msqudan.controller;

import com.github.pagehelper.PageInfo;
import com.qudan.qingcloud.msqudan.entity.ProductConfig;
import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.service.ProductConfigService;
import com.qudan.qingcloud.msqudan.util.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msqudanbg/productconfig")
@Api(value = "msqudanbg", description = "商品奖金结算配置")
public class ProductConfigController {
    @Autowired
    private ProductConfigService productConfigService;

    /**
     * 分页查询商品奖金结算配置列表
     * @param productConfig
     * @return
     */
    @PostMapping("/findbypage")
    public ResponseResult<PageInfo<ProductConfig>> findByPage(@RequestBody ProductConfig productConfig){
        List<ProductConfig> productConfigs = productConfigService.findByPage(productConfig);
        return new ResponseResult<>(new PageInfo<>(productConfigs));
    }

    /**
     * 根据ID删除商品奖金结算配置
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseResult<Boolean> findById(@PathVariable("id") Integer id){
        Boolean result = false;
        int delFlag = productConfigService.delete(id);
        if(delFlag == 1){
            result = true;
        }
        return new ResponseResult<>(result);
    }

    /**
     * 修改商品奖金结算配置对象
     * @param productConfig
     * @return
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(@RequestBody ProductConfig productConfig){
        Boolean result = false;
        int updateFlag = productConfigService.update(productConfig);
        if(updateFlag == 1){
            result = true;
        }
        return new ResponseResult<>(result);
    }

    /**
     * 添加商品奖金结算配置对象
     * @param productConfig
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<Boolean> add(@RequestBody ProductConfig productConfig){
        Boolean result = false;
        int updateFlag = productConfigService.add(productConfig);
        if(updateFlag == 1){
            result = true;
        }
        return new ResponseResult<>(result);
    }
}
