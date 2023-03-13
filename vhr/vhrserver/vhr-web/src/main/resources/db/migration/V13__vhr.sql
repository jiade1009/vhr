/* 修改股票池表 */
alter table stock_hold add `time_buy` timestamp NULL DEFAULT NULL COMMENT '购买时间';
alter table stock_hold add `time_sell` timestamp NULL DEFAULT NULL COMMENT '卖出时间';

/* 更新股票池的购买时间、卖出时间*/
update stock_hold h, stock_hold_trade t set h.time_buy=t.time_create where   h.status>1 and h.status<7 and t.trade_type=0 and t.status=1 and h.id = t.hold_id;
update stock_hold h, stock_hold_trade t set h.time_sell=t.time_create where   h.status>1 and h.status<7 and t.trade_type=1 and t.status=1 and h.id = t.hold_id;


/* 股票分红记录 */
CREATE TABLE `stock_hold_bonus` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/* 股票资金流水表 */
CREATE TABLE `stock_fund_flow` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL COMMENT '操作金额',
  `trade_type` int(11) DEFAULT NULL COMMENT '1.资金转入2.资金转出3.股票买入4.股票卖出5.股票红利（股票除息）6.利息归本7.股票交税',
  `income_expense` int(1) DEFAULT NULL COMMENT '收支类型（进1，出-1）',
  `time_create` timestamp NULL DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `database_type` (`id`, `code`, `name`, `value`, `description`, `sort_order`, `type`, `sys`)
VALUES
	(15, 'stk_fund_usable', '可用资金', '0', '股票的可用资金总额', 0, 'stk_fund', 1);
