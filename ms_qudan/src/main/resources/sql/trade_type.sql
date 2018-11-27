CREATE TABLE `trade_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `trade_type` int(10) DEFAULT NULL COMMENT '交易类型: 1-提现，2-任务佣金，3-团队佣金，4-vip购买',
  `apply_id` int(10) DEFAULT NULL COMMENT '申请记录id',
  `price` decimal(19,2) DEFAULT NULL COMMENT '交易金额',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `status` int(2) DEFAULT '1' COMMENT '交易状态：1-待审核，2-已审核，3-已拒绝',
  `account` int(10) DEFAULT NULL COMMENT '用户的账户id',
  `indirect_type` varchar(255) DEFAULT NULL COMMENT '1-直接佣金，2-上级佣金，3-上上级佣金 当trade为3佣金的时候，此字段表明佣金的类型',
  `send_status` int(2) DEFAULT '1' COMMENT '发放状态, 1-未发放，2-已发放',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `send_time` timestamp NULL DEFAULT NULL COMMENT '发放时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

