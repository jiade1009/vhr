/* 修改股票执行结果表，增加备注字段 */
alter table stock_execute_result add `generate_type` int(11) DEFAULT 1 COMMENT '生成方式，0手动，1自动';
