package org.javaboy.vhr.task;

import org.javaboy.vhr.model.util.CommandType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description : 用于定义股票统一的定时任务
 * @author      : sam
 * @datetime    : 2022-11-13 10:19:20
 * @version:    : 1.0
 */

@Component
public class StockCommonTask extends BaseStockTask{
    /**
     * 针对每天的定时任务的运行结果进行巡检
     * 每日的21：00点开始巡检
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.inspection}")
    public void inspection() {
        // A股巡检
        CommandType[] cmdArray = new CommandType[]{CommandType.WEEKLY, CommandType.WEEKLY_EMA, CommandType.BUY_RULE,
                CommandType.U_RETURN_RUN, CommandType.DAILY_REFRESH, CommandType.CJCX, CommandType.DIVIDEND,
                CommandType.PROFIT, CommandType.PROFIT_TOTAL, CommandType.CHECK_SELL};
        doInspection("A", cmdArray);
        // H股巡检
        cmdArray = new CommandType[]{CommandType.H_WEEKLY, CommandType.H_WEEKLY_EMA, CommandType.H_BUY_RULE,
                CommandType.H_U_RETURN_RUN};
        doInspection("H", cmdArray);

        // 增加对信号发现邮件是否漏发的检查
        checkSignalMail("A");
        checkSignalMail("H");
    }
}
