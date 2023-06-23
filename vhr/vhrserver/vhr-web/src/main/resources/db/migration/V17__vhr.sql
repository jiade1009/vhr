CREATE TABLE `stock_return_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL,
  `date_research` varchar(8) DEFAULT NULL,
  `result_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回头草策略运行结果';

CREATE TABLE `h_stock_return_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL,
  `date_research` varchar(8) DEFAULT NULL,
  `result_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回头草策略运行结果';

INSERT INTO `menu` (`id`, `url`, `path`, `component`, `name`, `iconCls`, `keepAlive`, `requireAuth`, `parentId`, `enabled`, `sortOrder`)
VALUES
	(10, '/', '/home', 'Home', 'Qt预测', 'fa fa-bar-chart-o', NULL, 1, 1, 1, 5);

INSERT INTO `menu` (`id`, `url`, `path`, `component`, `name`, `iconCls`, `keepAlive`, `requireAuth`, `parentId`, `enabled`, `sortOrder`)
VALUES
	(149, '/stock/qtweeklylineresult/**', '/stock/qtweeklylineresult', 'QtStockWeeklylineResult', '周线数据管理', NULL, NULL, 1, 10, 1, 1),
	(150, '/stock/qtweeklylineemaresult/**', '/stock/qtweeklylineemaresult', 'QtStockWeeklylineEmaResult', '周线ema数据管理', NULL, NULL, 1, 10, 1, 2),
	(151, '/stock/qthold/**', '/stock/qthold', 'QtStockHold', '股票池管理', NULL, NULL, 1, 10, 1, 3),
	(152, '/stock/qtbuyrule/**', '/stock/qtbuyrule', 'QtStockBuyRule', '买入策略管理', NULL, NULL, 1, 10, 1, 5),
	(153, '/stock/qtsellrule/**', '/stock/qtsellrule', 'QtStockSellRule', '卖出策略管理', NULL, NULL, 1, 10, 1, 6),
	(154, '/stock/qtprofittotal/**', '/stock/qtprofittotal', 'QtStockProfitTotal', '股票盈亏', NULL, NULL, 1, 10, 1, 4),
	(155, '/stock/returnresult/**', '/stock/returnresult', 'StockReturnResult', '回头草策略结果', NULL, NULL, 1, 7, 1, 8),
	(156, '/stock/hreturnresult/**', '/stock/hreturnresult', 'HStockReturnResult', '回头草策略结果', NULL, NULL, 1, 9, 1, 5);
