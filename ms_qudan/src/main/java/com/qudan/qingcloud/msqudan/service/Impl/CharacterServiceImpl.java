package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.entity.*;
import com.qudan.qingcloud.msqudan.mymapper.AgentMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.AgentMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.UserMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.VipMapperSelf;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    UserMapperSelf userMapperSelf;

    @Autowired
    AgentMapper agentMapper;

    public Map<String,Object> vipCheck(ApiResponseEntity ARE, Integer vipId){
        Date date = new Date();
        Map<String,Object> data = Maps.newHashMap();
        User user = userMapperSelf.selectById(ARE.getUserId());
        VipRecord vipRecord = vipMapperSelf.getVipRecordByUserId(ARE.getUserId());
        VipConfig config = vipMapperSelf.selectByPrimaryKey(vipId);
        if(config == null){
            ARE.addInfoError("vip.isNotExist", "不存在的VIP等级");
            return null;
        }
        boolean isVip = false;
        if(vipRecord  != null && vipRecord.getEndTime().compareTo(date) > 0){
            isVip = true;
        }
        if(isVip && user.getVipLevel() > config.getVipLevel()){
            ARE.addInfoError("vip.overCurrent", "当期VIP等级已经超过需要购买的VIP等级");
            return null;
        }
        return null;
    }

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

    public void becomeAgent(Integer recommendUserId){
        Date date = new Date();
        User user = userMapperSelf.selectById(recommendUserId);
        Integer currentLevel = user.getAgentLevel();
        if(currentLevel == null){
            currentLevel = 0;
        }
        int shareCt = userMapperSelf.countShareCt(recommendUserId);
        int taskDoneCt = userMapperSelf.countTaskDoneCt(user.getInviteCode());
        int inviteCt = userMapperSelf.countInviteCt(recommendUserId);
        List<AgentConfig> configs = agentMapperSelf.selectConfigs();
        int configLevel = 0;
        for (AgentConfig config : configs){
            boolean isShareEnough = true;
            boolean isTaskDoneEnough = true;
            boolean isInviteEnough = true;
            if(config.getShareLimit() != null){
                isShareEnough = shareCt >= config.getShareLimit();
            }
            if(config.getTaskDoneLimit() != null){
                isTaskDoneEnough = taskDoneCt >= config.getTaskDoneLimit();
            }
            if(config.getInviteLimit() != null){
                isInviteEnough = inviteCt >= config.getInviteLimit();
            }
            if(!(isShareEnough && isTaskDoneEnough && isInviteEnough)){
                break;
            }
            configLevel = config.getLevel();
        }
        if(configLevel > currentLevel.intValue()){
            User user_update = new User();
            user_update.setId(recommendUserId);
            user_update.setModifyTime(new Date());
            user_update.setAgentLevel(configLevel);
            if(user.getAgentId() == null || user.getAgentId() <= 0){
                Agent agent = new Agent();
                agent.setBeignAgentTime(date);
                agent.setCreateTime(date);
                agent.setModifyTime(date);
                agent.setLevel(configLevel);
                agent.setUserId(recommendUserId);
                agentMapper.insertSelective(agent);
                user_update.setAgentId(agent.getId());
            } else {
                Agent agent_update = new Agent();
                agent_update.setId(user.getAgentId());
                agent_update.setLevel(configLevel);
                agent_update.setModifyTime(date);
                agentMapper.updateByPrimaryKeySelective(agent_update);
            }
            if(user.getUserType() == null || user.getUserType() == 0){
                user_update.setUserType(2);
            } else if(user.getUserType() != null && user.getUserType() == 1){
                user_update.setUserType(3);
            }
            userMapperSelf.updateByPrimaryKeySelective(user_update);
        }
    }

    public AgentConfig getAgentByUserId(Integer userId){
        return agentMapperSelf.selectConfigByUserId(userId);
    }

    public VipConfig getVipByUserId(Integer userId){
        Date date = new Date();
        VipRecord vipRecord = vipMapperSelf.getVipRecordByUserId(userId);
        boolean isVip = false;
        if(vipRecord  != null && vipRecord.getEndTime().compareTo(date) > 0){
            isVip = true;
        }
        if(isVip){
            return vipMapperSelf.selectByPrimaryKey(vipRecord.getVipConfigId());
        }
        return null;
    }
}
