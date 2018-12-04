package com.qudan.qingcloud.msqudan.util.responses;

import java.math.BigDecimal;

public class UserAgentVo {
    private String agentLevel;//代理等级
    private Integer recommendRegisterCount;//已推荐的人
    private Integer recommendJobDoneCount;//已推荐完成任务次数
    private BigDecimal agentRevene;//团队佣金
    private BigDecimal agentRate;//代理加成
    private Integer nextLevelGap;//下一代理等级的差距
}
