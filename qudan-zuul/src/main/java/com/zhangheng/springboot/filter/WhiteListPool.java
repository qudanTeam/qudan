package com.zhangheng.springboot.filter;

import com.zhangheng.springboot.filter.pre.PreFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/7.
 * 白名单接口配置
 */
@Component
@RefreshScope
public class WhiteListPool {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(WhiteListPool.class);

    @Value("#{'${white.list}'.split(',')}")
    public Set<String> value;

    public Set<String> getValue() {
        System.out.println(value.toString());
        logger.info("白名单接口:"+value.toString());
        return value;
    }
}
