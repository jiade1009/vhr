/* 修改股票池表，增加股票池加入方式 */
alter table stock_hold add `generate_type` int(11) DEFAULT 0 COMMENT '加入方式，0直接，1回头草';
