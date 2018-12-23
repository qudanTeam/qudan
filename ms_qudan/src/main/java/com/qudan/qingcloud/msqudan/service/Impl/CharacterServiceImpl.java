package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.entity.*;
import com.qudan.qingcloud.msqudan.mymapper.self.AgentMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.VipMapperSelf;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CharacterServiceImpl {

    @Autowired
    VipMapperSelf vipMapperSelf;

    @Autowired
    AgentMapperSelf agentMapperSelf;

    @Autowired
    CommonConfig config;

    @HystrixCommand
    public Map<String,Object> vips(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        List<VipConfig> vips = vipMapperSelf.selectVips();
        for (VipConfig vip : vips){
            vip.setVipLogo(ComUtils.addPrefixToImg(vip.getVipLogo(), config.getQiniuImageUrl()));
        }
        data.put("vips", vips);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> agents(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        List<AgentConfig> agentConfigs = agentMapperSelf.selectConfigs();
        data.put("agents", agentConfigs);
        return data;
    }

    public AgentConfig getAgentByUserId(Integer userId){
        return agentMapperSelf.selectConfigByUserId(userId);
    }

    public VipConfig getVipByUserId(Integer userId){
        return null;
    }
}
