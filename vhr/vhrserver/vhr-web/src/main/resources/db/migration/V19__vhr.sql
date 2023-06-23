INSERT INTO `database_type` (`id`, `code`, `name`, `value`, `description`, `sort_order`, `type`, `sys`)
VALUES
	(16, 'stk_u_weekly_start_date', '美股周线读取起始日期', '20200101', '周线读取起始日期，日期格式：yyyyMMdd', 0, NULL, 1),
	(17, 'stk_u_auto_order', '美股自动下单开关', '1', '自动下单开关，0关闭，1开启', 0, 'stk_u_auto', 1),
	(18, 'stk_u_ema_18', '美股周线18ema', '18', '', 0, 'stk_u_ema', 1),
	(19, 'stk_u_ema_25', '美股周线25ema', '25', '', 1, 'stk_u_ema', 1),
	(20, 'stk_u_ema_75', '美股周线75ema', '75', '', 2, 'stk_u_ema', 1),
	(21, 'stk_u_buy_amount', '美股股票购买总金额', '30000', '自动下单购买股票的总金额', 1, 'stk_u_buy_amount', 1);

INSERT INTO `menu` (`id`, `url`, `path`, `component`, `name`, `iconCls`, `keepAlive`, `requireAuth`, `parentId`, `enabled`, `sortOrder`)
VALUES
	(11, '/', '/home', 'Home', 'U股交易', 'fa fa-bar-chart-o', NULL, 1, 1, 1, 3),
	(157, '/stock/ubasicinfo/**', '/stock/ubasicinfo', 'UStockBasicinfo', 'H股代码', NULL, NULL, 1, 8, 1, 2),
	(158, '/stock/ubuyrule/**', '/stock/ubuyrule', 'UStockBuyRule', '买入策略管理', NULL, NULL, 1, 11, 1, 4),
	(159, '/stock/uhold/**', '/stock/uhold', 'UStockHold', '股票池管理', NULL, NULL, 1, 11, 1, 3),
	(160, '/stock/ureturnresult/**', '/stock/ureturnresult', 'UStockReturnResult', '回头草策略结果', NULL, NULL, 1, 11, 1, 5),
	(161, '/stock/uweeklylineemaresult/**', '/stock/uweeklylineemaresult', 'UStockWeeklylineEmaResult', '周线ema数据管理', NULL, NULL, 1, 11, 1, 2),
	(162, '/stock/uweeklylineresult/**', '/stock/uweeklylineresult', 'UStockWeeklylineResult', '周线数据管理', NULL, NULL, 1, 11, 1, 1);

-- Create syntax for TABLE 'u_stock_basic_info'
CREATE TABLE `u_stock_basic_info` (
  `code` varchar(500) CHARACTER SET utf8mb3 NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb3 DEFAULT NULL,
  `date_publish` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `ix_vhr_u_stock_basic_info_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create syntax for TABLE 'u_stock_buy_rule'
CREATE TABLE `u_stock_buy_rule` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_market` int(11) NOT NULL DEFAULT 3 COMMENT '上市时长要求',
  `time_market_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '上市时长是否必要，0否，1是',
  `rule_period` int(11) NOT NULL DEFAULT 52 COMMENT '策略周期，单位为周',
  `turnover_limit` int(11) NOT NULL DEFAULT 0 COMMENT '成交量额度',
  `turnover_limit_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成交量额度是否必要，0否，1是',
  `conver_limit` float NOT NULL DEFAULT 1.1 COMMENT '收敛度，当天EMA75/当天EMA18<conver_limit ',
  `conver_limit_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '收敛度是否必要，0否，1是',
  `shock_limit` float NOT NULL DEFAULT 0.45 COMMENT '下跌幅度,最低价/最高价<=shock_limit',
  `shock_limit_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '下跌幅度是否必要，0否，1是',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '2结束，1运行，0草稿',
  `buy_price_limit` float NOT NULL DEFAULT 1.03 COMMENT '买入价限价，买入价格/昨日的EMA_75的价格，默认1.03',
  `buy_price_limit_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '买入价限价开关',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create syntax for TABLE 'u_stock_hold'
CREATE TABLE `u_stock_hold` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '股票代码',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT 0 COMMENT '状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束、7交易失败',
  `buy_amount` int(11) DEFAULT 0 COMMENT '买入股票数',
  `buy_price` float DEFAULT 0 COMMENT '买入价格',
  `hold_amount` int(11) DEFAULT 0 COMMENT '持有股票数',
  `sell_stage` int(11) DEFAULT 0 COMMENT '卖出阶段',
  `weekly_ema_result_id` int(11) DEFAULT NULL COMMENT '关联的ema_result_id',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `price_close` float DEFAULT 0 COMMENT '昨日收盘价',
  `price_high` float DEFAULT 0 COMMENT '近期最高价',
  `price_low` float DEFAULT 0 COMMENT '近期最低价',
  `enhance_white_sign` tinyint(1) DEFAULT 0 COMMENT '是否发出增强白信号, 0未发出，1已发出',
  `lose_enhance_white_sign` tinyint(1) DEFAULT 0 COMMENT '是否失去增强白信号, 0未失去，1已失去',
  `price_sell_high` float DEFAULT 0 COMMENT '下一阶段价格或者是卖出最高价阀值',
  `price_sell_low` float DEFAULT 0 COMMENT '卖出最低价阀值',
  `close_ema_18` float DEFAULT 0 COMMENT '最近一个交易日的close_ema_18值',
  `price_stop` float DEFAULT 0 COMMENT '止损价格',
  `generate_type` int(11) DEFAULT 0 COMMENT '加入方式，0直接，1回头草',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create syntax for TABLE 'u_stock_return_result'
CREATE TABLE `u_stock_return_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL,
  `date_research` varchar(8) DEFAULT NULL,
  `result_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回头草策略运行结果';

-- Create syntax for TABLE 'u_stock_trade_date'
CREATE TABLE `u_stock_trade_date` (
  `trade_date` varchar(255) CHARACTER SET utf8mb3 NOT NULL,
  PRIMARY KEY (`trade_date`),
  KEY `ix_vhr_stock_a_trade_date_trade_date` (`trade_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create syntax for TABLE 'u_stock_weekly_line_result'
CREATE TABLE `u_stock_weekly_line_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rehabilitation_way` varchar(10) DEFAULT NULL COMMENT '复权方式',
  `date_weekly_research` varchar(8) NOT NULL DEFAULT '' COMMENT '周线调研日期',
  `date_weekly_start` varchar(8) NOT NULL DEFAULT '' COMMENT '周线调研起始日期',
  `result` int(11) NOT NULL DEFAULT 0 COMMENT '周线导入结果，0成功，1失败',
  `ema_result` int(11) NOT NULL DEFAULT 0 COMMENT 'expma生成状态（0未生成、1已生成、2生成失败）',
  `time_create` timestamp NULL DEFAULT NULL COMMENT '运行开始时间',
  `time_end` timestamp NULL DEFAULT NULL COMMENT '运行结束时间',
  `generate_type` int(11) DEFAULT 0 COMMENT '生成方式，0手动，1自动',
  `result_desc` longtext DEFAULT NULL COMMENT '生成结果备注',
  `ema_result_desc` longtext DEFAULT NULL COMMENT 'ema生成结果备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create syntax for TABLE 'u_stock_weekly_line_ema_result'
CREATE TABLE `u_stock_weekly_line_ema_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL COMMENT 'ema_result创建时间',
  `time_end` timestamp NULL DEFAULT NULL COMMENT 'ema_result创建成功时间',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT 'ema生成状态，0成功，-1失败',
  `status_desc` longtext DEFAULT NULL COMMENT 'ema生成结果备注信息',
  `weekly_line_id` int(11) unsigned DEFAULT NULL,
  `ema_index` varchar(50) DEFAULT NULL COMMENT 'ema数据线，例如18_25_75',
  `time_run_begin` timestamp NULL DEFAULT NULL COMMENT 'ema数据运行买入规则开始时间',
  `time_run_end` timestamp NULL DEFAULT NULL COMMENT 'ema数据运行买入规则结束时间',
  `run_status` int(11) DEFAULT 0 COMMENT 'ema运行买入规则状态，0未运行，1运行成功，-1运行失败',
  `run_status_desc` longtext DEFAULT NULL COMMENT 'ema运行买入规则结果备注',
  PRIMARY KEY (`id`),
  KEY `weekly_line_id` (`weekly_line_id`),
  CONSTRAINT `u_stock_weekly_line_ema_result_ibfk_2` FOREIGN KEY (`weekly_line_id`) REFERENCES `u_stock_weekly_line_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

