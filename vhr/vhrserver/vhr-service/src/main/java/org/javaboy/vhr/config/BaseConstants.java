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

    // -----------A股指令 begin--------------
    public static final String PY_API_LOAD_A_STOCK = "load_a_stock";   // 重新更新A股股票代码
    public static final String PY_API_LOAD_A_CALENDAR = "load_a_calendar";   // 重新更新A股交易日历
    // 生成weekly数据线，传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
    public static final String PY_API_CREATE_A_WEEKLY = "create_a_weekly";
    public static final String PY_API_CREATE_A_EMA = "create_a_ema";   // 生成ema数据线
    public static final String PY_API_RUN_A_BUY_RULE = "run_a_buy_rule";  //  运行A股购买策略
    public static final String PY_API_RUN_A_UTURN_RULE = "run_a_uturn_rule";  //  运行A股回头草策略V1
    public static final String PY_API_RUN_A_UTURN_RULE_V2 = "run_a_uturn_rule_v2";  //  运行A股回头草策略V2
    public static final String PY_API_RUN_A_BUY = "run_a_buy";  //  运行A股股票买入
    public static final String PY_API_RUN_A_SELL = "run_a_sell";  //  运行A股股票卖出
    public static final String PY_API_RUN_A_AI_ORDER = "run_a_ai_order";  //  运行智能订单触发查询
    public static final String PY_API_RUN_A_ORDER_QUERY = "run_a_order_query";  //  运行A股股票交易结果（买入和卖出）查询
    public static final String PY_API_RUN_A_REVOKE_QUERY = "run_a_revoke_query";  //  运行A股股票撤销交易查询
    public static final String PY_API_RUN_A_DAILY_REFRESH = "run_a_daily_refresh";  //  收盘股票信息更新
    public static final String PY_API_RUN_A_CJCX = "run_a_cjcx";  //  收盘股票成交结果更新
    public static final String PY_API_RUN_A_CHECK_SELL = "run_a_check_sell";  //  收盘股票卖出交易结果检查
    public static final String PY_API_RUN_A_CCTJ = "run_a_cctj";  //  调用qmt客户端查询股票账户持仓结果
    // 标记现在开始进行周线数据下载 true/false
    public static final String OPE_STOCK_CREATE_A_WEEKLY = "OPE_STOCK_CREATE_A_WEEKLY";


    // -----------H股指令 begin--------------
    public static final String PY_API_CREATE_H_WEEKLY = "create_h_weekly";
    public static final String PY_API_CREATE_H_EMA = "create_h_ema";   // 生成ema数据线
    public static final String PY_API_RUN_H_BUY_RULE = "run_h_buy_rule";  //  运行H股购买策略
    public static final String PY_API_RUN_H_UTURN_RULE = "run_h_uturn_rule";  //  运行H股回头草策略V1
    public static final String PY_API_RUN_H_UTURN_RULE_V2 = "run_h_uturn_rule_v2";  //  运行H股回头草策略V2

    // -----------U股指令 begin--------------
    public static final String PY_API_CREATE_U_WEEKLY = "create_u_weekly";
    public static final String PY_API_CREATE_U_EMA = "create_u_ema";   // 生成ema数据线
    public static final String PY_API_RUN_U_BUY_RULE = "run_u_buy_rule";  //  运行购买策略
    public static final String PY_API_RUN_U_UTURN_RULE = "run_u_uturn_rule";  //  运行回头草策略V1
    public static final String PY_API_RUN_U_UTURN_RULE_V2 = "run_u_uturn_rule_v2";  //  运行回头草策略V2

}
