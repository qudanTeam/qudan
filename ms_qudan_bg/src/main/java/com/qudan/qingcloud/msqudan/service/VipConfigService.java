package com.qudan.qingcloud.msqudan.service;

import com.qudan.qingcloud.msqudan.entity.VipConfig;

import java.util.List;

public interface VipConfigService {
    int add(VipConfig vipConfig);
    int delete(Integer id);
    int update(VipConfig vipConfig);
    List<VipConfig> findByPage(VipConfig vipConfig);
}
