CREATE TABLE `sms_send_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL COMMENT '验证码',
  `send_type` int(2) DEFAULT NULL COMMENT '发送类型：1-注册登陆，2-忘记密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `create_time` timestamp NULL DEFAULT NULL,
  `invalid_time` timestamp NULL DEFAULT NULL COMMENT '失效时间',
  `is_valid` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '是否验证过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

