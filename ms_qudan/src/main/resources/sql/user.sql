CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `userface` varchar(100) DEFAULT NULL COMMENT '头像',
  `isenable` int(2) DEFAULT '1' COMMENT '是否启用,0-禁言，1-启用 等同于上下线',
  `register_mobile` varchar(100) DEFAULT NULL COMMENT '注册手机号',
  `id_no` varchar(100) DEFAULT NULL COMMENT '身份证',
  `alipay_no` varchar(100) DEFAULT NULL COMMENT '支付宝账号',
  `agent_level` int(10) DEFAULT NULL COMMENT '代理等级',
  `register_time` int(10) DEFAULT NULL COMMENT '注册时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后的登录时间',
  `status` int(2) DEFAULT '0' COMMENT '实名审核状态, 0-未审核，1-审核通过，2-退回',
  `user_type` int(10) DEFAULT NULL COMMENT '用户类型1-vip,2-代理',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `agent_id` int(10) DEFAULT NULL COMMENT '代理id',
  `recommend_invite_code` varchar(100) DEFAULT NULL COMMENT '推荐人邀请码',
  `invite_code` varchar(200) DEFAULT NULL COMMENT '邀请码',
  `recommend_invite_id` bigint(19) DEFAULT NULL COMMENT '推荐人邀ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

