package com.qudan.qingcloud.msqudan.mapper;

import com.qudan.qingcloud.msqudan.entity.VipConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VipConfigMapper {
    int delete(Integer id);
    int insert(VipConfig record);
    VipConfig selectByPrimaryKey(Integer id);
    int update(VipConfig record);
    List<VipConfig> selectByPage();

}