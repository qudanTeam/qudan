CREATE TABLE `product_config` (
  `product_id` int(10) DEFAULT NULL COMMENT '产品id',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '文案内容（富文本）',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

