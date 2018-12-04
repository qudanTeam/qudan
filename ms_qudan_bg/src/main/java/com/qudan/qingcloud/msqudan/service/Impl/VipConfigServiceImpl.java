package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.entity.VipConfig;
import com.qudan.qingcloud.msqudan.mapper.VipConfigMapper;
import com.qudan.qingcloud.msqudan.service.VipConfigService;
import com.qudan.qingcloud.msqudan.util.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VipConfigServiceImpl implements VipConfigService {

    @Autowired
    private VipConfigMapper vipConfigMapper;

    @Override
    @HystrixCommand
    public int add(VipConfig vipConfig) {
        AssertUtils.notNull(vipConfig,"添加VIP对象为空");
        return vipConfigMapper.insert(vipConfig);
    }

    @Override
    @HystrixCommand
    public int delete(Integer id) {
        AssertUtils.greaterThanZero(id,"要删除的vip对象id不能为空");
        return vipConfigMapper.delete(id);
    }

    @Override
    @HystrixCommand
    public int update(VipConfig vipConfig) {
        AssertUtils.notNull(vipConfig,"修改VIP对象不能为空");
        AssertUtils.greaterThanZero(vipConfig.getId(),"修改VIP对象ID不能为空");
        return vipConfigMapper.update(vipConfig);
    }

    @Override
    @HystrixCommand
    public List<VipConfig> findByPage(VipConfig vipConfig) {
        PageHelper.startPage(vipConfig.getPageNum(),vipConfig.getPageSize());
        return vipConfigMapper.selectByPage();
    }
}
