package org.javaboy.vhr.task;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.StockATradeDate;
import org.javaboy.vhr.model.StockExecuteResult;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;
import org.javaboy.vhr.model.StockWeeklyLineResult;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockATradeDateService;
import org.javaboy.vhr.service.StockExecuteResultService;
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
 * @description : 1.定时刷新是否已经完成周线数据的下载
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
    @Resource
    private StockExecuteResultService stockExecuteResultService;
    @Value("${task.cron.StockCore.weekly}")
    private String weekly;


    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.weekly}")
    public void weekly() {
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
            while (!already && time<90) {
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
//            传递stock_hold_id 如果为0，则查询所有的股票池数据
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY, "0"});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行买入股票命令");
        }
    }

    /**
     * qmt客户端交易结果查询
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.orderQuery_0}")
    public void orderQuery_0() {
        orderQueryTask();
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.orderQuery_1}")
    public void orderQuery_1() {
        orderQueryTask();
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.orderQuery_2}")
    public void orderQuery_2() {
        orderQueryTask();
    }

    private void orderQueryTask() {
        StockATradeDate tradeDate = getTodayTradeDate();
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的股票交易结果查询的运行..............", tradeDate.getTradeDate());
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_ORDER_QUERY});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行股票交易结果查询命令");
        }
    }

    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.stockCodeUpdate}")
    public void stockCodeUpdate() {
        LOGGER.info(".............开始股票代码的更新..............");
        execPython.runPython(new String[]{BaseConstants.PY_API_LOAD_A_STOCK});
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

    /**
     * qmt客户端卖出股票交易请求
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.sell_0}")
    public void sell_0() {
        sellTask();
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.sell_1}")
    public void sell_1() {
        sellTask();
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.sell_2}")
    public void sell_2() {
        sellTask();
    }

    private void sellTask() {
        StockATradeDate tradeDate = getTodayTradeDate();
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的卖出股票的运行..............", tradeDate.getTradeDate());
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_SELL});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行卖出股票命令");
        }
    }

    /**
     * qmt客户端股票撤销交易请求
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.revokeQuery_0}")
    public void revokeQuery_0() {
        revokeQueryTask();
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.revokeQuery_1}")
    public void revokeQuery_1() {
        revokeQueryTask();
    }
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.revokeQuery_2}")
    public void revokeQuery_2() {
        revokeQueryTask();
    }

    private void revokeQueryTask() {
        StockATradeDate tradeDate = getTodayTradeDate();
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的股票撤销交易查询的运行..............", tradeDate.getTradeDate());
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_REVOKE_QUERY});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行股票撤销交易查询命令");
        }
    }
    /**
     * 股票最高价相关信息更新（收盘股票信息更新）
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.dailyRefresh}")
    public void dailyRefresh() {
        StockATradeDate tradeDate = getTodayTradeDate();
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        if (tradeDate!=null) { //当天为股票交易日
            boolean already = false;
            Integer time = 0;
            while (!already && time<90) {
                //今天的ema数据是否已经生成
                List<StockExecuteResult> list = stockExecuteResultService.getBeanlistByCommand(CommandType.WEEKLY_EMA.name(), now);
                if (list.size()>0) {
                    LOGGER.info(".............开始【{}】的收盘股票信息更新的运行..............", tradeDate.getTradeDate());
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
            if (already) {
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_DAILY_REFRESH});
            }
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行买入策略命令");
        }
    }

    /**
     * 收盘查询今日的股票成交结果信息。（买入和卖出成交记录）
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.cjcx}")
    public void cjcx() {
        StockATradeDate tradeDate = getTodayTradeDate();
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始【{}】的股票成交结果信息查询的运行..............", tradeDate.getTradeDate());
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_CJCX, now});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行股票成交结果信息查询命令");
        }
    }
}
