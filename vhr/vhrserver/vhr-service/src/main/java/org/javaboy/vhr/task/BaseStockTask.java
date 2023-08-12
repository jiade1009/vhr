package org.javaboy.vhr.task;

import org.javaboy.vhr.model.*;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.model.util.MessageType;
import org.javaboy.vhr.model.util.SendType;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.*;
import org.javaboy.vhr.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description : 股票定时任务基类
 * @author      : sam
 * @datetime    : 2022-11-13 10:19:20
 * @version:    : 1.0
 */
@Component
public class BaseStockTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseStockTask.class);
    private static Pattern sign_p1 = Pattern.compile("ema18突破ema25股票：【(.*?)】");  //白色信号正则
    private static Pattern sign_p2 = Pattern.compile("满足购买策略股票：【(.*?)】");  //蓝色信号正则

    private static Pattern ureturn_sign_p1 = Pattern.compile("【回头草：(.*?)】");  //回头草信号正则
    private static Pattern ureturn_sign_p2 = Pattern.compile("【加强回头草：(.*?)】");  //加强回头草信号正则

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

    @Resource
    private StockWeeklyLineEmaResultService stockWeeklyLineEmaResultService;
    @Resource
    private HStockWeeklyLineEmaResultService hStockWeeklyLineEmaResultService;
    @Resource
    private UStockWeeklyLineEmaResultService uStockWeeklyLineEmaResultService;

    @Resource
    private StockReturnResultService stockReturnResultService;
    @Resource
    private HStockReturnResultService hStockReturnResultService;
    @Resource
    private UStockReturnResultService uStockReturnResultService;

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
                // 针对美股，由于它收盘时间是北京时间的第二天凌晨4点（或冬令5点），但是目前我们运行的周线，买入策略，回头草策略
                // 均是在第二天晚上22：00开始，我们判断其交易时间需要相减24小时
                Date usaTradeDate = DateUtils.addHour(nowDate, -24);
                String usaNow = DateUtils.formatDate(usaTradeDate, DateUtils.yyyy_MM_dd);
                UStockTradeDate uBean = uStockTradeDateService.getByDate(usaNow);
                tradeDate = uBean==null?null:uBean.getTradeDate();
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
        if (tradeDate!=null) { //当天为股票交易日
            tradeDate = tradeDate.replace("-", "");
            LOGGER.info(".............开始{}股【{}】的巡检查询..............", flag, tradeDate);
            LinkedHashMap<CommandType, Boolean> params = new LinkedHashMap();
            for (CommandType cmd: cmdArray) {
                params.put(cmd, getExecuteResult(cmd, tradeDate));
            }
            stockMessageLogService.insertInspectionMessages(params, tradeDate, flag);
        } else {
            LOGGER.debug("今天不是{}股股票交易日，不运行巡检操作", flag);
        }
    }

    protected void checkSignalMail(String flag) {
        String tradeDate = getTodayTradeDate(flag);
        if (tradeDate!=null) { //当天为股票交易日
            LOGGER.info(".............开始{}股【{}】的信号发现邮件检查..............", flag, tradeDate);
            tradeDate = tradeDate.replace("-", "");
            List messageList = stockMessageLogService.getMessageLogByDateResearch(tradeDate, MessageType.SIGN,
                    SendType.EMAIL, flag);
            if (messageList.size() == 0) { //未成功发送信号发现邮件
                String white_stocks = "";
                String buy_stocks = "";
                String desc = getRunStatusDesc(tradeDate, flag);
                Matcher m = sign_p1.matcher(desc);  // 白色信号
                while (m.find()) {
                    white_stocks = m.group(1);
                }
                Matcher m2 = sign_p2.matcher(desc);  // 蓝色信号
                while (m2.find()) {
                    buy_stocks = m2.group(1);
                }
                stockMessageLogService.insertSignalMessages(white_stocks, buy_stocks, tradeDate, flag);
            }

            // 回头草信号
            messageList = stockMessageLogService.getMessageLogByDateResearch(tradeDate, MessageType.URETURNSIGN,
                    SendType.EMAIL, flag);
            if (messageList.size() == 0) { //未成功发送回头草发现邮件
                List<String> uturn_stocks = new ArrayList<String>();
                List<String> enhance_uturn_stocks = new ArrayList<String>();

                if (flag.equals("A")) {
                    List<StockReturnResult> returnResultList = stockReturnResultService.getBeanlistByDateResearch(tradeDate);
                    if (returnResultList.size()>0) {
                        StockReturnResult returnResult = returnResultList.get(0);
                        String desc = returnResult.getResultDesc();
                        composeUturnStock(uturn_stocks, enhance_uturn_stocks, desc);
                    }
                } else if (flag.equals("H")) {
                    List<HStockReturnResult> returnResultList = hStockReturnResultService.getBeanlistByDateResearch(tradeDate);
                    if (returnResultList.size()>0) {
                        HStockReturnResult returnResult = returnResultList.get(0);
                        String desc = returnResult.getResultDesc();
                        composeUturnStock(uturn_stocks, enhance_uturn_stocks, desc);
                    }
                } else if (flag.equals("U")) {
                    List<UStockReturnResult> returnResultList = uStockReturnResultService.getBeanlistByDateResearch(tradeDate);
                    if (returnResultList.size()>0) {
                        UStockReturnResult returnResult = returnResultList.get(0);
                        String desc = returnResult.getResultDesc();
                        composeUturnStock(uturn_stocks, enhance_uturn_stocks, desc);
                    }
                }

                if (uturn_stocks.size()>0 || enhance_uturn_stocks.size()>0) {
                    stockMessageLogService.insertUreturnSignalMessages(uturn_stocks, enhance_uturn_stocks, tradeDate, flag);
                }
            }
        } else {
            LOGGER.debug("今天不是{}股股票交易日，不运行信号发现邮件检查", flag);
        }
    }

    private void composeUturnStock(List<String> uturn_stocks, List<String> enhance_uturn_stocks, String desc) {
        Matcher m = ureturn_sign_p1.matcher(desc);
        while (m.find()) {
            String ureturn = m.group(1);
            if (!ureturn.equals("无")) {
                uturn_stocks.addAll(Arrays.asList(ureturn.split(",")));
            }
        }
        Matcher m2 = ureturn_sign_p2.matcher(desc);
        while (m2.find()) {
            String enhance_ureturn = m2.group(1);
            if (!enhance_ureturn.equals("无")) {
                enhance_uturn_stocks.addAll(Arrays.asList(enhance_ureturn.split(",")));
            }
        }
    }

    private String getRunStatusDesc(String tradeDate, String flag) {
        if (flag.equals("A")) {
            List<StockWeeklyLineEmaResult> list = stockWeeklyLineEmaResultService.getBeanlistByDateResearch(tradeDate);
            if (list.size()>0) {
                return list.get(0).getRunStatusDesc();
            } else {
                return "";
            }
        } else if (flag.equals("H")) {
            List<HStockWeeklyLineEmaResult> list = hStockWeeklyLineEmaResultService.getBeanlistByDateResearch(tradeDate);
            if (list.size()>0) {
                return list.get(0).getRunStatusDesc();
            } else {
                return "";
            }
        } else if (flag.equals("U")) {
            List<UStockWeeklyLineEmaResult> list = uStockWeeklyLineEmaResultService.getBeanlistByDateResearch(tradeDate);
            if (list.size()>0) {
                return list.get(0).getRunStatusDesc();
            } else {
                return "";
            }
        }
        return "";
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
