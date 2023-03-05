/* 修改股票池表，增加股票池加入方式 */
alter table stock_hold add `buy_total` float DEFAULT 0 COMMENT '买入总金额';
alter table stock_hold add `buy_fee` float DEFAULT 0 COMMENT '买入手续费';
-- alter table stock_hold add `hold_begin` varchar(8) DEFAULT NULL COMMENT '持股开始日期';
-- alter table stock_hold add `hold_end` varchar(8) DEFAULT NULL COMMENT '持股结束日期';

CREATE TABLE `stock_profit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hold_id` int(11) NOT NULL COMMENT '股票池id',
  `date_research` varchar(8) NOT NULL DEFAULT '' COMMENT '盈亏调研日期',
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '股票代码',
  `price_close` float DEFAULT 0 COMMENT '收盘价',
  `amount_hold` int(11) DEFAULT 0 COMMENT '持股数',
  `total` float DEFAULT 0 COMMENT '总市值',
  `time_create` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '创建时间',
  `profit` float DEFAULT 0 COMMENT '盈亏值',
  `profit_rate` float DEFAULT 0 COMMENT '盈亏率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `stock_profit_total` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `date_research` varchar(8) NOT NULL DEFAULT '' COMMENT '盈亏调研日期',
  `total` float DEFAULT 0 COMMENT '总市值',
  `time_create` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '创建时间',
  `profit` float DEFAULT 0 COMMENT '盈亏值',
  `profit_rate` float DEFAULT 0 COMMENT '盈亏率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

