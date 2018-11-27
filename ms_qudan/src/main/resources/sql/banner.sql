CREATE TABLE `banner` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL COMMENT '图片标题',
  `link` varchar(2000) DEFAULT NULL COMMENT '链接地址',
  `img` varchar(2000) DEFAULT NULL COMMENT '图片地址',
  `sort_val` int(2) DEFAULT NULL COMMENT '排序值，越小越前',
  `is_show` int(2) DEFAULT '0' COMMENT '是否显示 0-不显示，1-以显示',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_tiime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

