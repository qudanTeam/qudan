package com.qudan.qingcloud.msqudan.util.requestBody;

public class ApplyRB {
    private Integer productId;
    private String inviteCode;
    private String name;
    private String mobile;
    private String idNo;
    private String validcode;
    private String shareid;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getValidcode() {
        return validcode;
    }

    public void setValidcode(String validcode) {
        this.validcode = validcode;
    }

    public String getShareid() {
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid;
    }
}
