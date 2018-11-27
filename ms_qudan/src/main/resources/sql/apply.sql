CREATE TABLE `apply` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `product_id` int(10) DEFAULT NULL COMMENT '商品id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `id_no` varchar(100) DEFAULT NULL COMMENT '身份证号码',
  `status` int(10) DEFAULT '1' COMMENT '申请状态 1-申请中,2-已通过,3-已拒绝',
  `official_status` int(10) DEFAULT '1' COMMENT 'C端审核状态，1-申请中，2-通过，3-拒绝',
  `last_official_query` timestamp NULL DEFAULT NULL COMMENT '最后一次向官方查询时间',
  `reject_reason` varchar(200) DEFAULT NULL COMMENT '拒绝原因',
  `salary_status` int(10) DEFAULT '1' COMMENT '工资发放状态，1-申请中,2-结算中，3-已发放',
  `invite_code` varchar(200) DEFAULT NULL COMMENT '邀请码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

