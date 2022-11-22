package org.javaboy.vhr.config;

/**
 * @author : sam
 * @ClassName : BaseConstants
 * @description : 定义一些项目用到的常用常量
 * @datetime : 2022年 10月 20日 10:52
 * @version: : 1.0
 */
public class BaseConstants {

    /**
     * 定义 调用 python接口参数的常量
     */
    public static final String PY_API_LOAD_A_STOCK = "load_a_stock";   // 重新更新A股股票代码
    public static final String PY_API_LOAD_A_CALENDAR = "load_a_calendar";   // 重新更新A股交易日历
    // 生成weekly数据线，传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
    public static final String PY_API_CREATE_A_WEEKLY = "create_a_weekly";
    public static final String PY_API_CREATE_A_EMA = "create_a_ema";   // 生成ema数据线
    public static final String PY_API_RUN_A_BUY_RULE = "run_a_buy_rule";  //  运行A股购买策略
    public static final String PY_API_RUN_A_BUY = "run_a_buy";  //  运行A股股票买入
    public static final String PY_API_RUN_A_BUY_QUERY = "run_a_buy_query";  //  运行A股股票交易结果查询


    // 标记现在开始进行周线数据下载 true/false
    public static final String OPE_STOCK_CREATE_A_WEEKLY = "OPE_STOCK_CREATE_A_WEEKLY";

}
