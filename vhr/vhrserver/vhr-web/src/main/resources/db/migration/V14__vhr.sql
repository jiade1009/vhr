/* 股票盈亏月报表 */
CREATE TABLE `stock_profit_month` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `month_research` varchar(6) NOT NULL DEFAULT '' COMMENT '盈亏调研月份',
  `total_begin` float DEFAULT 0 COMMENT '月初始总市值',
  `profit` float DEFAULT 0 COMMENT '盈亏值',
  `profit_rate` float DEFAULT 0 COMMENT '盈亏率',
  `time_update` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `time_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `total_end` float DEFAULT 0 COMMENT '总市值',
  `profit_amount` int(11) DEFAULT NULL COMMENT '盈利交易笔数',
  `loss_amount` int(11) DEFAULT NULL COMMENT '亏损交易笔数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* 股票盈亏年报表 */
CREATE TABLE `stock_profit_year` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `year_research` varchar(4) NOT NULL DEFAULT '' COMMENT '盈亏调研年份',
  `total_begin` float DEFAULT 0 COMMENT '年初始总市值',
  `profit` float DEFAULT 0 COMMENT '盈亏值',
  `profit_rate` float DEFAULT 0 COMMENT '盈亏率',
  `time_update` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `total_end` float DEFAULT 0 COMMENT '总市值',
  `time_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `profit_amount` int(11) DEFAULT NULL COMMENT '盈利交易笔数',
  `loss_amount` int(11) DEFAULT NULL COMMENT '亏损交易笔数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;