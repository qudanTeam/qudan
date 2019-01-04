package com.qudan.qingcloud.msqudan.dao;

import com.qudan.qingcloud.msqudan.entity.PayOrder;
import com.qudan.qingcloud.msqudan.entity.WeixinBinding;
import com.qudan.qingcloud.msqudan.util.YHMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2019/1/4.
 */
@Component
@Mapper
public interface WeixinBindingMapper extends YHMapper<WeixinBinding> {
}
