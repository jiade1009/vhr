/* 新增加分级止盈功能模块 */
CREATE TABLE `stock_substep_profit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '股票名称',
  `code` varchar(255) DEFAULT NULL COMMENT '股票代码',
  `profit_stage` int(11) DEFAULT 0 COMMENT '止盈阶段0：未止盈、1：p3阶段、2：p5阶段、3：p8阶段、4：p10阶段、5：p105-1阶段、6：p105-2阶段、7：p105-3阶段、8：终极',
  `status` int(11) DEFAULT 1 COMMENT '状态，0：已结束、1：运行中',
  `price_cost` float DEFAULT 0 COMMENT '成本价',
  `amount_cost` int(11) DEFAULT 0 COMMENT '成本股票数，即统计入成本中的股票数',
  `amount_able` int(11) DEFAULT 0 COMMENT '可用股票数',
  `amount_substep` int(11) DEFAULT 0 COMMENT '分级统计股票数，用于计算每次分级止盈的股票数',
  `price_p3` float DEFAULT 0 COMMENT 'p3阶段成交价',
  `amount_p3` int(11) DEFAULT 0 COMMENT 'p3阶段成交股票数',
  `price_p5` float DEFAULT 0 COMMENT 'p5阶段成交价',
  `amount_p5` int(11) DEFAULT 0 COMMENT 'p5阶段成交股票数',
  `price_p8` float DEFAULT 0 COMMENT 'p8阶段成交价',
  `amount_p8` int(11) DEFAULT 0 COMMENT 'p8阶段成交股票数',
  `price_p10` float DEFAULT 0 COMMENT 'p10阶段成交价',
  `amount_p10` int(11) DEFAULT 0 COMMENT 'p10阶段成交股票数',
  `price_p1051` float DEFAULT 0 COMMENT 'p105-1阶段成交价',
  `amount_p1051` int(11) DEFAULT 0 COMMENT 'p105-1阶段成交股票数',
  `price_p1052` float DEFAULT 0 COMMENT 'p105-2阶段成交价',
  `amount_p1052` int(11) DEFAULT 0 COMMENT 'p105-2阶段成交股票数',
  `price_p1053` float DEFAULT 0 COMMENT 'p105-3阶段成交价',
  `amount_p1053` int(11) DEFAULT 0 COMMENT 'p105-3阶段成交股票数',
  `last_trade_type` int(11) DEFAULT 0 COMMENT '最新交易类型（加仓0、止盈1、止损2、减仓3、清仓4、调仓5）',
  `price_stop_loss` float DEFAULT 0 COMMENT '止损价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COMMENT='分级止盈表';

CREATE TABLE `stock_substep_trade` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '股票代码',
  `price` float DEFAULT 0 COMMENT '成交价',
  `amount` int(11) DEFAULT 0 COMMENT '成交数量',
  `type` int(11) DEFAULT 0 COMMENT '交易类型（加仓0、止盈1、止损2、减仓3、清仓4、调仓5）',
  `date_trade` varchar(8) DEFAULT NULL COMMENT '交易日期',
  `substep_profit_id` int(11) unsigned NOT NULL COMMENT '分级止盈id',
  PRIMARY KEY (`id`),
  KEY `substep_profit_id` (`substep_profit_id`),
  CONSTRAINT `stock_substep_trade_ibfk_1` FOREIGN KEY (`substep_profit_id`) REFERENCES `stock_substep_profit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COMMENT='分级交易表';

CREATE TABLE `stock_substep_trigger` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '股票代码',
  `stage_trigger` int(11) DEFAULT 0 COMMENT '触发哪个止盈阶段，止盈阶段0：未止盈、1：p3阶段、2：p5阶段、3：p8阶段、4：p10阶段、5：p105-1阶段、6：p105-2阶段、7：p105-3阶段、8：终极',
  `date_trigger` varchar(8) DEFAULT NULL COMMENT '触发日期',
  `substep_profit_id` int(11) unsigned NOT NULL COMMENT '分级止盈id',
  PRIMARY KEY (`id`),
  KEY `substep_profit_id` (`substep_profit_id`),
  CONSTRAINT `stock_substep_trigger_ibfk_1` FOREIGN KEY (`substep_profit_id`) REFERENCES `stock_substep_profit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分级触发结果表';

INSERT INTO `menu` (`id`, `url`, `path`, `component`, `name`, `iconCls`, `keepAlive`, `requireAuth`, `parentId`, `enabled`, `sortOrder`)
VALUES
	(165, '/stock/substepprofit/**', '/stock/substepprofit', 'StockSubstepProfit', '分级止盈列表', NULL, NULL, 1, 12, 1, 2);
