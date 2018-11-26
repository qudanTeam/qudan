package com.qudan.qingcloud.msqudan.util;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtils<T> {

    private List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public PageUtils(List<T> result) {
        this.result = result;
    }

    public Map<String, Object> build() {
        PageInfo page = new PageInfo(this.result);

        //返回值封装
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalRows", page.getTotal());
        resultMap.put("totalPages", page.getPages());
        resultMap.put("pageSize", page.getPageSize());
        resultMap.put("pageNum", page.getPageNum());
        resultMap.put("list", result);

        return resultMap;
    }

}
