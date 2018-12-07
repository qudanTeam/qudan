package com.qudan.qingcloud.msqudan.util.responses;

import java.math.BigDecimal;

public class UserAgentVo {
    private Integer agentLevel;//代理等级
    private Integer recommendRegisterCount;//已推荐的人
    private Integer recommendJobDoneCount;//已推荐完成任务次数
    private BigDecimal agentRevenue;//团队佣金
    private BigDecimal agentRate;//代理加成
    private Integer nextLevelGap;//下一代理等级的差距

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getRecommendRegisterCount() {
        return recommendRegisterCount;
    }

    public void setRecommendRegisterCount(Integer recommendRegisterCount) {
        this.recommendRegisterCount = recommendRegisterCount;
    }

    public Integer getRecommendJobDoneCount() {
        return recommendJobDoneCount;
    }

    public void setRecommendJobDoneCount(Integer recommendJobDoneCount) {
        this.recommendJobDoneCount = recommendJobDoneCount;
    }

    public BigDecimal getAgentRevenue() {
        return agentRevenue;
    }

    public void setAgentRevenue(BigDecimal agentRevenue) {
        this.agentRevenue = agentRevenue;
    }

    public BigDecimal getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(BigDecimal agentRate) {
        this.agentRate = agentRate;
    }

    public Integer getNextLevelGap() {
        return nextLevelGap;
    }

    public void setNextLevelGap(Integer nextLevelGap) {
        this.nextLevelGap = nextLevelGap;
    }
}
