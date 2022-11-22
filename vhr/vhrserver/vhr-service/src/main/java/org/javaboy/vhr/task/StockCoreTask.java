package org.javaboy.vhr.task;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.StockATradeDate;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;
import org.javaboy.vhr.model.StockWeeklyLineResult;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockATradeDateService;
import org.javaboy.vhr.service.StockWeeklyLineEmaResultService;
import org.javaboy.vhr.service.StockWeeklyLineResultService;
import org.javaboy.vhr.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description : TODO 1.定时刷新是否已经完成周线数据的下载
 * @author      : sam
 * @datetime    : 2022-11-13 10:19:20
 * @version:    : 1.0
 */

@Component
//@PropertySource("classpath:/application.yml")
public class StockCoreTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockCoreTask.class);
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ExecPython execPython;
    @Resource
    private StockATradeDateService stockATradeDateService;
    @Resource
    private StockWeeklyLineResultService stockWeeklyLineResultService;
    @Resource
    private StockWeeklyLineEmaResultService stockWeeklyLineEmaResultService;
    @Value("${task.cron.StockCore.weekly}")
    private String weekly;


    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.weekly}")
    public void weekly() {
        System.out.println(weekly);
        StockATradeDate tradeDate = getTodayTradeDate();
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的周线数据下载..............", tradeDate.getTradeDate());
            // 传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
            execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_A_WEEKLY, "1"});

            //记录现在开始进行周线数据下载
            ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
            ops1.set(BaseConstants.OPE_STOCK_CREATE_A_WEEKLY, "true");
        } else {
            LOGGER.debug("今天不是A股股票交易日，不生成周线数据");
        }
    }

    /**
     * 每个交易日16:00开始自动运行买入策略，选择合适的股票
     * 1.判断是否是交易日，如果不是，则结束当日的运行
     * 2.如果是交易日，则判断今天的周线和ema数据是否已经自动生成，如果未生成，则休眠1分钟后，再继续判断是否生成，如果进行90次判断
     * 即90分钟之后，仍未生成，则结束程序
     * 3.如果生成，根据weeklyid，获取对应的ema数据，发起买入策略的运行
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.buyrule}")
    public void buyrule() {
        StockATradeDate tradeDate = getTodayTradeDate();
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的买入策略的运行..............", tradeDate.getTradeDate());
            String searchDate = tradeDate.getTradeDate().replace("-", "");
            boolean already = false;
            Integer time = 0;
            //判断是否已经生成今天的周线和ema数据，即自动生成，且ema结果为成功生成的周线
            List<StockWeeklyLineResult> weeklyList = null;
            while (!already && time<2) {
                weeklyList = stockWeeklyLineResultService.getBeanListByPro(searchDate, 1, 1);
                if (weeklyList != null && weeklyList.size()>0) {
                    already = true;
                } else {
                    try {
                        time ++;
                        LOGGER.info("time:{}", String.valueOf(time));
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (weeklyList!=null && weeklyList.size()>0) {
                List<StockWeeklyLineEmaResult> emaList = stockWeeklyLineEmaResultService.getBeanlistByWeeklyId(weeklyList.get(0).getId());
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY_RULE, String.valueOf(emaList.get(0).getId())});
            }
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行买入策略命令");
        }
    }

    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.buy}")
    public void buy() {
        StockATradeDate tradeDate = getTodayTradeDate();
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的买入股票的运行..............", tradeDate.getTradeDate());
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行买入股票命令");
        }
    }

    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.tradeResult}")
    public void tradeResult() {
        StockATradeDate tradeDate = getTodayTradeDate();
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的股票交易结果查询的运行..............", tradeDate.getTradeDate());
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY_QUERY});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行股票交易结果查询命令");
        }
    }

    /**
     * 定时刷新股票各种操作的状态
     */
    public void freshStatus() {
        //定时更新查看是否已经完成周线数据的下载

        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set(BaseConstants.OPE_STOCK_CREATE_A_WEEKLY, "false");
    }

    private StockATradeDate getTodayTradeDate() {
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd);
        return stockATradeDateService.getByDate(now);
    }
}
