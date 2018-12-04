package com.qudan.qingcloud.msqudan.base;

import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.mymapper.self.UserMapperSelf;
import com.qudan.qingcloud.msqudan.util.JwtUtil;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.ErrorDetail;
import com.qudan.qingcloud.msqudan.util.responses.JSONResponseUtil;
import com.qudan.qingcloud.msqudan.util.responses.UserInfo;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtFilter extends GenericFilterBean {

    private static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private CommonConfig config;
    @Autowired
    private UserMapperSelf userMapperSelf;

    private  String excludeUrl;

    private  String uncertainUrl;

    public void setExcludeUrl(String excludeUrl) {
        this.excludeUrl = excludeUrl;
    }

    public void setUncertainUrl(String uncertainUrl) {
        this.uncertainUrl = uncertainUrl;
    }

    public List<String> excludes = new ArrayList<String>();

    public List<String> uncertains = new ArrayList<String>();

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        String temp = this.excludeUrl;
        if (temp != null) {
            String[] url = temp.split(",");
            for (int i = 0; url != null && i < url.length; i++) {
                excludes.add(url[i]);
            }
        }
        temp = this.uncertainUrl;
        if (temp != null) {
            String[] url = temp.split(",");
            for (int i = 0; url != null && i < url.length; i++) {
                uncertains.add(url[i]);
            }
        }
    }

    /**
     * 允许通过的URI
     * @param request
     * @return
     */
    public boolean handleExcludeURL(HttpServletRequest request) {
        if (excludes == null || excludes.isEmpty()) {
            return false;
        }
        String uri = request.getServletPath();
        for (String pattern : excludes) {
            if(pattern.equals(uri)){
                return true;
            }
            if(pattern.indexOf("*") > -1 && uri.startsWith(pattern.substring(0,pattern.indexOf("*")))){
                return true;
            }
        }
        return false;
    }


    /**
     * 无论有没有JWT都给通过
     * @param request
     * @return
     */
    public boolean handleUncertainURL(HttpServletRequest request) {
        if (uncertains == null || uncertains.isEmpty()) {
            return false;
        }
        String uri = request.getServletPath();
        for (String pattern : uncertains) {
            if(pattern.equals(uri)){
                return true;
            }

            if(pattern.indexOf("*") > -1 && uri.startsWith(pattern.substring(0,pattern.indexOf("*")))){
                return true;
            }

        }
        return false;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        if(handleExcludeURL(request)){
            chain.doFilter(req, resp);
            return;
        }
        Boolean uncertain = handleUncertainURL(request);

        ApiResponseEntity ARE = new ApiResponseEntity();
        final String authHeader = request.getHeader("Authorization");
        final String permit = request.getHeader("Permit");
        UserInfo userInfo= new UserInfo();
        Integer userId = null;

        if (authHeader == null && !uncertain){
            ARE.addTokenError(new ErrorDetail("token","token.empty","Missing or invalid Authorization header."));
            JSONResponseUtil.APIStrictJsonResponse((HttpServletResponse) resp, ARE);
            return;
        }
        logger.info("当前用户token：" + authHeader);

        String token = ""; // The part after "Bearer "
        Claims claims = null;
        try {
            claims = JwtUtil.getClaims(authHeader);
            userId = Integer.valueOf(claims.get("userId").toString());
            if(userId == null){
                ARE.addTokenError(new ErrorDetail("user","userId.error","没有此用户"));
            }  else {
                UserInfo user = userMapperSelf.selectById(userId);
                if(user == null){
                    ARE.addTokenError(new ErrorDetail("user","userId.error","没有此用户"));
                } else {
                    LocalUserHelper.putUser(userId);
                }
            }
        } catch (final SignatureException e) {
            if(uncertain){
                logger.error("token签名不正确,但不需要拦截的路径");
            } else {
                logger.error("签名不正确",e);
                ARE.addTokenError(new ErrorDetail("signature","signature.miss","签名不正确"));
            }
        } catch (final ExpiredJwtException e){
            if(uncertain){
                logger.error("token签名不正确,但不需要拦截的路径");
            } else {
                logger.error("token已过期",e);
                ARE.addTokenError(new ErrorDetail("token","token.isExpire","token 已过期"));
            }
        } catch (StringIndexOutOfBoundsException e){
            if(uncertain){
                logger.info("JWT的字符过短,但不需要拦截的路径");
            } else {
                logger.info("JWT的字符过短,JWT:" + token);
                ARE.addTokenError(new ErrorDetail("signature","signature.miss","签名不正确"));
            }
        } catch (Exception e){
            if(uncertain){
                logger.info("JWT 验证出错,但不需要拦截的路径");
            } else {
                logger.info("JWT 验证出错",e);
                ARE.addTokenError(new ErrorDetail("token", "token.error", "令牌出错"));
            }
        }

        if (ARE.hasError() && !uncertain){
            try {
                JSONResponseUtil.APIStrictJsonResponse((HttpServletResponse) resp, ARE);
            } catch (Exception e) {
                e.printStackTrace();
                e.printStackTrace();
                logger.error("token验证出错:" + e.getMessage() ,e);
            }
            return;
        }

        chain.doFilter(req, resp);
        LocalUserHelper.destory();
    }
}
