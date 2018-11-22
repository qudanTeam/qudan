package com.zhangheng.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/27.
 * 测试读取远程配置中的配置
 */

@RestController
@RefreshScope//刷新配置
public class TestController {

//    @Value("${profile}")
//    private String profile;
//
//    @RequestMapping("/profile")
//    public String from() {
//        return this.profile;
//    }


    @RequestMapping("/")
    public String index() {
        return "权限获取成功!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "不验证哦";
    }

//    @PreAuthorize("hasAuthority('TEST')")//有TEST权限的才能访问
    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }

//    @PreAuthorize("hasAuthority('ADMIN')")//必须要有ADMIN权限的才能访问
    @RequestMapping("/authorize")
    public String authorize() {
        return "有权限访问";
    }

    @RequestMapping("/authorize/test")
    public String test() {
        return "用于测试正则匹配路径";
    }

    /**这里注意的是，TEST与ADMIN只是权限编码，可以自己定义一套规则，根据实际情况即可*/

}
