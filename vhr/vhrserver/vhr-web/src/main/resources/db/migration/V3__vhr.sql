/* 修改买入策略规则，增加买入价限价字段 */


alter table stock_buy_rule add buy_price_limit FLOAT NOT NULL DEFAULT 1.03 COMMENT '买入价限价，买入价格/昨日的EMA_75的价格，默认1.03';
alter table stock_buy_rule add buy_price_limit_option TINYINT(1) NOT NULL DEFAULT 1 COMMENT '买入价限价开关';