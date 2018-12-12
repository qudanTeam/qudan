package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.entity.Apply;
import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.entity.Product;
import com.qudan.qingcloud.msqudan.mymapper.ApplyMapper;
import com.qudan.qingcloud.msqudan.mymapper.ProductMapper;
import com.qudan.qingcloud.msqudan.mymapper.TradeTypeMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.ApplyMapperSelf;
import com.qudan.qingcloud.msqudan.util.requestBody.ApplyRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.QudanHashIdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl {

    @Autowired
    private ApplyMapperSelf applyMapperSelf;

    @Autowired
    private TradeTypeMapper tradeTypeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserServiceImpl userService;

    @HystrixCommand
    public Map<String,Object> loanApply(ApiResponseEntity ARE, @RequestBody ApplyRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(checkApplyRB(ARE, RB)){
            createByRB(RB, ARE.getUserId());
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> cardApply(ApiResponseEntity ARE, @RequestBody ApplyRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(checkApplyRB(ARE, RB)){
            createByRB(RB, ARE.getUserId());
        }
        //TODO 申请之后是否产生分佣
        return data;
    }

    private boolean checkApplyRB(ApiResponseEntity ARE,ApplyRB RB){
        if(StringUtils.isBlank(RB.getIdNo())){
            ARE.addInfoError("idNo.isEmpty", "身份证号不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("mobile.isEmpty", "手机号不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getValidcode())){
            ARE.addInfoError("validcode.isEmpty", "手机号不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getName())){
            ARE.addInfoError("name.isEmpty", "用户姓名不能能为空");
            return false;
        }
        if(RB.getProductId() == null){
            ARE.addInfoError("product.isEmpty", "产品id不能为空");
            return false;
        }
        Product product = productMapper.selectByPrimaryKey(RB.getProductId());
        if(product == null){
            ARE.addInfoError("product.isNotExist", "不存在的产品Id");
            return false;
        }
        Apply apply = applyMapperSelf.selectApplyByUserIdAndProductId(product.getId(), ARE.getUserId());
        if(apply != null){
            ARE.addInfoError("app.isExist", "不能重复申请！");
            return false;
        }
        return userService.checkCode(ARE, RB.getMobile(), RB.getValidcode(), 4, true);
    }

    private Apply createByRB(ApplyRB RB, Integer userId){
        Date date = new Date();
        Apply apply = new Apply();
        apply.setUserId(userId);
        apply.setProductId(RB.getProductId());
        apply.setCreateTime(date);
        apply.setModifyTime(date);
        apply.setMobile(RB.getMobile());
        apply.setName(RB.getName());
        apply.setIdNo(RB.getIdNo());
        apply.setStatus(1);
        apply.setOfficialStatus(0);
        apply.setLastOfficialQuery(null);
        apply.setRejectReason(null);
        apply.setInviteCode(RB.getInviteCode());
        applyMapperSelf.insert(apply);
        Apply apply_update = new Apply();
        apply_update.setId(apply.getId());
        apply_update.setApplyIdCode(QudanHashIdUtils.encodeHashId(apply.getId()));
        applyMapperSelf.updateByPrimaryKeySelective(apply_update);
        return apply;
    }
}
