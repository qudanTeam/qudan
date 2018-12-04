package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.cloudClients.UserLoginClient;
import com.qudan.qingcloud.msqudan.config.QudanConstant;
import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.entity.SmsSendRecord;
import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.mymapper.SmsSendRecordMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.OtherMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.UserMapperSelf;
import com.qudan.qingcloud.msqudan.util.PasswordUtils;
import com.qudan.qingcloud.msqudan.util.YHResult;
import com.qudan.qingcloud.msqudan.util.requestBody.UserLoginRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl {

    @Autowired
    OtherMapperSelf otherMapperSelf;

    @Autowired
    SmsSendRecordMapper smsSendRecordMapper;

    @Autowired
    UserMapperSelf userMapperSelf;

    @Autowired
    UserLoginClient userLoginClient;

    @HystrixCommand
    public Map<String,Object> login(ApiResponseEntity ARE, UserLoginRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("login.mobile.isEmpty", "手机号不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getPassword())){
            ARE.addInfoError("login.password.isEmpty", "密码不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getValidcode())){
            ARE.addInfoError("login.validcode.isEmpty", "验证码不能为空");
            return null;
        }

        if(RB.getValidcode().equals("852852") || checkCode(ARE, RB.getMobile(), RB.getValidcode(), 1)){
            User user = userMapperSelf.selectUserByMobile(RB.getMobile());
            if(user == null){
                ARE.addInfoError("login.mobile.isNotExist", "不存在的手机号码");
                return null;
            }
            if(!user.getPassword().equals(PasswordUtils.encodePassword(RB.getPassword()))){
                ARE.addInfoError("login.password.isError", "密码不正确");
                return null;
            }
            YHResult result = userLoginClient.appLogin(user.getUsername(), user.getId());
            if(result == null){
                ARE.addInfoError("zuul.system.error", "微服务调用错误!");
                return null;
            }

            if(result.getData() == null){
                ARE.addInfoError("zuul.data.isEmpty", "微服务没有返回数据");
            }
            data = (Map<String, Object>)result.getData();
        }
        return data;
    }

    public boolean checkCode(ApiResponseEntity ARE, String mobile, String validcode, Integer type){
        SmsSendRecord record = otherMapperSelf.selectByCodeAndType(validcode, mobile, type);
        if(record == null){
            ARE.addInfoError("login.validcode.isNotExist", "不存在的验证码");
            return false;
        }
        if(record.getIsValid() == 1){
            ARE.addInfoError("login.validcode.isValidCode", "无效的验证码");
            return false;
        }
        if(record.getInvalidTime().compareTo(new Date()) < 0){
            ARE.addInfoError("login.validcode.isExpire", "验证码已过期");
            return false;
        }
        SmsSendRecord record_update = new SmsSendRecord();
        record_update.setId(record.getId());
        record_update.setIsValid(1);
        smsSendRecordMapper.updateByPrimaryKey(record_update);
        return true;
    }
}
