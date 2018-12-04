CREATE TABLE `category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '分类名字',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `category_type` int(2) DEFAULT NULL COMMENT '分类类型，1-信用卡银行，2-贷款',
  `logo` varchar(2000) DEFAULT NULL,
  `get_link` varchar(2000) DEFAULT NULL COMMENT '查询链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

