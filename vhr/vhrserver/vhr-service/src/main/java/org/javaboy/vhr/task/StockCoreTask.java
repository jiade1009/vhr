package org.javaboy.vhr.task;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.*;
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
import java.util.LinkedHashMap;
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
    private DatabaseTypeService databaseTypeService;
    @Resource
    private StockATradeDateService stockATradeDateService;
    @Resource
    private HStockTradeDateService hStockTradeDateService;
    @Resource
    private StockWeeklyLineResultService stockWeeklyLineResultService;
    @Resource
    private HStockWeeklyLineResultService hStockWeeklyLineResultService;
    @Resource
    private StockWeeklyLineEmaResultService stockWeeklyLineEmaResultService;
    @Resource
    private HStockWeeklyLineEmaResultService hStockWeeklyLineEmaResultService;
    @Resource
    private StockExecuteResultService stockExecuteResultService;
    @Resource
    private StockMessageLogService stockMessageLogService;
    @Value("${task.cron.StockCore.weekly}")
    private String weekly;


    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.weekly}")
    public void weekly() {
        String tradeDate = getTodayTradeDate("A");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的周线数据下载..............", tradeDate);
            // 传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
            execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_A_WEEKLY, "1"});

            //记录现在开始进行周线数据下载
            ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
            ops1.set(BaseConstants.OPE_STOCK_CREATE_A_WEEKLY, "true");
        } else {
            LOGGER.debug("今天不是A股股票交易日，不生成周线数据");
        }
    }

    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.h_weekly}")
    public void hWeekly() {
        String tradeDate = getTodayTradeDate("H");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始H股【{}】的周线数据下载..............", tradeDate);
            // 传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
            execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_H_WEEKLY, "1"});
        } else {
            LOGGER.debug("今天不是H股股票交易日，不生成周线数据");
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
        String tradeDate = getTodayTradeDate("A");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的买入策略的运行..............", tradeDate);
            String searchDate = tradeDate.replace("-", "");
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
    @Scheduled(cron = "${task.cron.StockCore.h_buyrule}")
    public void hBuyrule() {
        String tradeDate = getTodayTradeDate("H");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始H股【{}】的买入策略的运行..............", tradeDate);
            String searchDate = tradeDate.replace("-", "");
            boolean already = false;
            Integer time = 0;
            //判断是否已经生成今天的周线和ema数据，即自动生成，且ema结果为成功生成的周线
            List<HStockWeeklyLineResult> weeklyList = null;
            while (!already && time<90) {
                weeklyList = hStockWeeklyLineResultService.getBeanListByPro(searchDate, 1, 1);
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
                List<HStockWeeklyLineEmaResult> emaList = hStockWeeklyLineEmaResultService.getBeanlistByWeeklyId(weeklyList.get(0).getId());
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_H_BUY_RULE, String.valueOf(emaList.get(0).getId())});
            }
        } else {
            LOGGER.debug("今天不是H股股票交易日，不运行买入策略命令");
        }
    }

    /**
     * 回头草策略运行
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.uturnrule}")
    public void uTurnRule() {
        String tradeDate = getTodayTradeDate("A");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的回头草策略的运行..............", tradeDate);
            String searchDate = tradeDate.replace("-", "");
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
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_UTURN_RULE});
            }
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行回头草策略命令");
        }
    }

    /**
     * 回头草策略运行
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.h_uturnrule}")
    public void hUTurnRule() {
        String tradeDate = getTodayTradeDate("H");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始H股【{}】的回头草策略的运行..............", tradeDate);
            String searchDate = tradeDate.replace("-", "");
            boolean already = false;
            Integer time = 0;
            //判断是否已经生成今天的周线和ema数据，即自动生成，且ema结果为成功生成的周线
            List<HStockWeeklyLineResult> weeklyList = null;
            while (!already && time<90) {
                weeklyList = hStockWeeklyLineResultService.getBeanListByPro(searchDate, 1, 1);
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
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_H_UTURN_RULE});
            }
        } else {
            LOGGER.debug("今天不是H股股票交易日，不运行回头草策略命令");
        }
    }

    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.buy}")
    public void buy() {
        DatabaseType vo = databaseTypeService.getByCode("stk_auto_order");
        if (vo.getValue().equals("1")) { //开启自动下单
            String tradeDate = getTodayTradeDate("A");
            if (tradeDate!=null) { //当天为股票交易日
                LOGGER.info(".............开始A股【{}】的买入股票的运行..............", tradeDate);
//            传递stock_hold_id 如果为0，则查询所有的股票池数据
                execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY, "0"});
            } else {
                LOGGER.debug("今天不是A股股票交易日，不运行买入股票命令");
            }
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
        String tradeDate = getTodayTradeDate("A");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的股票交易结果查询的运行..............", tradeDate);
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_ORDER_QUERY});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行股票交易结果查询命令");
        }
    }

//    暂停执行A股代码的更新操作
    @Async("stockCoreAsync")
//    @Scheduled(cron = "${task.cron.StockCore.stockCodeUpdate}")
    public void stockCodeUpdate() {
        LOGGER.info(".............开始A股股票代码的更新..............");
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

    private String getTodayTradeDate(String flag) {
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd);
        if (flag.equals("A")) {
            StockATradeDate bean = stockATradeDateService.getByDate(now);
            if (bean==null) {
                return null;
            } else {
                return bean.getTradeDate();
            }
        } else {
            HStockTradeDate bean = hStockTradeDateService.getByDate(now);
            if (bean==null) {
                return null;
            } else {
                return bean.getTradeDate();
            }
        }

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
        String tradeDate = getTodayTradeDate("A");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的卖出股票的运行..............", tradeDate);
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
        String tradeDate = getTodayTradeDate("A");
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的股票撤销交易查询的运行..............", tradeDate);
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
        String tradeDate = getTodayTradeDate("A");
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        if (tradeDate!=null) { //当天为股票交易日
            boolean already = false;
            Integer time = 0;
            while (!already && time<59) {
                //今天的ema数据是否已经生成
                List<StockExecuteResult> list = stockExecuteResultService.getBeanlistByCommand(CommandType.WEEKLY_EMA.name(), now);
                if (list.size()>0) {
                    LOGGER.info(".............开始A股【{}】的收盘股票信息更新的运行..............", tradeDate);
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
     * 查询今日的股票成交结果信息。（买入和卖出成交记录）
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.cjcx}")
    public void cjcx() {
        String tradeDate = getTodayTradeDate("A");
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的股票成交结果信息查询的运行..............", tradeDate);
            execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_CJCX, now});
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行股票成交结果信息查询命令");
        }
    }

    /**
     * 针对每天的定时任务的运行结果进行巡检
     * 每日的21：00点开始巡检
     * 巡检项目：周线数据、周线ema数据、买入策略、行情信息查询、成交记录查询
     */
    @Async("stockCoreAsync")
    @Scheduled(cron = "${task.cron.StockCore.inspection}")
    public void inspection() {
        inspectionA();
        inspectionH();
    }

    /**
     * A股巡检
     */
    public void inspectionA() {
        String tradeDate = getTodayTradeDate("A");
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始A股【{}】的巡检查询..............", tradeDate);
            LinkedHashMap<CommandType, Boolean> params = new LinkedHashMap();
            //今天的weekly数据是否已经生成
            params.put(CommandType.WEEKLY, getExecuteResult(CommandType.WEEKLY, now));
            //今天的ema数据是否已经生成
            params.put(CommandType.WEEKLY_EMA, getExecuteResult(CommandType.WEEKLY_EMA, now));
            //今天的买入策略的运行结果数据是否已经生成
            params.put(CommandType.BUY_RULE, getExecuteResult(CommandType.BUY_RULE, now));
            //今天的回头草策略的运行结果数据是否已经生成
            params.put(CommandType.U_RETURN_RUN, getExecuteResult(CommandType.U_RETURN_RUN, now));
            //今天的收盘信息数据是否已经更新
            params.put(CommandType.DAILY_REFRESH, getExecuteResult(CommandType.DAILY_REFRESH, now));
            //今天的成交记录是否已经更新
            params.put(CommandType.CJCX, getExecuteResult(CommandType.CJCX, now));
            //今天的盈亏计算是否已经更新
            params.put(CommandType.PROFIT, getExecuteResult(CommandType.PROFIT, now));
            //今天的总盈亏计算是否已经更新
            params.put(CommandType.PROFIT_TOTAL, getExecuteResult(CommandType.PROFIT_TOTAL, now));
            stockMessageLogService.insertInspectionMessages(params, "A");
        } else {
            LOGGER.debug("今天不是A股股票交易日，不运行巡检操作");
        }
    }

    /**
     * H股巡检
     */
    public void inspectionH() {
        String tradeDate = getTodayTradeDate("H");
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始H股【{}】的巡检查询..............", tradeDate);
            LinkedHashMap<CommandType, Boolean> params = new LinkedHashMap();
            //今天的weekly数据是否已经生成
            params.put(CommandType.H_WEEKLY, getExecuteResult(CommandType.H_WEEKLY, now));
            //今天的ema数据是否已经生成
            params.put(CommandType.H_WEEKLY_EMA, getExecuteResult(CommandType.H_WEEKLY_EMA, now));
            //今天的买入策略的运行结果数据是否已经生成
            params.put(CommandType.H_BUY_RULE, getExecuteResult(CommandType.H_BUY_RULE, now));
            //今天的回头草策略的运行结果数据是否已经生成
            params.put(CommandType.H_U_RETURN_RUN, getExecuteResult(CommandType.H_U_RETURN_RUN, now));

            stockMessageLogService.insertInspectionMessages(params, "H");
        } else {
            LOGGER.debug("今天不是H股股票交易日，不运行巡检操作");
        }
    }

    private boolean getExecuteResult(CommandType commandType, String now) {
        //今天的成交记录是否已经更新
        List<StockExecuteResult> cjcx_list = stockExecuteResultService.getBeanlistByCommand(commandType.name(), now);
        if (cjcx_list.size()>0) {
            return true;
        } else {
            return false;
        }
    }
}
