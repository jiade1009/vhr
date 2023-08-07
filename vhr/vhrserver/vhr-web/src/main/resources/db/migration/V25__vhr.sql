/* 修改股票池表 */
alter table h_stock_hold add `buy_total` float DEFAULT 0 COMMENT '买入总金额';
alter table h_stock_hold add `buy_fee` float DEFAULT 0 COMMENT '买入手续费';
alter table h_stock_hold add `time_buy` timestamp NULL DEFAULT NULL COMMENT '购买时间';
alter table h_stock_hold add `time_sell` timestamp NULL DEFAULT NULL COMMENT '卖出时间';
alter table h_stock_hold add `time_white_sign` varchar(8) DEFAULT '' COMMENT '股票白色信号发起日期';
alter table h_stock_hold add `time_hold_begin` timestamp NULL DEFAULT NULL COMMENT '股票持股有效起始日期';
alter table h_stock_hold add `price_peak_top` float DEFAULT 0 COMMENT '第一次峰顶价格';
alter table h_stock_hold add `price_up_rate_1` float DEFAULT 0 COMMENT '第一次涨幅比率';
alter table h_stock_hold add `price_up_rate_2` float DEFAULT 0 COMMENT '第二次涨幅比率';
alter table h_stock_hold add `price_up_rate_3` float DEFAULT 0 COMMENT '第三次涨幅比率，如果第三次涨幅比例为0，那么意味着第二次涨幅进行清仓操作';


alter table u_stock_hold add `buy_total` float DEFAULT 0 COMMENT '买入总金额';
alter table u_stock_hold add `buy_fee` float DEFAULT 0 COMMENT '买入手续费';
alter table u_stock_hold add `time_buy` timestamp NULL DEFAULT NULL COMMENT '购买时间';
alter table u_stock_hold add `time_sell` timestamp NULL DEFAULT NULL COMMENT '卖出时间';
alter table u_stock_hold add `time_white_sign` varchar(8) DEFAULT '' COMMENT '股票白色信号发起日期';
alter table u_stock_hold add `time_hold_begin` timestamp NULL DEFAULT NULL COMMENT '股票持股有效起始日期';
alter table u_stock_hold add `price_peak_top` float DEFAULT 0 COMMENT '第一次峰顶价格';
alter table u_stock_hold add `price_up_rate_1` float DEFAULT 0 COMMENT '第一次涨幅比率';
alter table u_stock_hold add `price_up_rate_2` float DEFAULT 0 COMMENT '第二次涨幅比率';
alter table u_stock_hold add `price_up_rate_3` float DEFAULT 0 COMMENT '第三次涨幅比率，如果第三次涨幅比例为0，那么意味着第二次涨幅进行清仓操作';
