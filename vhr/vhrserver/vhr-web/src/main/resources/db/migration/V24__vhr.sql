/* 修改股票池表 */

alter table stock_hold add `time_white_sign` varchar(8) DEFAULT '' COMMENT '股票白色信号发起日期';
alter table stock_hold add `time_hold_begin` timestamp NULL DEFAULT NULL COMMENT '股票持股有效起始日期';
alter table stock_hold add `price_peak_top` float DEFAULT 0 COMMENT '第一次峰顶价格';
alter table stock_hold add `price_up_rate_1` float DEFAULT 0 COMMENT '第一次涨幅比率';
alter table stock_hold add `price_up_rate_2` float DEFAULT 0 COMMENT '第二次涨幅比率';
alter table stock_hold add `price_up_rate_3` float DEFAULT 0 COMMENT '第三次涨幅比率，如果第三次涨幅比例为0，那么意味着第二次涨幅进行清仓操作';

alter table stock_qt_hold add `time_white_sign` varchar(8) DEFAULT '' COMMENT '股票白色信号发起日期';
alter table stock_qt_hold add `time_hold_begin` timestamp NULL DEFAULT NULL COMMENT '股票持股有效起始日期';
alter table stock_qt_hold add `price_peak_top` float DEFAULT 0 COMMENT '第一次峰顶价格';
alter table stock_qt_hold add `price_up_rate_1` float DEFAULT 0 COMMENT '第一次涨幅比率';
alter table stock_qt_hold add `price_up_rate_2` float DEFAULT 0 COMMENT '第二次涨幅比率';
alter table stock_qt_hold add `price_up_rate_3` float DEFAULT 0 COMMENT '第三次涨幅比率，如果第三次涨幅比例为0，那么意味着第二次涨幅进行清仓操作';

alter table stock_qt_ureturn_hold add `time_white_sign` varchar(8) DEFAULT '' COMMENT '股票白色信号发起日期';
alter table stock_qt_ureturn_hold add `time_hold_begin` timestamp NULL DEFAULT NULL COMMENT '股票持股有效起始日期';
alter table stock_qt_ureturn_hold add `price_peak_top` float DEFAULT 0 COMMENT '第一次峰顶价格';
alter table stock_qt_ureturn_hold add `price_up_rate_1` float DEFAULT 0 COMMENT '第一次涨幅比率';
alter table stock_qt_ureturn_hold add `price_up_rate_2` float DEFAULT 0 COMMENT '第二次涨幅比率';
alter table stock_qt_ureturn_hold add `price_up_rate_3` float DEFAULT 0 COMMENT '第三次涨幅比率，如果第三次涨幅比例为0，那么意味着第二次涨幅进行清仓操作';