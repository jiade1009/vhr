/* 修改股票池表，增加股票池加入方式 */
alter table stock_message_log add `flag` varchar(127) DEFAULT 0 COMMENT '备注信息';
