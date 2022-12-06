/* 修改股票池，增加备注字段 */
alter table stock_hold add `note` varchar(500) COMMENT '备注';
alter table stock_hold add `price_close` float COMMENT '昨日收盘价';
alter table stock_hold add `price_high` float COMMENT '近期最高价';
alter table stock_hold add `price_low` float COMMENT '近期最低价';
alter table stock_hold add `enhance_white_sign` tinyint(1) DEFAULT 0 COMMENT '是否发出增强白信号, 0未发出，1已发出';
alter table stock_hold add `lose_enhance_white_sign` tinyint(1) DEFAULT 0 COMMENT '是否失去增强白信号, 0未失去，1已失去';
alter table stock_hold add `sell_phase` int(11) NOT NULL DEFAULT 0 COMMENT '卖出阶段，-1止损阶段，0初始阶段，1止盈1阶段';
alter table stock_hold add `price_sell_high` float COMMENT '下一阶段价格或者是卖出最高价阀值';
alter table stock_hold add `price_sell_low` float COMMENT '卖出最低价阀值';

/* 修改股票交易表，增加备注字段 */
alter table stock_hold_trade add `counter` int(11) DEFAULT 0 COMMENT '查询结果计数器，默认为0';

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

