package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class ShareManager {
    private Integer id;

    private Integer productId;

    private Integer isShow;

    private String shareImg;

    private Date modifyTiime;

    private Date createTime;

    private Integer sortVal;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg == null ? null : shareImg.trim();
    }

    public Date getModifyTiime() {
        return modifyTiime;
    }

    public void setModifyTiime(Date modifyTiime) {
        this.modifyTiime = modifyTiime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}