package com.qudan.qingcloud.msqudan.mapper;

import com.qudan.qingcloud.msqudan.entity.ProductConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductConfig record);

    List<ProductConfig> selectByPage();

    int update(ProductConfig record);

}