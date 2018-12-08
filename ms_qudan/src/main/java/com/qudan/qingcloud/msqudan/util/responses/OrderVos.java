package com.qudan.qingcloud.msqudan.util.responses;

public class OrderVos {
    private String productLogo;
    private String productName;
    private String orderNo;
    private String mobile;
    private String applyName;
    private Integer officialStatus;
    private Integer status;

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public Integer getOfficialStatus() {
        return officialStatus;
    }

    public void setOfficialStatus(Integer officialStatus) {
        this.officialStatus = officialStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
