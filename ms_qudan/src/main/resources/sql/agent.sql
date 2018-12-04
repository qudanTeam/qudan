CREATE TABLE `agent` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `level` int(2) DEFAULT NULL COMMENT '代理等级',
  `beign_agent_time` int(10) DEFAULT NULL COMMENT '成为代理时间',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

