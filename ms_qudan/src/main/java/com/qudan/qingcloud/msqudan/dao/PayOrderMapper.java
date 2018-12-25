package com.qudan.qingcloud.msqudan.dao;

import com.qudan.qingcloud.msqudan.entity.PayOrder;
import com.qudan.qingcloud.msqudan.util.YHMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/25.
 */
@Component
@Mapper
public interface PayOrderMapper extends YHMapper<PayOrder>{
}
