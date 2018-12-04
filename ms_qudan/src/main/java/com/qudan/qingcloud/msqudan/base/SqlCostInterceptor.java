package com.qudan.qingcloud.msqudan.base;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

/**
 * Sql执行时间记录拦截器
 */
    @Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
    @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
    @Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
public class SqlCostInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(Interceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long sqlCost = endTime - startTime;
            logger.info("执行耗时 : [" + sqlCost + "ms ] ");
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}