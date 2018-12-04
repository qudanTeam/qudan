package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.mapper.UserMapper;
import com.qudan.qingcloud.msqudan.service.UserService;
import com.qudan.qingcloud.msqudan.util.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    //    @HystrixCommand  统计调取次数,每个接口都要有

    @Autowired
    private UserMapper userMapper;

    @Override
    @HystrixCommand
    public User findById(Integer id) {
        AssertUtils.greaterThanZero(id,"id不能为空");
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @HystrixCommand
    public List<User> findByPage(User user) {
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        return userMapper.selectByPage(user);
    }

    @Override
    public int update(User user) {
        AssertUtils.isNull(user,"修改对象不能为空");
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
