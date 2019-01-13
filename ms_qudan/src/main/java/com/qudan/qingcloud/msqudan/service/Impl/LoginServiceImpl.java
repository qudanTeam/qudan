package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.emailAndSms.sms.SmsService;
import com.qudan.qingcloud.msqudan.entity.SmsSendRecord;
import com.qudan.qingcloud.msqudan.mymapper.SmsSendRecordMapper;
import com.qudan.qingcloud.msqudan.util.DateUtil;
import com.qudan.qingcloud.msqudan.util.RandomUtils;
import com.qudan.qingcloud.msqudan.util.requestBody.UserLoginRB;
import com.qudan.qingcloud.msqudan.util.requestBody.ValidcodeRB;
import com.qudan.qingcloud.msqudan.util.requestBody.VerifyRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class LoginServiceImpl {
    @Autowired
    SmsSendRecordMapper smsSendRecordMapper;

    @Autowired
    SmsService smsService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private UserServiceImpl userService;

    @HystrixCommand
    public Map<String,Object> verify(ApiResponseEntity ARE, VerifyRB verifyRB){
        Map<String,Object> data = Maps.newHashMap();
        userService.checkCode(ARE, verifyRB.getMobile(), verifyRB.getValidcode(), verifyRB.getType(),false);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> mobileValidcode(ApiResponseEntity ARE, ValidcodeRB validcodeRB){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(validcodeRB.getMobile())){
            ARE.addInfoError("mobile.isNotExist", "手机号为空");
            return data;
        }
        if(validcodeRB.getType() == null){
            ARE.addInfoError("type.isNotExist", "发送类型为空");
            return data;
        }
        String code = RandomUtils.generateNumString(4);
        Date date = new Date();
        SmsSendRecord smsSendRecord = new SmsSendRecord();
        smsSendRecord.setCode(code);
        smsSendRecord.setIsValid(0);
        smsSendRecord.setCreateTime(new Date());
        smsSendRecord.setSendType(validcodeRB.getType());
        smsSendRecord.setMobile(validcodeRB.getMobile());
        smsSendRecord.setInvalidTime(DateUtil.getPerDate(date, "m", 5));
        smsSendRecordMapper.insertSelective(smsSendRecord);

        //发送短信
        taskExecutor.execute(()->{
            Map<String,Object> params = Maps.newHashMap();
            params.put("code", code);
            params.put("time", "5");
            smsService.sendThing(validcodeRB.getMobile(), "qeKhW1", params);
        });
        return data;
    }
}
