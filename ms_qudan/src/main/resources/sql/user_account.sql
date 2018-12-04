CREATE TABLE `user_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `blance` decimal(19,2) DEFAULT NULL COMMENT '账户余额(可提现的金额+正在结算的金额)',
  `allow_tx` decimal(19,2) DEFAULT NULL COMMENT '账户可提现金额',
  `tx` decimal(19,2) DEFAULT NULL COMMENT '已经提现金额',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

