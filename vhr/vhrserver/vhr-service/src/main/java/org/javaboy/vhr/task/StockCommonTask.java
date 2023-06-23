package org.javaboy.vhr.task;

import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description : 用于定义股票统一的定时任务
 * @author      : sam
 * @datetime    : 2022-11-13 10:19:20
 * @version:    : 1.0
 */

@Component
public class StockCommonTask extends BaseStockTask{
    private static final Logger LOGGER = LoggerFactory.getLogger(AStockCoreTask.class);
    @Resource
    private DatabaseTypeService databaseTypeService;
    @Resource
    private StockATradeDateService stockATradeDateService;
    @Resource
    private StockWeeklyLineResultService stockWeeklyLineResultService;
    @Resource
    private StockWeeklyLineEmaResultService stockWeeklyLineEmaResultService;
    @Resource
    private HStockTradeDateService hStockTradeDateService;
    @Resource
    private HStockWeeklyLineResultService hStockWeeklyLineResultService;
    @Resource
    private HStockWeeklyLineEmaResultService hStockWeeklyLineEmaResultService;
    @Resource
    private StockExecuteResultService stockExecuteResultService;
    @Resource
    private StockMessageLogService stockMessageLogService;
    @Value("${task.cron.StockCore.weekly}")
    private String weekly;
    private String flag = "H";

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
                CommandType.PROFIT, CommandType.PROFIT_TOTAL};
        doInspection("A", cmdArray);
        // H股巡检
        cmdArray = new CommandType[]{CommandType.H_WEEKLY, CommandType.H_WEEKLY_EMA, CommandType.H_BUY_RULE,
                CommandType.H_U_RETURN_RUN};
        doInspection("H", cmdArray);
        // U股巡检
        cmdArray = new CommandType[]{CommandType.U_WEEKLY, CommandType.U_WEEKLY_EMA, CommandType.U_BUY_RULE,
                CommandType.U_U_RETURN_RUN};
        doInspection("U", cmdArray);
    }
}
