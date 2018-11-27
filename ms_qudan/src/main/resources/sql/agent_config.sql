CREATE TABLE `agent_config` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `level` int(2) DEFAULT NULL COMMENT '代理等级',
  `direct_rate` decimal(5,2) DEFAULT NULL COMMENT '之间分佣比例',
  `related_rate` decimal(5,2) DEFAULT NULL COMMENT '间接分佣比列',
  `invite_limit` int(10) DEFAULT NULL COMMENT '邀请上限',
  `task_done_limit` int(10) DEFAULT NULL COMMENT '任务完成数上限',
  `share_limit` int(10) DEFAULT NULL COMMENT '分享上限',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

