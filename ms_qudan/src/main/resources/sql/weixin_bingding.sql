CREATE TABLE `weixin_binding` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `openid` varchar(100) DEFAULT NULL COMMENT '微信openid',
  `unionid` varchar(100) DEFAULT NULL COMMENT 'unionid',
  `wechat_name` varchar(100) DEFAULT NULL COMMENT '微信名称',
  `wechat_logo` varchar(100) DEFAULT NULL COMMENT '微信logo',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

