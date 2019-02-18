package com.qudan.qingcloud.msqudan.util.params;

public class OrderParams {
    Integer userId;
    Integer product_type; //1-信用卡，2-贷款 ,3-POS机
    Integer apply_status; // 贷款查询状态 1-审核中，2审核通过，3-退回
    Integer official_status; //信用卡查询状态 0-待查询，1-审核中，2审核通过，3-退回

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProduct_type() {
        return product_type;
    }

    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }

    public Integer getApply_status() {
        return apply_status;
    }

    public void setApply_status(Integer apply_status) {
        this.apply_status = apply_status;
    }

    public Integer getOfficial_status() {
        return official_status;
    }

    public void setOfficial_status(Integer official_status) {
        this.official_status = official_status;
    }
}
