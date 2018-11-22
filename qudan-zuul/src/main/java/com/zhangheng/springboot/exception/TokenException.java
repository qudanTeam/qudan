package com.zhangheng.springboot.exception;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/11/13.
 */
public class TokenException extends BaseException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }
}
