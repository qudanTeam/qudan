package com.zhangheng.springboot.controller;

import com.zhangheng.springboot.feign.UserInfoFeign;
import com.zhangheng.springboot.utils.JwtUtil;
import com.zhangheng.springboot.utils.YHResult;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/8/3.
 */


@RestController
@RefreshScope//刷新配置
@RequestMapping("/qudanZuul/user")//窄化请求地址
@Api(value = "qudan-zuul", description = "用户信息")
public class VUserInfoController {

    /**
     * 注入用户相关
     *
     */
    @Autowired
    private UserInfoFeign userInfoFeign;


    //日志
    private final static Logger logger = LoggerFactory.getLogger(VUserInfoController.class);

    /**
     *前端用户登录接口
     */
    @ApiOperation(value = "登录获取token", response = String.class, notes = "用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "username", dataType = "String", value = "用户名"),
            @ApiImplicitParam(paramType = "query", required = true, name = "userId", dataType = "String", value = "用户id"),
    })
    @PostMapping("/login")
    public YHResult appLogin(
//              @RequestBody Map<String,Object> params,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "userId", required = true) int userId
            //HttpServletRequest request
    ){
          try {
              /**
               * 登录根据数据库的逻辑处理
               */
              Map<String,Object> claims = new HashMap<>();//存放用户信息(敏感信息不要放 比如密码)
              claims.put("username",username);
              claims.put("userId",userId);
              long ttlMillis = 1000 * 60 * 60;//过期时间(单位毫秒)

              String token  = JwtUtil.createJWT(claims, "qudan", "趣单",ttlMillis,"");
              Map<String,Object> params = new HashMap<>();
              params.put("token",token);
              params.put("expiration",ttlMillis);
              return YHResult.build(200,"登录成功!",params);
          }catch (Exception e){
              logger.error(e.getMessage());
              logger.error("login 异常!");
              return YHResult.build(500,"接口异常!");
          }
      }

    /**
     * 刷新token
     */
    @ApiOperation(value = "token延期", response = String.class, notes = "token延期", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "token", dataType = "String", value = "快过期token"),
    })
    @PostMapping("/refreshToken")
    public YHResult refreshToken(
            @RequestParam(value = "token", required = true) String token,
            HttpServletRequest request
    ){
        try {
            String refreshToken = JwtUtil.refreshToken(token);
            if(!StringUtils.isEmpty(refreshToken)){
                Map<String,Object> params = new HashMap<>();
                params.put("refreshToken",refreshToken);
                return YHResult.build(200,"token延期成功!",params);
            }else{
                return YHResult.build(400,"token已过期!");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            logger.error("refreshToken 异常!");
            return YHResult.build(500,"接口异常!");
        }
    }

    /**
     * 登出 让客户端直接丢弃token
     */

}
