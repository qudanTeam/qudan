package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.mymapper.BannerMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.BankMapperSelf;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.BankSimple;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BankServiceImpl {

    @Autowired
    BankMapperSelf bankMapperSelf;

    @Autowired
    CommonConfig config;

    @HystrixCommand
    public Map<String,Object> banks(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        List<Category> banks = bankMapperSelf.categories(1);
        for (Category category : banks){
            category.setLogo(config.getQiniuImageUrl() + category.getLogo());
            if(category.getNeedMobileVerifyCode() == null){
                category.setNeedMobileVerifyCode(new Byte("0"));
            }
            if(category.getNeedVerifyCode() == null){
                category.setNeedVerifyCode(new Byte("0"));
            }
        }
        data.put("banks", banks);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> trggierMobileVerify(ApiResponseEntity ARE, Integer bankId){
        BankSimple bankSimple = bankMapperSelf.selectSimpleByProductId(bankId);
        if(StringUtils.isNotBlank(bankSimple.getLogo())){
            bankSimple.setLogo(ComUtils.addPrefixToImg(bankSimple.getLogo(), config.getQiniuImageUrl()));
        }
        if(bankSimple.getNeedMobileVerifyCode() != null && bankSimple.getNeedMobileVerifyCode() == 1){

        } else {
            ARE.addInfoError("mobileverify.notneed", "不需要手机验证码");
        }
        return null;
    }

    @HystrixCommand
    public Map<String,Object> bankDetail(ApiResponseEntity ARE, Integer catId){
        Map<String,Object> data = Maps.newHashMap();
        BankSimple bankSimple = bankMapperSelf.selectSimpleByProductId(catId);
        if(StringUtils.isNotBlank(bankSimple.getLogo())){
            bankSimple.setLogo(ComUtils.addPrefixToImg(bankSimple.getLogo(), config.getQiniuImageUrl()));
        }
        if(bankSimple.getNeedMobileVerifyCode() == null){
            bankSimple.setNeedMobileVerifyCode(0);
        }
        if(bankSimple.getNeedVerifyCode() == null){
            bankSimple.setNeedVerifyCode(0);
        }
        data.put("bank", bankSimple);
        return data;
    }
}
