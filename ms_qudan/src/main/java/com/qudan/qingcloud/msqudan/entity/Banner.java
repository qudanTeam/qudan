package com.qudan.qingcloud.msqudan.entity;

import java.util.Date;

public class Banner {
    private Integer id;

    private String title;

    private String link;

    private String img;

    private Integer sortVal;

    private Integer isShow;

    private Date createTime;

    private Date modifyTiime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTiime() {
        return modifyTiime;
    }

    public void setModifyTiime(Date modifyTiime) {
        this.modifyTiime = modifyTiime;
    }
}