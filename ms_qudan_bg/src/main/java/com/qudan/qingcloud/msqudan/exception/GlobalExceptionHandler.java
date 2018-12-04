package com.qudan.qingcloud.msqudan.exception;

import com.qudan.qingcloud.msqudan.util.ResponseResult;
import com.qudan.qingcloud.msqudan.util.RestResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception e){
        log.error("未知异常,请通知管理员。Exception：{}",e);
        return new ResponseResult<>(RestResultEnum.UNKNOWN_ERROR.getKey(),RestResultEnum.UNKNOWN_ERROR.getMessage());
    }

    @ExceptionHandler(ArgumentException.class)
    public ResponseResult<String> handleArgumentException(ArgumentException a){
        return new ResponseResult<>(RestResultEnum.ARGUMENT_ERROR,a.getMessage());
    }

    @ExceptionHandler(QDException.class)
    public ResponseResult<String> handleRSException(QDException r){
        log.error("系统异常,RSException：{}",r);
        return new ResponseResult<>(RestResultEnum.UNKNOWN_ERROR,r.getMessage());
    }

    @ExceptionHandler(BeansException.class)
    public ResponseResult<String> handleIllException(BeansException b){
        log.error("对象复制异常：BeansException:{}",b);
        return new ResponseResult<>(RestResultEnum.ERROR,"对象复制异常");
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseResult<String> handleBadSqlGrammarException(BadSqlGrammarException sql){
        log.error("sql语句异常：BadSqlGrammarException:{}",sql);
        return new ResponseResult<>(RestResultEnum.ERROR,"sql语句异常，通知开发者。");
    }

    @ExceptionHandler(IOException.class)
    public ResponseResult<String> handleIOException(IOException io){
        log.error("io流异常：IOException:{}",io);
        return new ResponseResult<>(RestResultEnum.ERROR,"io流异常，重新尝试。");
    }
}
