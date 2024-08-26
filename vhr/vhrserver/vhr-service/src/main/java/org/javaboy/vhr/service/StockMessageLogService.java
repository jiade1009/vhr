package org.javaboy.vhr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockMessageLogMapper;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.model.util.MessageType;
import org.javaboy.vhr.model.util.ProfitStage;
import org.javaboy.vhr.model.util.SendType;
import org.javaboy.vhr.utils.SubstepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author : sam
 * @ClassName : StockMessageLogService
 * @description : TODO
 * @datetime : 2022年 10月 26日 17:27
 * @version: : 1.0
 */

@Service
public class StockMessageLogService extends BaseService<StockMessageLog, Integer> {

    @Resource
    private StockMessageLogMapper stockMessageLogMapper;
    @Resource
    private StockMessageConfService stockMessageConfService;
    @Resource
    private StockHoldTradeService stockHoldTradeService;
    @Resource
    private StockAiOrderService stockAiOrderService;
    @Resource
    private StockSubstepProfitService stockSubstepProfitService;
    @Resource
    private StockSubstepTriggerService stockSubstepTriggerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StockMessageLogService.class);

    public int insert(StockMessageLog record) {
        return stockMessageLogMapper.insert(record);
    }

    public Integer updateStockMessageLogStatus(String msgId, Integer status) {
        return stockMessageLogMapper.updateStockMessageLogStatus(msgId, status);
    }

    /**
     * 获取指定状态的消息日志
     * @return
     */
    public List<StockMessageLog> getStockMessageLogByStatus(Integer status) {
        return stockMessageLogMapper.getStockMessageLogByStatus(status);
    }

    public Integer updateCount(String msgId, Date date) {
        return stockMessageLogMapper.updateCount(msgId,date);
    }

    /**
     * 查找指定日期是否生成对应消息类型的日志
     * @param dateResearch  指定日期 格式：yyyyMMdd
     * @param messageType 消息类型
     * @param sendType
     * @param flag 股票交易市场
     * @return
     */
    public List<StockMessageLog> getMessageLogByDateResearch(String dateResearch, MessageType messageType,
                                                             SendType sendType, String flag) {
        return stockMessageLogMapper.getMessageLogByDateResearch(dateResearch, messageType.getIndex(),
                sendType.getIndex(), flag);
    }
    /**
     * 批量新增股票白色信号和蓝色信号通知
     * @param white_stocks
     * @param buy_stocks
     */
    public void insertSignalMessages(String white_stocks, String buy_stocks, String date_research, String flag) {
        white_stocks = StringUtils.hasLength(white_stocks)?white_stocks:"暂无数据";
        buy_stocks = StringUtils.hasLength(buy_stocks)?buy_stocks:"暂无数据";
        String content = "白色信号：" + white_stocks+"； <br/>蓝色信号：" + buy_stocks;
        String title = flag + "股" + MessageType.SIGN.getName() +
                (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
        insertMessage(content, title, MessageType.SIGN.getIndex(), flag);
    }

    public void insertUreturnSignalMessages(List<String> ureturn_stocks, List<String> enhance_uturn_stocks,
                                            String date_research, String flag) {
        StringBuilder sbd = new StringBuilder("");
        if (ureturn_stocks != null && ureturn_stocks.size()>0) {
            String codes = String.join(",", ureturn_stocks);
            sbd.append("【回头草：").append(codes).append("】，");
        } else {
            sbd.append("【回头草：无】，");
        }
        if (enhance_uturn_stocks != null && enhance_uturn_stocks.size()>0) {
            String codes = String.join(",", enhance_uturn_stocks);
            sbd.append("【加强回头草：").append(codes).append("】，");
        }else {
            sbd.append("【加强回头草：无】");
        }
        String title = flag + "股" + MessageType.URETURNSIGN.getName() +
                (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
        insertMessage(sbd.toString(), title, MessageType.URETURNSIGN.getIndex(), flag);
    }

    public void insertBuyHoldMessages(List<String> hold_trade_ids, String date_research, String flag) {
        if (hold_trade_ids.size()>0) {
            StringBuilder sbd = new StringBuilder("");
            for (String id: hold_trade_ids) {
                StockHoldTrade trade = stockHoldTradeService.selectByPrimaryKey(Integer.valueOf(id));
                sbd.append("[").append(trade.getCode()).append("]，买入价：").append(String.format("%.2f",trade.getPrice()));
                sbd.append("，股票数：").append(trade.getAmount()).append(" <br/> ");
            }
            String title = flag + "股" + MessageType.BUY.getName() +
                    (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
            insertMessage(sbd.toString(), title, MessageType.BUY.getIndex(), flag);
        }
    }

    public void insertBuyHoldResultMessages(List<String> hold_trade_ids, String date_research, String flag) {
        if (hold_trade_ids.size()>0) {
            StringBuilder sbdBuy = new StringBuilder("");
            StringBuilder sellBuy = new StringBuilder("");
            for (String id: hold_trade_ids) {
                StockHoldTrade trade = stockHoldTradeService.selectByPrimaryKey(Integer.valueOf(id));
                String price = getPriceInfo(trade.getPriceType());
                if (trade.getTradeType().equals(0)) {
                    //买入交易
                    //委托状态、委托信息、任务状态、任务状态信息
                    sbdBuy.append("[").append(trade.getCode()).append("]，买入价格：").append(price);
                    sbdBuy.append("，股票数：").append(trade.getAmount()).append("，委托结果：")
                            .append(trade.getTaskstatusNote());
                    if (trade.getTaskstatus()==10 || trade.getTaskstatus()==12) {
                        sbdBuy.append("，原因：").append(trade.getTaskmsg());
                    }
                    sbdBuy.append(" <br/>");
                } else if(trade.getTradeType().equals(1)) {
                    //卖出交易
                    sellBuy.append("[").append(trade.getCode()).append("]，卖出价格：").append(price);
                    sellBuy.append("，股票数：").append(trade.getAmount()).append("，委托结果：")
                            .append(trade.getTaskstatusNote());
                    if (trade.getTaskstatus()==10 || trade.getTaskstatus()==12) {
                        sellBuy.append("，原因：").append(trade.getTaskmsg());
                    }
                    sellBuy.append(" <br/>");
                }
            }
            if (sbdBuy.length()>0) {
                String title = flag + "股" + MessageType.BUY.getName() +
                        (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
                insertMessage(sbdBuy.toString(), title, MessageType.BUY.getIndex(), flag);
            }
            if (sellBuy.length()>0) {
                String title = flag + "股" + MessageType.SELL.getName() +
                        (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
                insertMessage(sellBuy.toString(), title, MessageType.SELL.getIndex(), flag);
            }
        }
    }
    private String getPriceInfo(String flag) {
        if (flag.equals("1")) {
            return "最新价";
        } else if (flag.substring(0, 1).equals("B")) {
            return "买" + flag.substring(1) + "价";
        } else if (flag.substring(0, 1).equals("S")) {
            return "卖" + flag.substring(1) + "价";
        } else {
            return flag;
        }
    }

    public void insertSellHoldMessages(Integer holdTradeId, String date_research, String flag) {
        StringBuilder sbd = new StringBuilder("");
        StockHoldTrade trade = stockHoldTradeService.selectByPrimaryKey(holdTradeId);
        sbd.append("[").append(trade.getCode()).append("]，卖出价：").append(String.format("%.2f",trade.getPrice()));
        sbd.append("，股票数：").append(trade.getAmount());
        String title = flag + "股" + MessageType.SELL.getName() +
                (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
        insertMessage(sbd.toString(), title, MessageType.SELL.getIndex(), flag);
    }

    /**
     * 插入巡检结果信息
     */
    public void insertInspectionMessages(LinkedHashMap<CommandType, Boolean> params, String tradeDate, String flag) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json_str = mapper.writeValueAsString(params);
            String title = flag + "股" + MessageType.INSPECTION.getName() + "-" + tradeDate ;
            insertMessage(json_str, title, MessageType.INSPECTION.getIndex(), flag);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void insertAiOrderMessages(List<String> ai_order_id_list, String date_research, String flag) {
        if (ai_order_id_list.size()>0) {
            StringBuilder sbd = new StringBuilder("");
            for (String id: ai_order_id_list) {
                StockAiOrder order = stockAiOrderService.selectByPrimaryKey(Integer.valueOf(id));
                sbd.append("[").append(order.getName()).append("(").append(order.getCode()).
                        append(")，触发条件：").append(order.getTriggerCondition());
                sbd.append("，委托价格：").append(order.getPriceEntrust()).
                        append("，委托数量：").append(order.getAmountEntrust()).
                        append("。触发结果：").append(order.getNote()).
                        append("。 ]<br/> ");
            }
            String title = flag + "股" + MessageType.AI_ORDER.getName() +
                    (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
            insertMessage(sbd.toString(), title, MessageType.AI_ORDER.getIndex(), flag);
        }
    }

    /**
     * 发送分级止盈触发服务通知
     * @param substep_list 列表，元素数据结构为： [分级止盈id， 止盈或止损标识（1表示止盈，-1表示止损），触发第几个止盈阶段]
     * @param date_research
     * @param flag
     */
    @Transactional
    public void insertSubstepMessages(List<List<String>> substep_list, String date_research, String flag) {
        if (substep_list.size()>0) {
            StringBuilder sbd = new StringBuilder("");
            for (List<String> item: substep_list) {
                StockSubstepProfit vo = stockSubstepProfitService.selectByPrimaryKey(Integer.valueOf(item.get(0)));

                StockSubstepTrigger trigger = new StockSubstepTrigger();
                trigger.setCode(vo.getCode());
                trigger.setDateTrigger(date_research);
                trigger.setSubstepProfitId(vo.getId());
                if (item.get(1).equals("-1")) { // 止损清仓处理
                    sbd.append("[").append(vo.getName()).append("(").append(vo.getCode()).
                            append(")：触碰止损价格，请进行清仓处理");
                    sbd.append("，止损价格：").append(vo.getPriceStopLoss()).
                            append("，卖出数量：").append(vo.getAmountAble()).
                            append("。 ]<br/> ");

                    trigger.setStageTrigger(-1);
                } else { // 分级止盈
//                    Integer stage = vo.getProfitStage();
//                    Integer nextStage = stage + 1;
                    Integer triggerStage = Integer.valueOf(item.get(2));
                    List<String> profitInfo = SubstepUtils.getProfitInfo(vo, triggerStage);
                    sbd.append("[").append(vo.getName()).append("(").append(vo.getCode()).
                            append(")：触碰" + ProfitStage.getName(triggerStage)+"阶段止盈价格，请进行止盈操作");
                    sbd.append("，止盈价格：").append(profitInfo.get(0)).
                            append("，卖出数量：").append(profitInfo.get(1)).
                            append("。 ]<br/> ");
                    trigger.setStageTrigger(triggerStage);
                }
                stockSubstepTriggerService.insert(trigger);
            }
            String title = flag + "股" + MessageType.SUBSTEP.getName() +
                    (StringUtils.hasLength(date_research) ? ("-" + date_research):"");
            insertMessage(sbd.toString(), title, MessageType.SUBSTEP.getIndex(), flag);
        }
    }

    /**
     * 读取所有待接收信息的消息发送配置
     * @param content
     */
    public void insertMessage(String content, String title, Integer messageType, String flag) {
        LOGGER.info("新增消息内容：{} - {} - {}", flag, MessageType.getName(messageType), content);
        List<StockMessageConf> confList = stockMessageConfService.getListByStatus(true);
        for (StockMessageConf conf : confList) {
            String send_type = conf.getSendType();
            String[] send_type_arr = send_type.split("");
            for (int i = 0; i < send_type_arr.length; i++) {
                if (send_type_arr[i].equals("1")) {
                    String tag = String.valueOf(conf.getMessageType().charAt(messageType));
                    if (tag.equals("1")) {
                        //接收信号
                        String msgId = UUID.randomUUID().toString();
                        StockMessageLog log = new StockMessageLog();
                        log.setMsgid(msgId);
                        log.setSendType(i);
                        log.setFlag(flag);
                        log.setMessageType(messageType);
                        log.setEmpid(conf.getEmpid());
                        log.setExchange(MailConstants.STOCK_EXCHANGE_NAME);
                        log.setRoutekey(MailConstants.STOCK_ROUTING_KEY_NAME);
                        log.setTimeCreate(new Date());
                        log.setTimeTry(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
                        log.setContent(content);
                        log.setTitle(title);
                        stockMessageLogMapper.insert(log);
                    }
                }
            }
        }
    }
}
