package com.qudan.qingcloud.msqudan.service;

import com.qudan.qingcloud.msqudan.entity.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);


    List<User> findByPage(User user);

    int update(User user);
}
