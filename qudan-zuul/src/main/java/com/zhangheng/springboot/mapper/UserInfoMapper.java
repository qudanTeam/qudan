package com.zhangheng.springboot.mapper;

import com.zhangheng.springboot.entity.User;
import com.zhangheng.springboot.utils.YHMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/26.
 */
@Component
@Mapper
public interface UserInfoMapper extends YHMapper<User> {
}
