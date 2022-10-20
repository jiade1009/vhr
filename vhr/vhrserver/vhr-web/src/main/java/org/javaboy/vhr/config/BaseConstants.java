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
    public static final String PY_API_CREATE_A_WEEKLY = "create_a_weekly";   // 生成weekly数据线
    public static final String PY_API_CREATE_A_EMA = "create_a_ema";   // 生成ema数据线
    public static final String PY_API_RUN_A_BUY_RULE = "run_a_buy_rule";  //  运行A股购买策略

}
