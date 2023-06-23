/* 修改股票池表 */
alter table stock_qt_hold add `is_bull_market` tinyint(1) DEFAULT 1 COMMENT '是否是牛市，0否，1是';
alter table stock_qt_ureturn_hold add `is_bull_market` tinyint(1) DEFAULT 1 COMMENT '是否是牛市，0否，1是';
