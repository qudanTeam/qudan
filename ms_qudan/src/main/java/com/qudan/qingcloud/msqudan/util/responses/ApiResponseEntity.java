package com.qudan.qingcloud.msqudan.util.responses;

import com.qudan.qingcloud.msqudan.util.responses.jackson.NullData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by word on 2016/6/17.
 */
public class ApiResponseEntity {
    private HttpStatus code = HttpStatus.OK;
    private Object data;
    private ErrorEntity errorEntity;


    public boolean hasError(){
        if(code.equals(HttpStatus.OK)){
            return false;
        } else {
            return  true;
        }
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ErrorEntity getErrorEntity() {
        return errorEntity;
    }

    public void setErrorEntity(ErrorEntity errorEntity) {
        this.errorEntity = errorEntity;
    }


    public ApiResponseEntity() {

    }

    /**
     * 添加详细错误信息,并且设置第一级的报错信息
     */
    public void addError(HttpStatus code, ErrorDetail errorDetail, String allMessage){
        addError(code, errorDetail);
        errorEntity.setMessage(allMessage);
    }


    public void addError(HttpStatus code, ErrorDetail errorDetail){
        setCode(code);
        if(errorEntity == null){
            errorEntity = new ErrorEntity(errorDetail.getMessage());
            errorEntity.setMessage(errorDetail.getMessage());
        }
        errorEntity.getErrors().add(errorDetail);
    }

    public void addWithUrl(String url){
        setCode(HttpStatus.PAYMENT_REQUIRED);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("h5Url", url);
        data = map;
    }

    /**
     * 添加详细错误信息
     */

    public void  addInfoError(ErrorDetail errorDetail){
        addError(HttpStatus.BAD_REQUEST, errorDetail);
    }

    public void  addInfoError(String code, String message){
        String field = "";
        if(code.indexOf(".") > -1){
            field = code.substring(0, code.indexOf("."));
        } else {
            field = code;
        }
        ErrorDetail detail = new ErrorDetail(field, code, message);
        addError(HttpStatus.BAD_REQUEST, detail);
    }

    public void  addInfoError(String field, String code, String message){
        ErrorDetail detail = new ErrorDetail(field, code, message);
        addError(HttpStatus.BAD_REQUEST, detail);
    }

    /**
     * 添加验证错误信息
     * @param errorDetail
     */
    public void addTokenError(ErrorDetail errorDetail){
        addError(HttpStatus.UNAUTHORIZED, errorDetail);
    }

    /**
     * 如果成功返回{"code":200, "data":object}
     * example
              {
                 "code":200,
                 "data":object
              }
     * 失败返回
     * example
     *          {
                    "code": 400,
                    "error": {
                        "errors": [
                            {
                            "field": "deliver",
                            "code": "deliver.record.exist",
                            "message": "已经投递过该职位"
                            },
                            {
                            "field": "user",
                            "code": "user.incomplete",
                            "message": "用户信息不完善"
                            }
                        ],
                        "message": "不符合投递条件"
                        }
                    }
     *
     * 最后需要返回的JSON数据
     * @return
     */
    public Map<String, Object> getResponseData(){
        Map<String, Object> map = new HashMap<String, Object>();
        int codeValue = getCode().value();
        map.put("code", codeValue);
        if(codeValue >= 400 && codeValue < 500 && codeValue != 402){
            map.put("error", errorEntity);
        } else {
            if(data == null){
                data = new NullData();
            }
            map.put("data", data);
        }
        return map;
    }

    public ResponseEntity<Map<String,Object>> createResponseEntityWithObj(Object obj){
        setData(obj);
        return  new ResponseEntity<Map<String, Object>>(getResponseData(), code);
    }

    public ResponseEntity<Map<String,Object>> createResponseEntity(Map<String,Object> data){
        setData(data);
        return  new ResponseEntity<Map<String, Object>>(getResponseData(), code);
    }


    public ResponseEntity<Map<String,Object>> createResponseEntity(){
        return  new ResponseEntity<Map<String, Object>>(getResponseData(), code);
    }
}
