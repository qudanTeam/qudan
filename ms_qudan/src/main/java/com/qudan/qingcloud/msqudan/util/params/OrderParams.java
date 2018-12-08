package com.qudan.qingcloud.msqudan.util.params;

public class OrderParams {
    Integer userId;
    Integer productType; //1-信用卡，2-贷款
    Integer applyStatus; // 贷款查询状态 1-审核中，2审核通过，3-退回
    Integer officialStatus; //信用卡查询状态 0-待查询，1-审核中，2审核通过，3-退回

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getOfficialStatus() {
        return officialStatus;
    }

    public void setOfficialStatus(Integer officialStatus) {
        this.officialStatus = officialStatus;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }
}
