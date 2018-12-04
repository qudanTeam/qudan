CREATE TABLE `agent_relation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `agent_id` int(10) DEFAULT NULL COMMENT '代理id',
  `father_id` int(10) DEFAULT NULL COMMENT '父级代理ID',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

