package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : CommandType
 * @description : 股票操作类型
 * @datetime : 2022年 11月 23日 16:55
 * @version: : 1.0
 */
public enum CommandType {
    // A股相关指令
    WEEKLY,  // 周线数据下载
    WEEKLY_EMA, // ema数据生成
    DAILY_REFRESH, // 收盘股票信息更新
    BUY, // 每日开盘买入
    BUY_RULE, // 每日买入策略运行
    CODE_REFRESH,  // 每周股票代码刷新
    TRADE_DATE,  // 交易日数据更新
    CJCX,  // 收盘股票成交信息查询
    U_RETURN_RUN,  //  回头草策略运行
    //  H股相关指令
    H_WEEKLY, //周线数据下载
    H_WEEKLY_EMA, //ema数据生成
    H_BUY_RULE, //每日买入策略运行
    H_U_RETURN_RUN; //每日回头草策略运行

    public String getCommandDesc() {
        String desc = "";
        switch (this) {
            case H_WEEKLY:
            case WEEKLY:
                desc = "周线数据生成";
                break;
            case H_WEEKLY_EMA:
            case WEEKLY_EMA:
                desc = "ema数据生成";
                break;
            case DAILY_REFRESH:
                desc = "收盘数据更新";
                break;
            case BUY:
                desc = "每日开盘买入";
                break;
            case H_BUY_RULE:
            case BUY_RULE:
                desc = "买入策略运行";
                break;
            case CODE_REFRESH:
                desc = "股票代码刷新";
                break;
            case TRADE_DATE:
                desc = "交易日数据更新";
                break;
            case CJCX:
                desc = "成交信息查询";
                break;
            case H_U_RETURN_RUN:
            case U_RETURN_RUN:
                desc = "回头草策略运行";
                break;
        }
        return desc;
    }

}
