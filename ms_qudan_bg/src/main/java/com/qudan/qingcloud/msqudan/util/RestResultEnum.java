package com.qudan.qingcloud.msqudan.util;

/**
 * 自定义异常枚举类
 */
public enum RestResultEnum {
    SUCCESS(200000, "操作成功"),
    ERROR(905000, "操作错误"),
    UNKNOWN_ERROR(906000, "未知异常错误"),
    NOT_FOUND_ERROR(905010, "请求地址不存在"),
    ARGUMENT_ERROR(100000, "请求参数错误");


    private int key;
    private String message;

    RestResultEnum(int key, String message) {
        this.key = key;
        this.message = message;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
