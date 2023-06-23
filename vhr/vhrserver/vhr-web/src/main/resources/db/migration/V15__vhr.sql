/* 增加QT预测模块 */
CREATE TABLE `stock_qt_buy_rule` (
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
INSERT INTO `stock_qt_buy_rule` (`id`, `time_market`, `time_market_option`, `rule_period`, `turnover_limit`, `turnover_limit_option`, `conver_limit`, `conver_limit_option`, `shock_limit`, `shock_limit_option`, `time_create`, `time_update`, `status`, `buy_price_limit`, `buy_price_limit_option`)
VALUES
	(1, 3, 1, 52, 20, 1, 1.15, 1, 0.45, 1, '2023-02-22 10:40:44', '2023-02-22 10:40:53', 1, 1.03, 1);


CREATE TABLE `stock_qt_sell_rule` (
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
INSERT INTO `stock_qt_sell_rule` (`id`, `sell_ratio`, `p1_ratio`, `p2_ratio`, `p3_ratio`, `p4_ratio`, `p5_ratio`, `sp1_ratio`, `sp2_ratio`, `sp3_ratio`, `sp4_ratio`, `time_create`, `time_update`, `status`)
VALUES
	(1, 0.5, 1.15, 1.1, 1.1, 1.1, 1.1, 0.85, 0.87, 0.89, 0.91, NULL, NULL, 1);


CREATE TABLE `stock_qt_weekly_line_result` (
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

CREATE TABLE `stock_qt_weekly_line_ema_result` (
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
  KEY `weekly_line_result_id_fk` (`weekly_line_id`),
  CONSTRAINT `stock_qt_weekly_line_ema_result_ibfk_1` FOREIGN KEY (`weekly_line_id`) REFERENCES `stock_qt_weekly_line_result` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE `stock_qt_hold` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '股票代码',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT 0 COMMENT '状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束、7交易失败',
  `buy_amount` int(11) DEFAULT 0 COMMENT '买入股票数',
  `buy_price` float DEFAULT 0 COMMENT '买入价格',
  `hold_amount` int(11) DEFAULT 0 COMMENT '持有股票数',
  `sell_stage` int(11) DEFAULT 0 COMMENT '卖出阶段，-1止损阶段，0初始阶段，1止盈1阶段，2止盈2阶段',
  `weekly_ema_result_id` int(11) DEFAULT NULL COMMENT '关联的ema_result_id',
  `note` varchar(500) DEFAULT '' COMMENT '备注',
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
  `buy_total` float DEFAULT 0 COMMENT '买入总金额',
  `buy_fee` float DEFAULT 0 COMMENT '买入手续费',
  `time_buy` timestamp NULL DEFAULT NULL COMMENT '购买时间',
  `time_sell` timestamp NULL DEFAULT NULL COMMENT '卖出时间',
  `buy_rule_id` int(11) DEFAULT NULL COMMENT '买入策略id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `stock_qt_hold_bonus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL COMMENT '股票代码',
  `donate_rate` float DEFAULT 0 COMMENT '送股比例',
  `increase_rate` float DEFAULT 0 COMMENT '转增比例',
  `bonus_rate` float DEFAULT 0 COMMENT '派息比例',
  `date_register` varchar(20) DEFAULT NULL COMMENT '股权登记日',
  `date_ex_right` varchar(20) DEFAULT NULL COMMENT '除权日期',
  `date_ex_bonus` varchar(20) DEFAULT NULL COMMENT '除息日',
  `bonus_note` varchar(255) DEFAULT NULL COMMENT '分红说明',
  `bonus_type` varchar(20) DEFAULT NULL COMMENT '分红类型(年度分红/中期分红)',
  `date_report` varchar(20) DEFAULT NULL COMMENT '报告时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `stock_qt_hold_trade` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hold_id` int(11) unsigned NOT NULL,
  `code` varchar(255) NOT NULL DEFAULT '',
  `amount` int(11) DEFAULT 100 COMMENT '委托数量',
  `trade_type` int(11) DEFAULT 0 COMMENT '交易类型：0买入，1卖出，2买入撤销，3卖出撤销，4分红配送',
  `trade_note` varchar(500) DEFAULT NULL COMMENT '交易备注',
  `orderid` varchar(50) DEFAULT NULL COMMENT '委托合同编号',
  `message` varchar(1000) DEFAULT NULL COMMENT '委托/撤单状态信息',
  `status` int(11) DEFAULT NULL COMMENT '委托/撤单状态，1结束，0执行中，-1失败',
  `note` varchar(1000) DEFAULT NULL COMMENT '投资备注',
  `taskid` varchar(50) DEFAULT NULL COMMENT '任务/指令编号',
  `taskstatus` int(11) DEFAULT NULL COMMENT '任务/指令状态，0 未知,1 等待,2 提交中,3 执行中,4 暂停,5 撤销中,6 异常撤销中,7 完成,8 已撤,9 打回,10  异常终止,11  放弃，目前用于组合交易中，放弃补单,12  强制终止',
  `taskmsg` varchar(1000) DEFAULT NULL COMMENT '任务/指令状态信息',
  `taskpro` varchar(500) DEFAULT NULL COMMENT '任务进度',
  `ordernum` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `time_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `time_update` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `price` float DEFAULT 1,
  `counter` int(11) DEFAULT 0 COMMENT '查询结果计数器，默认为0',
  `price_type` varchar(10) DEFAULT '1' COMMENT '委托价格类型：1最新价、2对手价、3指定价、B1买1价、B2买2价、S1卖1价、S2卖2价',
  `cj_price` float DEFAULT 0 COMMENT '成交价格',
  `cj_amount` int(11) DEFAULT 0 COMMENT '成交数量',
  `cj_time` varchar(20) DEFAULT NULL COMMENT '成交时间',
  `cj_fee` float DEFAULT NULL COMMENT '成交手续费',
  `cj_total` float DEFAULT NULL COMMENT '成交金额',
  PRIMARY KEY (`id`),
  KEY `hold_id_fk` (`hold_id`),
  CONSTRAINT `stock_qt_hold_trade_ibfk_1` FOREIGN KEY (`hold_id`) REFERENCES `stock_qt_hold` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `stock_qt_profit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hold_id` int(11) NOT NULL COMMENT '股票池id',
  `date_research` varchar(8) NOT NULL DEFAULT '' COMMENT '盈亏调研日期',
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '股票代码',
  `price_close` float DEFAULT 0 COMMENT '收盘价',
  `amount_hold` int(11) DEFAULT 0 COMMENT '持股数',
  `total` float DEFAULT 0 COMMENT '总市值',
  `time_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `profit` float DEFAULT 0 COMMENT '盈亏值',
  `profit_rate` float DEFAULT 0 COMMENT '盈亏率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `stock_qt_profit_hold` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hold_id` int(11) NOT NULL COMMENT '股票池id',
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '股票代码',
  `total_begin` float DEFAULT 0 COMMENT '成本',
  `time_create` timestamp NULL DEFAULT NULL,
  `profit` float DEFAULT 0 COMMENT '盈亏值',
  `profit_rate` float DEFAULT 0 COMMENT '盈亏率',
  `time_buy` timestamp NULL DEFAULT NULL COMMENT '购买时间',
  `time_sell` timestamp NULL DEFAULT NULL COMMENT '清仓时间',
  `hold_days` int(11) DEFAULT 1 COMMENT '持股天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

