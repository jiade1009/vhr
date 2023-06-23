package org.javaboy.vhr.task;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.UStockWeeklyLineEmaResult;
import org.javaboy.vhr.model.UStockWeeklyLineResult;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.service.UStockWeeklyLineEmaResultService;
import org.javaboy.vhr.service.UStockWeeklyLineResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description : 美股定时任务设定
 * 9:30开盘，16:00收盘，美股持续交易时段为美国东部时间周一至周五：9:30-16:00，同时允许在其他时段进行盘前盘后交易。
 * 对应的北京时间是：21:30到次日4:00，冬令时比夏令时晚一小时，对应的就是北京时间22:30到次日5:00。
 * 美国因为有夏令时间，因此夏天的交易时间与冬天相比会提前一小时。
 * 美国从每年3月第二个星期日到11月第一个星期六采用夏令时，这段时间其交易时间为北京时间晚21:30－次日凌晨4:00。
 * @author      : sam
 * @datetime    : 2022-11-13 10:19:20
 * @version:    : 1.0
 */

@Component
public class UStockCoreTask extends BaseStockTask{
    private static final Logger LOGGER = LoggerFactory.getLogger(AStockCoreTask.class);
    @Resource
    private UStockWeeklyLineResultService uStockWeeklyLineResultService;
    @Resource
    private UStockWeeklyLineEmaResultService uStockWeeklyLineEmaResultService;
    private String flag = "U";


    //-----------U股相关定时任务配置---------------
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.u_weekly}")
    public void weekly() {
        // 传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
        String[] params = new String[]{BaseConstants.PY_API_CREATE_U_WEEKLY, "1"};
        LOGGER.info("=====================");
        runPython(flag, params, "周线数据下载");
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.u_buyrule}")
    public void buyrule() {
        String tradeDate = getTodayTradeDate(flag);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始{}股【{}】的买入策略的运行..............", flag, tradeDate);
            List<UStockWeeklyLineResult> weeklyList = getStockWeeklyLineResults(tradeDate);
            if (weeklyList!=null && weeklyList.size()>0) {
                List<UStockWeeklyLineEmaResult> emaList = uStockWeeklyLineEmaResultService.getBeanlistByWeeklyId(weeklyList.get(0).getId());
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_U_BUY_RULE, String.valueOf(emaList.get(0).getId())});
            }
        } else {
            LOGGER.debug("今天不是{}股股票交易日，不运行买入策略命令", flag);
        }
    }

    /**
     * 回头草策略运行
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.u_uturnrule}")
    public void returnRule() {
        String tradeDate = getTodayTradeDate(flag);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始{}股【{}】的回头草策略的运行..............", flag, tradeDate);
            List<UStockWeeklyLineResult> weeklyList = getStockWeeklyLineResults(tradeDate);
            if (weeklyList!=null && weeklyList.size()>0) {
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_U_UTURN_RULE});
            }
        } else {
            LOGGER.debug("今天不是{}股股票交易日，不运行回头草策略命令", flag);
        }
    }

    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.u_inspection}")
    public void inspection() {
        // U股巡检
        CommandType[] cmdArray = new CommandType[]{CommandType.U_WEEKLY, CommandType.U_WEEKLY_EMA, CommandType.U_BUY_RULE,
                CommandType.U_U_RETURN_RUN};
        doInspection("U", cmdArray);
    }

    private List<UStockWeeklyLineResult> getStockWeeklyLineResults(String tradeDate) {
        String searchDate = tradeDate.replace("-", "");
        boolean already = false;
        Integer time = 0;
        //判断是否已经生成今天的周线和ema数据，即自动生成，且ema结果为成功生成的周线
        List<UStockWeeklyLineResult> weeklyList = null;
        while (!already && time < 90) {
            weeklyList = uStockWeeklyLineResultService.getBeanListByPro(searchDate, 1, 1);
            if (weeklyList != null && weeklyList.size() > 0) {
                already = true;
            } else {
                try {
                    time++;
                    LOGGER.info("time:{}", String.valueOf(time));
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return weeklyList;
    }

}
