package com.zhangheng.springboot.feign;

import com.zhangheng.springboot.utils.YHResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/24.
 *
 * 调用ms_qudan的微服务的api
 */
@FeignClient(value = "msqudan", url = "localhost:8763")
public interface UserInfoFeign {

//    @RequestMapping(value = "/msvideo/user/getUserInfoByUsername",method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    String getUserInfoByUsername(@RequestParam("username") String username);
//
//
//    /**
//     * 根据用户名获取所拥有的所有角色
//     */
//    @RequestMapping(value = "/msvideo/user/getUserAndRoleInfoByUsername",method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    String getUserAndRoleInfoByUsername(@RequestParam("username") String username);
//
//    /**
//     *获取后台所有的api
//     */
//    @RequestMapping(value = "/msvideo/user/getAllApi",method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    String getAllApi();
//
//    /**
//     * 根据apiid获取api所拥有的访问角色权限
//     */
//    @RequestMapping(value = "/msvideo/user/getApiRoleByApiId",method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    String getApiRoleByApiId(@RequestParam("apiid") Integer apiid);
//
//    /**
//     * 前端登录
//     */
//    @RequestMapping(value = "/msvideo/user/appLogin",method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    YHResult appLogin(@RequestParam("username") String username, @RequestParam("password") String password);
}
