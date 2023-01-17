package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : CommandType
 * @description : 股票操作类型
 * @datetime : 2022年 11月 23日 16:55
 * @version: : 1.0
 */
public enum CommandType {

    WEEKLY,  // 周线数据下载
    WEEKLY_EMA, // ema数据生成
    DAILY_REFRESH, // 收盘股票信息更新
    BUY, // 每日开盘买入
    BUY_RULE, // 每日买入策略运行
    CODE_REFRESH,  // 每周股票代码刷新
    TRADE_DATE,  // 交易日数据更新
    CJCX;  // 收盘股票成交信息查询

}
