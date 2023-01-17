/* 修改股票交易表，增加备注字段 */
alter table stock_hold_trade add `cj_price` float DEFAULT 0 COMMENT '成交价格';
alter table stock_hold_trade add `cj_amount` int(11) DEFAULT 0 COMMENT '成交价格';
alter table stock_hold_trade add `cj_time` varchar(20)  COMMENT '成交时间';
alter table stock_hold_trade add `cj_fee` float  COMMENT '成交手续费';
alter table stock_hold_trade add `cj_total` float  COMMENT '成交金额';
