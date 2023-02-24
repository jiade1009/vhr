INSERT INTO `database_type` (`id`, `code`, `name`, `value`, `description`, `sort_order`, `type`, `sys`)
VALUES
	(8, 'stk_h_weekly_start_date', '港股周线读取起始日期', '20200101', '周线读取起始日期，日期格式：yyyyMMdd', 0, NULL, 1),
	(9, 'stk_h_auto_order', '港股自动下单开关', '1', '自动下单开关，0关闭，1开启', 0, 'stk_h_auto', 1),
	(10, 'stk_h_ema_18', '港股周线18ema', '18', '', 0, 'stk_h_ema', 1),
	(11, 'stk_h_ema_25', '港股周线25ema', '25', '', 1, 'stk_h_ema', 1),
	(12, 'stk_h_ema_75', '港股周线75ema', '75', '', 2, 'stk_h_ema', 1),
	(13, 'stk_h_buy_amount', '港股股票购买总金额', '30000', '自动下单购买股票的总金额', 1, 'stk_h_buy_amount', 1);