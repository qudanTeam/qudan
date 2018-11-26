package com.qudan.qingcloud.msqudan.controller;

import com.qudan.qingcloud.msqudan.mymapper.UserMapper;
import com.qudan.qingcloud.msqudan.service.UserInfoService;
import com.qudan.qingcloud.msqudan.util.YHResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/24.
 */

@RestController
@RequestMapping("/msqudan/user")//窄化请求地址
@Api(value = "msqudan", description = "用户信息")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/getUsernames", method = RequestMethod.GET)
    public List<String> names(){
        return userMapper.selectUserById();
    }

    /**
     * 根据用户名查找用户信息
     */
    @ApiOperation(value = "CAS用户信息", response = String.class, notes = "用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "username", dataType = "String", value = "用户名"),

    })
    @RequestMapping(value = "/getUserInfoByUsername", method = RequestMethod.POST)
    public YHResult getUserInfoByUsername(
            @RequestParam(value = "username", required = true) String username,
            HttpServletRequest request
    ){


        Map<String, String> param = new HashMap<String, String>();
        param.put("username",username);
        try {
            YHResult yhResult = userInfoService.getUserInfoByUsername(param);
            log.info("getUserInfoByUsername接口返回的信息:"+yhResult.toString());
            return yhResult;
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("getUserInfoByUsername接口 异常!");
            return YHResult.build(500,"接口异常!");
        }
    }


    /**
     * 根据用户名查询用户所拥有的角色
     */
    @ApiOperation(value = "CAS用户信息", response = String.class, notes = "根据用户名获取用户所拥有的角色", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "username", dataType = "String", value = "用户名"),

    })
    @RequestMapping(value = "/getUserAndRoleInfoByUsername", method = RequestMethod.POST)
    public YHResult getUserAndRoleInfoByUsername(
            @RequestParam(value = "username", required = true) String username,
            HttpServletRequest request
    ){
        Map<String, String> param = new HashMap<String, String>();
        param.put("username",username);
        try {
            YHResult userAndRoleInfoByUsername = userInfoService.getUserAndRoleInfoByUsername(param);
            log.info("getUserAndRoleInfoByUsername 接口返回数据:"+userAndRoleInfoByUsername);
            return userAndRoleInfoByUsername;
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("getUserAndRoleInfoByUsername 异常!");
            return YHResult.build(500,"接口异常!");
        }

    }

    /**
     * 获取所有的api接口
     */
    @ApiOperation(value = "后台api信息", response = String.class, notes = "获取后台所有api", httpMethod = "POST")
    @ApiImplicitParams({

    })
    @RequestMapping(value = "/getAllApi", method = RequestMethod.POST)
    public YHResult getAllApi(HttpServletRequest request){

        try {
            YHResult allApi = userInfoService.getAllApi();
            log.info("getAllApi 接口返回数据:"+allApi);
            return allApi;
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("getAllApi 异常!");
            return YHResult.build(500,"接口异常!");
        }
    }

    /**
     * 根据apiid获取api所拥有的访问角色权限
     */
    @ApiOperation(value = "后台api信息", response = String.class, notes = "根据apiid获取api所拥有的访问角色权限", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "apiid", dataType = "int", value = "apiid"),
    })
    @RequestMapping(value = "/getApiRoleByApiId", method = RequestMethod.POST)
    public YHResult getApiRoleByApiId(
            @RequestParam(value = "apiid", required = true) Integer apiid,
            HttpServletRequest request
    ){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("apiid",apiid);
        try {
            YHResult apiRoleByApiId = userInfoService.getApiRoleByApiId(param);
            log.info("getApiRoleByApiId 接口返回数据:"+apiRoleByApiId);
            return apiRoleByApiId;
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("getApiRoleByApiId 异常!");
            return YHResult.build(500,"接口异常!");
        }
    }

    /**
     * 前端登录接口
     */
    @ApiOperation(value = "前端登录", response = String.class, notes = "前端登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "username", dataType = "String", value = "用户名"),
            @ApiImplicitParam(paramType = "query", required = true, name = "password", dataType = "String", value = "密码"),
    })
    @RequestMapping(value = "/appLogin", method = RequestMethod.POST)
    public YHResult appLogin(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request
    ){

        Map<String, String> param = new HashMap<String, String>();
        param.put("username",username);
        param.put("password",password);
        try {
            YHResult appLogin = userInfoService.appLogin(param);
            if(appLogin.getStatus()==200){
                //转json
                if(appLogin.getData()==null){
                    //登录失败返回false
                    return YHResult.ok(false);
                }else if(appLogin.getData()!=null){
                    //登录成功 返回true
                    return YHResult.ok(true);
                }
                log.info("appLogin 接口返回数据:"+appLogin);
            }else{
                return YHResult.ok(false);
            }
            return YHResult.ok(false);
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("appLogin 异常!");
            return YHResult.build(500,"接口异常!");
        }
    }

}
