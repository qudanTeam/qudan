package com.qudan.qingcloud.msqudan.controller;

import com.github.pagehelper.PageInfo;
import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.service.UserService;
import com.qudan.qingcloud.msqudan.util.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msqudanbg/user")
@Api(value = "msqudanbg", description = "用户列表-2C")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 条件分页查询
     * @param user 用户对象
     * @return
     */
    @PostMapping("/findbypage")
    public ResponseResult<PageInfo<User>> findByPage(@RequestBody User user){
        List<User> users = userService.findByPage(user);
        return new ResponseResult<>(new PageInfo<>(users));
    }

    /**
     * 根据ID查询对象
     * @param id 用户id
     * @return
     */
    @PostMapping("/find/{id}")
    public ResponseResult<User> findById(@PathVariable("id") Integer id){
        User user = userService.findById(id);
        return new ResponseResult<>(user);
    }

    /**
     * 修改用户
     * @param user 用户对象
     * @return
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(@RequestBody User user){
        Boolean result = false;
        int updateFlag = userService.update(user);
        if(updateFlag == 1){
            result = true;
        }
        return new ResponseResult<>(result);
    }
}
