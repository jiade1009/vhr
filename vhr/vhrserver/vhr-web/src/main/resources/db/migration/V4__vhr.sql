/* 修改股票池，增加备注字段 */

alter table stock_hold add note varchar(500) COMMENT '备注';