CREATE TABLE `message` (
  `msg_logo` varchar(2000) DEFAULT NULL COMMENT '消息logo',
  `msg_title` varchar(200) DEFAULT NULL COMMENT '消息标题',
  `msg_content` varchar(200) DEFAULT NULL COMMENT '消息内容',
  `msg_link` varchar(2000) DEFAULT NULL COMMENT '消息链接',
  `create_time` timestamp NULL DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `is_user_delete` int(10) DEFAULT '0' COMMENT '用户是否删除 0-未删除，1-已删除',
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

