CREATE TABLE `share_manager` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `product_id` int(10) DEFAULT NULL COMMENT '商品id',
  `content` text COMMENT '文案区域',
  `is_show` int(10) DEFAULT '0' COMMENT '是否显示 0-不显示，1-显示',
  `share_img` varchar(2000) DEFAULT NULL COMMENT '分享图片',
  `modify_tiime` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `sort_val` int(10) DEFAULT NULL COMMENT '排序值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

