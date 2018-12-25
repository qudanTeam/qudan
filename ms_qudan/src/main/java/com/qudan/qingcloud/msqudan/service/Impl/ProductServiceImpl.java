package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.entity.Category;
import com.qudan.qingcloud.msqudan.entity.Product;
import com.qudan.qingcloud.msqudan.entity.ProductConfig;
import com.qudan.qingcloud.msqudan.entity.ShareManager;
import com.qudan.qingcloud.msqudan.mymapper.self.ApplyMapperSelf;
import com.qudan.qingcloud.msqudan.util.responses.HotProductVo;
import com.qudan.qingcloud.msqudan.util.responses.ProductListVo;
import com.qudan.qingcloud.msqudan.util.responses.ProductVo;
import com.qudan.qingcloud.msqudan.mymapper.self.ProductMapperSelf;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl {


    @Autowired
    ApplyMapperSelf applyMapperSelf;
    @Autowired
    ProductMapperSelf productMapperSelf;

    @Autowired
    CommonConfig config;

    @HystrixCommand
    public Map<String,Object> hotProducts(ApiResponseEntity ARE, Integer type){
        List<HotProductVo> hotProductVos  = productMapperSelf.getHotProduct(type);
        for (HotProductVo vo : hotProductVos){
            vo.setLogo(config.getQiniuImageUrl()+vo.getLogo());
            vo.setRecommendCount(1144);//TODO，推荐次数未界定
        }
        Map<String,Object> data = Maps.newHashMap();
        data.put("hots", hotProductVos);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> products(ApiResponseEntity ARE,
                                       Integer type,
                                       String keyword,
                                       Integer page,
                                       Integer perPage
                                       ){
        ComUtils.startPage(page, perPage);
        List<ProductListVo> list = productMapperSelf.getProductList(type, keyword);
        Map<String,Object> data = Maps.newHashMap();
        long total = 0;
        if(CollectionUtils.isEmpty(list)){
            list = Lists.newArrayList();
        } else {
            total =  ((Page) list).getTotal();
        }
        List<ProductListVo> card = Lists.newArrayList();
        List<ProductListVo> loan = Lists.newArrayList();
        for (ProductListVo vo : list){
            vo.setLogo(config.getQiniuImageUrl()+vo.getLogo());
            if(vo.getProductType() == 1){
                card.add(vo);
            } else if(vo.getProductType() == 2){
                loan.add(vo);
            }
        }
        data.put("card", card);
        data.put("loan", loan);
        data.put("total", total);
        return data;
    }


    public Map<String,Object> productDetail(ApiResponseEntity ARE, Integer id){
        ProductVo productVo = new ProductVo();
        Product product = productMapperSelf.selectByPrimaryKey(id);
        if(product == null){
            ARE.addInfoError("id.isNotExist", "id对应的商品不存在");
        }
        List<ShareManager> shares = productMapperSelf.getShareManagerList(id);
        if(CollectionUtils.isEmpty(shares)){
            shares = Lists.newArrayList();
        }
        productVo.setShares(shares);
        product.setLogo(ComUtils.addPrefixToImg(product.getLogo(), config.getQiniuImageUrl()));
        product.setProgressQueryImg(ComUtils.addPrefixToImg(product.getLogo(), config.getQiniuImageUrl()));
        product.setApplyTpImg(ComUtils.addPrefixToImg(product.getApplyTpImg(), config.getQiniuImageUrl()));
        product.setDetailHeaderImg(ComUtils.addPrefixToImg(product.getDetailHeaderImg(), config.getQiniuImageUrl()));
        product.setCardLongImg(ComUtils.addPrefixToImg(product.getCardLongImg(), config.getQiniuImageUrl()));
        product.setProductShowImg(ComUtils.addPrefixToImg(product.getProductShowImg(), config.getQiniuImageUrl()));
        product.setCardProgressImg(ComUtils.addPrefixToImg(product.getCardProgressImg(), config.getQiniuImageUrl()));
        productVo.setProduct(product);

        List<Category> categories = productMapperSelf.selectCatByProductId(id);
        if(product.getProductType() == 1){
            productVo.setBank(categories.get(0).getName());
            productVo.setBankQueryLink(categories.get(0).getGetLink());
            productVo.setLoanTag(Lists.newArrayList());
        } else {
            List<String> tags = Lists.newArrayList();
            for (Category category:categories) {
                tags.add(category.getName());
            }
            productVo.setLoanTag(tags);
        }
        if(ARE.getUserId() != null){
            productVo.setApply(applyMapperSelf.selectApplyByUserIdAndProductId(product.getId(), ARE.getUserId()) == null);
        }
        ProductConfig config = productMapperSelf.getProductConfig(id);
        productVo.setConfig(config);
        Map<String,Object> data = Maps.newHashMap();
        data.put("detail", productVo);
        return data;
    }

    //TODO 搜索记录

}
