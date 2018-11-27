package com.qudan.qingcloud.msqudan.util;

import com.github.pagehelper.PageHelper;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.ErrorDetail;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

public class ComUtils {

    public static Integer getPage(HttpServletRequest request){
        return  Integer.valueOf(request.getParameter("page"));
    }

    public static Integer getPerPage(HttpServletRequest request){
        return  Integer.valueOf(request.getParameter("per_page"));
    }

    public static void startPage(HttpServletRequest request){
        //分页
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer perPage = Integer.valueOf(request.getParameter("per_page"));
        PageHelper.startPage(page, perPage);
    }

    public static void startPage(Integer page ,Integer perPage){
        PageHelper.startPage(page, perPage);
    }

    /**
     * 验证分页参数
     * @param ARE
     * @param request
     * @return
     */
    public static boolean validPage(ApiResponseEntity ARE, HttpServletRequest request){
        //分页
        try {
            Integer page = Integer.valueOf(request.getParameter("page"));
            Integer perPage = Integer.valueOf(request.getParameter("per_page"));
        }catch (Exception e){
            ARE.addInfoError(new ErrorDetail("page","page","分页参数错误"));
        }
        return  !ARE.hasError();
    }


    public static String addPrefixToImg(String img, String prefix){
        if(StringUtils.isBlank(img)){
            return img;
        }
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(img, ",");
        while (tokenizer.hasMoreTokens()){
            result = (result + prefix + tokenizer.nextToken() + ",");
        }
        if(StringUtils.isNotBlank(result)){
            result = result.substring(0, result.length()-1);
        }
        return result;
    }
}
