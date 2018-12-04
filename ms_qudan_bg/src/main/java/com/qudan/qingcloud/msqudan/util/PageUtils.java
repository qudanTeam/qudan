package com.qudan.qingcloud.msqudan.util;


import java.io.Serializable;
import java.util.Objects;

/**
 * 分页基类
 * Created by wzx on 2018/7/24.
 */
public class PageUtils implements Serializable {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if(Objects.nonNull(pageNum)){
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(Objects.nonNull(pageSize)){
            this.pageSize = pageSize;
        }
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
