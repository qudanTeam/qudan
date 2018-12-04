package com.qudan.qingcloud.msqudan.exception;

/**
 * 参数异常类
 */
public class ArgumentException extends QDException{

    public ArgumentException() {
    }

    public ArgumentException(String message) {
        super(message,new IllegalArgumentException(message));
    }

    public ArgumentException(Throwable cause) {
        super(cause);
    }
}
