package com.zhangheng.springboot.filter.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
//import com.zw.se2.hy.zuul.UserLoginInfoCache;
import com.zhangheng.springboot.filter.ConstantPath;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;



/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/23.
 *
 * 在routing和error过滤器之后被调用
 * 拦截前端的登录接口后进行鉴权验证逻辑匹配
 *
 */
public class LoginResponseFilter extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(LoginResponseFilter.class);

    @Autowired
    private ConstantPath constantPath;


    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        String url = context.getRequest().getRequestURL().toString();
        log.info("请求url:"+url);

        log.info("匹配结果:"+StringUtils.endsWith(url, constantPath.LOGIN_PATH));
        //只处理登录请求
        return StringUtils.endsWith(url, constantPath.LOGIN_PATH);//直接匹配登录接口,匹配就直接拦截进行登录验证

    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            String url = context.getRequest().getRequestURL().toString();

            if (StringUtils.isNotBlank(body)) {
                //验证响应结果是否为登录成功
                JSONObject bodyJson = JSONObject.fromObject(body);
                if (bodyJson.has(constantPath.LOGIN_RESPONSE_STATUS)) {
                    String status = bodyJson.getString(constantPath.LOGIN_RESPONSE_STATUS);
                    if (StringUtils.equals(status, "200")) {
                        if (bodyJson.has(constantPath.LOGIN_RESPONSE_RESULT)) {
                            JSONArray resultArray = bodyJson.getJSONArray(constantPath.LOGIN_RESPONSE_RESULT);
                            if (resultArray != null && resultArray.size() > 0) {
                                JSONObject userObject = resultArray.getJSONObject(0);
                                processLogin(context, userObject);
                            }
                        }
                    }


                }
            }

            context.setResponseBody(body);
        } catch (IOException e) {
            rethrowRuntimeException(e);
        }
        return null;
    }

    private void processLogin(RequestContext context, JSONObject userObject) {
        if (userObject.has(constantPath.LOGIN_USERNAME)) {
            String userName = userObject.getString(constantPath.LOGIN_USERNAME);
            //将用户名存储在session中，判断用户是否登录
            HttpServletRequest request = context.getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            session.setMaxInactiveInterval(1800);
            log.info(">>>用户>>>" + userName + "执行了>>>登录>>>操作");
        }
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }




}
