package com.qudan.qingcloud.msqudan.util.responses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by word on 2016/6/17.
 */
public class ErrorEntity {
    private List<ErrorDetail> errors;
    private String message;

    public ErrorEntity(List<ErrorDetail> errors, String message) {
        this.errors = errors;
        this.message = message;
    }
    public ErrorEntity(String message){
        this.errors = new ArrayList<ErrorDetail>();
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
