package com.qudan.qingcloud.msqudan.util.requestBody;

public class ValidcodeRB {
    String mobile;
    Integer type; //1-注册，2-登录，3忘记密码，4-申请产品

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
