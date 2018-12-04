package com.qudan.qingcloud.msqudan.controller;

import com.github.pagehelper.PageInfo;
import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.entity.VipConfig;
import com.qudan.qingcloud.msqudan.service.VipConfigService;
import com.qudan.qingcloud.msqudan.util.PageUtils;
import com.qudan.qingcloud.msqudan.util.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msqudanbg/vipconfig")
@Api(value = "msqudanbg", description = "VIP配置")
public class VipConfigController {
    @Autowired
    private VipConfigService vipConfigService;

    /**
     * 分页查询VIP列表
     * @param vipConfig VIP配置类  传分页属性就行
     * @return
     */
    @PostMapping("/findbypage")
    public ResponseResult<PageInfo<VipConfig>> findByPage(@RequestBody VipConfig vipConfig){
        List<VipConfig> vipConfigs = vipConfigService.findByPage(vipConfig);
        return new ResponseResult<>(new PageInfo<>(vipConfigs));
    }

    /**
     * 添加VIP配置
     * @param vipConfig
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<Boolean> add(@RequestBody VipConfig vipConfig){
        Boolean result = false;
        int addFlag = vipConfigService.add(vipConfig);
        if(addFlag == 1){
            result = true;
        }
        return new ResponseResult<>(result);
    }

    /**
     * 修改VIP对象
     * @param vipConfig
     * @return
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(@RequestBody VipConfig vipConfig){
        Boolean result = false;
        int updateFlag = vipConfigService.update(vipConfig);
        if(updateFlag == 1){
            result = true;
        }
        return new ResponseResult<>(result);
    }

    /**
     * 删除VIP对象
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseResult<Boolean> delete(@PathVariable("id") Integer id){
        Boolean result = false;
        int delFlag = vipConfigService.delete(id);
        if(delFlag == 1){
            result = true;
        }
        return new ResponseResult<>(result);
    }

}
