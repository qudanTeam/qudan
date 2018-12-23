package com.qudan.qingcloud.msqudan.service.Impl;

import com.qudan.qingcloud.msqudan.entity.Product;
import com.qudan.qingcloud.msqudan.entity.ProductConfig;
import com.qudan.qingcloud.msqudan.mymapper.ProductMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.ProductMapperSelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RewordServiceImpl {

    @Autowired
    ProductMapperSelf productMapperSelf;

    public BigDecimal getBasePrice(Product product, ProductConfig config){
        return product.getBaseSalary();
    }

    private BigDecimal getLevelPrice(Integer inviteNum, Product product){
        BigDecimal reword = BigDecimal.ZERO;
        if(product.getaBegin() <= inviteNum  && inviteNum < product.getaLimit()){
            return product.getaLevelReward();
        } else if(product.getbBegin() <= inviteNum  && inviteNum < product.getbLimit()){
            return product.getbLevelReward();
        } else if(product.getcStart() <= inviteNum  && inviteNum < product.getcLimit()){
            return product.getcLevelReward();
        }
        return null;
    }
}
