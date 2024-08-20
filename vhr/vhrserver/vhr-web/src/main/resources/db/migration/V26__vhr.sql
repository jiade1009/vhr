/* 新增加ai订单功能模块 */
CREATE TABLE `stock_ai_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '股票名称',
  `code` varchar(255) DEFAULT NULL COMMENT '股票代码',
  `strategy_type` int(11) DEFAULT 0 COMMENT '策略类型，0：到价买出',
  `status` int(11) DEFAULT 1 COMMENT '状态，0：已结束、1：监控中',
  `process` int(11) DEFAULT 0 COMMENT '进度，0：未触发、1：已触发1笔、2停止',
  `trigger_condition` varchar(255) DEFAULT NULL COMMENT '触发条件，eg:当股价 ≤ 2.95触发委托;股价≥9.00元',
  `price_entrust` varchar(127) DEFAULT NULL COMMENT '委托价格',
  `amount_entrust` varchar(127) DEFAULT '0' COMMENT '委托数量',
  `auto_entrust` int(11) DEFAULT 0 COMMENT '自动委托，0：否，1：是',
  `date_begin` date DEFAULT NULL COMMENT '开始日期',
  `date_expire` date DEFAULT NULL COMMENT '截止日期',
  `order_no` varchar(127) DEFAULT NULL COMMENT '订单号',
  `source_note` varchar(500) DEFAULT NULL COMMENT '来源备注',
  `order_source` varchar(127) DEFAULT NULL COMMENT '订单来源，0：国泰君安',
  `note` varchar(500) DEFAULT NULL COMMENT '系统备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `menu` (`id`, `url`, `path`, `component`, `name`, `iconCls`, `keepAlive`, `requireAuth`, `parentId`, `enabled`, `sortOrder`)
VALUES
	(12, '/', '/home', 'Home', '交易监控', 'fa fa-rocket', NULL, 1, 1, 1, 7);
INSERT INTO `menu` (`id`, `url`, `path`, `component`, `name`, `iconCls`, `keepAlive`, `requireAuth`, `parentId`, `enabled`, `sortOrder`)
VALUES
	(164, '/stock/aiorder/**', '/stock/aiorder', 'StockAiOrder', 'AI订单列表', NULL, NULL, 1, 12, 1, 1);
