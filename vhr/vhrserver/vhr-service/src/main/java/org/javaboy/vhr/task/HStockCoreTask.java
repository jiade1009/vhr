package org.javaboy.vhr.task;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.HStockWeeklyLineEmaResult;
import org.javaboy.vhr.model.HStockWeeklyLineResult;
import org.javaboy.vhr.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description : 港股定时任务设定
 * @author      : sam
 * @datetime    : 2022-11-13 10:19:20
 * @version:    : 1.0
 */

@Component
//@PropertySource("classpath:/application.yml")
public class HStockCoreTask extends BaseStockTask{
    private static final Logger LOGGER = LoggerFactory.getLogger(AStockCoreTask.class);
    @Resource
    private HStockWeeklyLineResultService hStockWeeklyLineResultService;
    @Resource
    private HStockWeeklyLineEmaResultService hStockWeeklyLineEmaResultService;
    @Value("${task.cron.StockCore.weekly}")
    private String weekly;
    private String flag = "H";


    //-----------H股相关定时任务配置---------------
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.h_weekly}")
    public void hWeekly() {
        // 传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
        String[] params = new String[]{BaseConstants.PY_API_CREATE_H_WEEKLY, "1"};
        runPython(flag, params, "周线数据下载");
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.h_buyrule}")
    public void hBuyrule() {
        String tradeDate = getTodayTradeDate(flag);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始{}股【{}】的买入策略的运行..............", flag, tradeDate);
            List<HStockWeeklyLineResult> weeklyList = getStockWeeklyLineResults(tradeDate);
            if (weeklyList!=null && weeklyList.size()>0) {
                List<HStockWeeklyLineEmaResult> emaList = hStockWeeklyLineEmaResultService.getBeanlistByWeeklyId(weeklyList.get(0).getId());
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_H_BUY_RULE, String.valueOf(emaList.get(0).getId())});
            }
        } else {
            LOGGER.debug("今天不是{}股股票交易日，不运行买入策略命令", flag);
        }
    }

    /**
     * 回头草策略运行
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.h_uturnrule}")
    public void hUTurnRule() {
        String tradeDate = getTodayTradeDate(flag);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始{}股【{}】的回头草策略的运行..............", flag, tradeDate);
            List<HStockWeeklyLineResult> weeklyList = getStockWeeklyLineResults(tradeDate);
            if (weeklyList!=null && weeklyList.size()>0) {
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_H_UTURN_RULE_V2});
            }
        } else {
            LOGGER.debug("今天不是{}股股票交易日，不运行回头草策略命令", flag);
        }
    }

    private List<HStockWeeklyLineResult> getStockWeeklyLineResults(String tradeDate) {
        String searchDate = tradeDate.replace("-", "");
        boolean already = false;
        Integer time = 0;
        //判断是否已经生成今天的周线和ema数据，即自动生成，且ema结果为成功生成的周线
        List<HStockWeeklyLineResult> weeklyList = null;
        while (!already && time < 90) {
            weeklyList = hStockWeeklyLineResultService.getBeanListByPro(searchDate, 1, 1);
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
