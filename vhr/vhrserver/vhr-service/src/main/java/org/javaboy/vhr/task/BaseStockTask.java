package org.javaboy.vhr.task;

import org.javaboy.vhr.model.HStockTradeDate;
import org.javaboy.vhr.model.StockATradeDate;
import org.javaboy.vhr.model.StockExecuteResult;
import org.javaboy.vhr.model.UStockTradeDate;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.*;
import org.javaboy.vhr.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @description : 股票定时任务基类
 * @author      : sam
 * @datetime    : 2022-11-13 10:19:20
 * @version:    : 1.0
 */

public class BaseStockTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseStockTask.class);
    @Resource
    protected StringRedisTemplate stringRedisTemplate;
    @Resource
    protected ExecPython execPython;
    @Resource
    protected DatabaseTypeService databaseTypeService;
    @Resource
    private StockATradeDateService stockATradeDateService;
    @Resource
    private HStockTradeDateService hStockTradeDateService;
    @Resource
    private UStockTradeDateService uStockTradeDateService;
    @Resource
    private StockExecuteResultService stockExecuteResultService;
    @Resource
    protected StockMessageLogService stockMessageLogService;
    @Value("${task.cron.StockCore.weekly}")
    protected String weekly;

    /**
     * 调用执行对应交易所的脚本命令
     * @param flag 交易所标识符
     * @param cmdParams 脚本命令和脚本参数
     * @param desc 对应脚本的描述
     */
    protected void runPython(String flag, String[] cmdParams, String desc) {
        String tradeDate = getTodayTradeDate(flag);
        if (tradeDate!=null) {
            LOGGER.info(".............开始{}股【{}】的{}..............", flag, tradeDate, desc);
            execPython.runPython(cmdParams);
        } else {
            String now = DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd);
            LOGGER.debug("{}不是{}股股票交易日，不运行{}", now, flag, desc);
        }
    }
    /**
     * 判断今天是否是对应交易所的交易日
     * @param flag
     * @return
     */
    protected String getTodayTradeDate(String flag) {
        Date nowDate = new Date();
        String now = DateUtils.formatDate(nowDate, DateUtils.yyyy_MM_dd);
        String tradeDate = null;
        switch (flag) {
            case "A":
                StockATradeDate aBean = stockATradeDateService.getByDate(now);
                tradeDate = aBean==null?null:aBean.getTradeDate();
                break;
            case "H":
                HStockTradeDate hBean = hStockTradeDateService.getByDate(now);
                tradeDate = hBean==null?null:hBean.getTradeDate();
                break;
            case "U":
                // 针对美股，由于它收盘时间是北京时间的第二天凌晨4点（或冬令5点），因此，我们判断其交易时间需要相减12小时
                Date usaTradeDate = DateUtils.addHour(nowDate, -12);
                String usaNow = DateUtils.formatDate(usaTradeDate, DateUtils.yyyy_MM_dd);
                UStockTradeDate uBean = uStockTradeDateService.getByDate(usaNow);
                System.out.println(usaNow);
                tradeDate = uBean==null?null:uBean.getTradeDate();
                System.out.println(tradeDate);
                break;
        }
        return tradeDate;
    }

    /**
     * 巡检对应交易所的定时任务的执行结果
     * @param flag 交易所标识符
     * @param cmdArray 需要巡检的定时任务数组
     */
    protected void doInspection(String flag, CommandType[] cmdArray) {
        String tradeDate = getTodayTradeDate(flag);
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始{}股【{}】的巡检查询..............", flag, tradeDate);
            LinkedHashMap<CommandType, Boolean> params = new LinkedHashMap();
            for (CommandType cmd: cmdArray) {
                params.put(cmd, getExecuteResult(cmd, now));
            }
            stockMessageLogService.insertInspectionMessages(params, flag);
        } else {
            LOGGER.debug("今天不是{}股股票交易日，不运行巡检操作", flag);
        }
    }

    /**
     * 判断指定日期是否有运行对应脚本命令
     * @param commandType
     * @param now
     * @return
     */
    private boolean getExecuteResult(CommandType commandType, String now) {
        List<StockExecuteResult> cjcx_list = stockExecuteResultService.getBeanlistByCommand(commandType.name(), now);
        if (cjcx_list.size()>0) {
            return true;
        } else {
            return false;
        }
    }
}
