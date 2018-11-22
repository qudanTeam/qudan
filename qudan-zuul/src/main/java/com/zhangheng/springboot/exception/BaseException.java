package com.zhangheng.springboot.exception;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/11/13.
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
