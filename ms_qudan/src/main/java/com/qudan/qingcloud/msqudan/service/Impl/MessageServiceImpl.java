package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.entity.Message;
import com.qudan.qingcloud.msqudan.mymapper.self.MessageMapperSelf;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.MessageVo;
import com.qudan.qingcloud.msqudan.util.responses.OrderVos;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl {

    @Autowired
    CommonConfig config;
    @Autowired
    MessageMapperSelf messageMapperSelf;

    @HystrixCommand
    public Map<String,Object> messages(ApiResponseEntity ARE,Integer page, Integer per_page){
        Map<String,Object> data = Maps.newHashMap();
        long total = 0;
        ComUtils.startPage(page, per_page);
        List<MessageVo> list = messageMapperSelf.selectListByUserId(ARE.getUserId());
        if(CollectionUtils.isEmpty(list)){
            list = Lists.newArrayList();
        } else {
            for (MessageVo vo : list){
                if(vo.getMsgLogo()!= null){
                    vo.setMsgLogo(ComUtils.addPrefixToImg(vo.getMsgLogo(), config.getQiniuImageUrl()));
                }
            }
            total =  ((Page) list).getTotal();
        }
        data.put("rows", list);
        data.put("total", total);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> messageGet(ApiResponseEntity ARE,Integer id){
        Map<String,Object> data = Maps.newHashMap();
        if(id == null){
            ARE.addInfoError("message.id.isEmpty", "消息ID为空");
            return null;
        }
        MessageVo messageVo = messageMapperSelf.selectById(id);
        if(messageVo.getMsgLogo()!= null){
            messageVo.setMsgLogo(ComUtils.addPrefixToImg(messageVo.getMsgLogo(), config.getQiniuImageUrl()));
        }
        if(messageVo == null){
            ARE.addInfoError("message.id.isNotExists", "消息ID不存在");
            return null;
        }
        if(messageVo.getUserId() != ARE.getUserId()){
            ARE.addInfoError("message.not.yours", "不是属于你的消息");
            return null;
        }
        data.put("message", messageVo);
        return data;
    }


    @HystrixCommand
    public Map<String,Object> messageDelete(ApiResponseEntity ARE,Integer id){
        Map<String,Object> data = Maps.newHashMap();
        if(id == null){
            ARE.addInfoError("message.id.isEmpty", "消息ID为空");
            return null;
        }
        MessageVo messageVo = messageMapperSelf.selectById(id);
        if(messageVo == null){
            ARE.addInfoError("message.id.isNotExists", "消息ID不存在");
            return null;
        }
        if(messageVo.getUserId() != ARE.getUserId()){
            ARE.addInfoError("message.not.yours", "不是属于你的消息");
            return null;
        }
        Message mess_update = new Message();
        mess_update.setId(id);
        mess_update.setIsUserDelete(1);
        messageMapperSelf.updateByPrimaryKeySelective(mess_update);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> messageClear(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        messageMapperSelf.updateMessageDelete(ARE.getUserId());
        return data;
    }

}
