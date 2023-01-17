/* 修改股票池，增加备注字段 */
alter table stock_hold add `price_close` float DEFAULT 0 COMMENT '昨日收盘价';
alter table stock_hold add `price_high` float DEFAULT 0 COMMENT '近期最高价';
alter table stock_hold add `price_low` float DEFAULT 0 COMMENT '近期最低价';
alter table stock_hold add `enhance_white_sign` tinyint(1) DEFAULT 0 COMMENT '是否发出增强白信号, 0未发出，1已发出';
alter table stock_hold add `lose_enhance_white_sign` tinyint(1) DEFAULT 0 COMMENT '是否失去增强白信号, 0未失去，1已失去';
alter table stock_hold add `price_sell_high` float DEFAULT 0 COMMENT '下一阶段价格或者是卖出最高价阀值';
alter table stock_hold add `price_sell_low` float DEFAULT 0 COMMENT '卖出最低价阀值';
alter table stock_hold add `close_ema_18` float DEFAULT 0 COMMENT '最近一个交易日的close_ema_18值';

/* 修改股票交易表，增加备注字段 */
alter table stock_hold_trade add `counter` int(11) DEFAULT 0 COMMENT '查询结果计数器，默认为0';
alter table stock_hold_trade add `price_type` varchar(10) DEFAULT '1' COMMENT '委托价格类型：1最新价、2对手价、3指定价、B1买1价、B2买2价、S1卖1价、S2卖2价';

/* 新增股票卖出规则 */
CREATE TABLE `stock_sell_rule` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sell_ratio` float DEFAULT 0.5 COMMENT '第一次止盈卖出比例，默认0.5',
  `p1_ratio` float DEFAULT 1.15 COMMENT '止盈阶段计算公式比例',
  `p2_ratio` float DEFAULT 1.1 COMMENT '止盈阶段计算公式比例',
  `p3_ratio` float DEFAULT 1.1 COMMENT '止盈阶段计算公式比例',
  `p4_ratio` float DEFAULT 1.1 COMMENT '止盈阶段计算公式比例',
  `p5_ratio` float DEFAULT 1.1 COMMENT '止盈阶段计算公式比例',
  `sp1_ratio` float DEFAULT 0.85 COMMENT '止盈阶段卖出价格计算公式比例',
  `sp2_ratio` float DEFAULT 0.87 COMMENT '止盈阶段卖出价格计算公式比例',
  `sp3_ratio` float DEFAULT 0.89 COMMENT '止盈阶段卖出价格计算公式比例',
  `sp4_ratio` float DEFAULT 0.91 COMMENT '止盈阶段卖出价格计算公式比例',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT 0 COMMENT '2结束，1运行，0草稿',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 新增股票撤销交易表
CREATE TABLE `stock_hold_revoke` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stock_hold_trade_id` int(11) DEFAULT NULL COMMENT '撤销指定的交易hold_trade id',
  `orderid` varchar(50) DEFAULT NULL COMMENT '合同编号，迅投接口生成，当天唯一',
  `message` varchar(1000) DEFAULT NULL COMMENT '委托/撤单状态信息',
  `note` varchar(1000) DEFAULT NULL COMMENT '投资备注',
  `status` int(11) DEFAULT NULL COMMENT '0执行中，1结束，-1失败',
  `taskid` varchar(50) DEFAULT NULL COMMENT '任务/指令编号',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 每日定时操作执行结果表
CREATE TABLE `stock_execute_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `date_exec` varchar(8) DEFAULT NULL COMMENT '执行任务日期，格式%Y%m%d',
  `command` varchar(20) DEFAULT NULL COMMENT '任务命令',
  `status` int(1) DEFAULT NULL COMMENT '执行结果（-1失败，0执行中，1成功）',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每日定时操作执行结果表';

