package com.qudan.qingcloud.msqudan.exception;

/**
 * qudan 异常基类
 */
public class QDException extends RuntimeException {
    public QDException() {
    }

    public QDException(String message) {
        super(message);
    }

    public QDException(String message, Throwable cause) {
        super(message, cause);
    }

    public QDException(Throwable cause) {
        super(cause);
    }
}
