package com.qudan.qingcloud.msqudan.config;

public class QudanConstant {
    public static final String USER_PW_KEY = "qudan_fcc";

    public static enum TRADE_TYPE {
        TI_XIAN(1), //提现
        TASK_REWORD(2),//任务佣金
        TEAM_REWORD( 3),//团队佣金
        VIP_PAY(4),//VIP购买
        JITI_REWORD(5),//阶梯工资
        ;
        private Integer type;

        TRADE_TYPE(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }
}
