package com.zhangheng.springboot.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface YHMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //特别注意，该接口不能被扫描到，否则会出错
}
