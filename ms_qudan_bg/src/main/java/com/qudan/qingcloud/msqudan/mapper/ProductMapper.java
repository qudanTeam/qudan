package com.qudan.qingcloud.msqudan.mapper;

import com.qudan.qingcloud.msqudan.entity.Product;
import com.qudan.qingcloud.msqudan.entity.web.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    List<ProductCategory> selectByPage(ProductCategory productCategory);


    int update(Product record);
}