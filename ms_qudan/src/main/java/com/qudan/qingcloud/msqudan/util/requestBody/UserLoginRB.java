package com.qudan.qingcloud.msqudan.util.requestBody;

public class UserLoginRB {
    private String mobile;
    private String password;
    private String validcode;
    private String wutid;
    private String shareid;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidcode() {
        return validcode;
    }

    public void setValidcode(String validcode) {
        this.validcode = validcode;
    }

    public String getWutid() {
        return wutid;
    }

    public void setWutid(String wutid) {
        this.wutid = wutid;
    }

    public String getShareid() {
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid;
    }
}
