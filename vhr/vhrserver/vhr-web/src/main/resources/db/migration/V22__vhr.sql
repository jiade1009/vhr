INSERT INTO `database_type` (`id`, `code`, `name`, `value`, `description`, `sort_order`, `type`, `sys`)
VALUES
	(22, 'stk_a_run_buy_type', 'A股执行买入方案', '1', '1.只买回头草信号，2.只买蓝色信号，3.买蓝色和回头草信号', 1, 'stk_a_run_buy_type', 1),
	(23, 'stk_h_run_buy_type', 'H股执行买入方案', '1', '1.只买回头草信号，2.只买蓝色信号，3.买蓝色和回头草信号', 1, 'stk_run_buy_type', 1),
	(24, 'stk_u_run_buy_type', 'U股执行买入方案', '1', '1.只买回头草信号，2.只买蓝色信号，3.买蓝色和回头草信号', 1, 'stk_run_buy_type', 1),
	(25, 'stk_a_run_sell_type', 'A股执行卖出方案', '1', '1.采用卖出策略V1版本, 2.采用卖出策略V2版本', 1, 'stk_a_run_sell_type', 1),
	(26, 'stk_h_run_sell_type', 'H股执行卖出方案', '1', '1.采用卖出策略V1版本, 2.采用卖出策略V2版本', 1, 'stk_h_run_sell_type', 1),
	(27, 'stk_u_run_sell_type', 'U股执行卖出方案', '1', '1.采用卖出策略V1版本, 2.采用卖出策略V2版本', 1, 'stk_u_run_sell_type', 1);


update database_type set code="stk_a_weekly_start_date" where code="stk_weekly_start_date";

update database_type set type="stk_a_ema" where type="stk_ema";

update database_type set code="stk_a_ema_18" where code="stk_ema_18";
update database_type set code="stk_a_ema_25" where code="stk_ema_25";
update database_type set code="stk_a_ema_75" where code="stk_ema_75";


update database_type set code="stk_a_buy_amount", type="stk_a_buy_amount", name="A股股票购买总金额" where code="stk_buy_amount";






