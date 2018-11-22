package com.zhangheng.springboot.filter.error;

import com.netflix.zuul.ZuulFilter;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/23.
 */
public class ErrorFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("------------hi,  i am  a errorFilter");
        return "hi,  i am  a errorFilter";
    }

}
