package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockMessageLogMapper;
import org.javaboy.vhr.model.MailConstants;
import org.javaboy.vhr.model.StockHoldTrade;
import org.javaboy.vhr.model.StockMessageConf;
import org.javaboy.vhr.model.StockMessageLog;
import org.javaboy.vhr.model.util.MessageType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
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
     * 批量新增股票白色信号和蓝色信号通知
     * @param white_stocks
     * @param buy_stocks
     */
    public void insertSignalMessages(String white_stocks, String buy_stocks) {
        white_stocks = StringUtils.hasLength(white_stocks)?white_stocks:"暂无数据";
        buy_stocks = StringUtils.hasLength(buy_stocks)?buy_stocks:"暂无数据";
        String content = "白色信号：" + white_stocks+"； 蓝色信号：" + buy_stocks;
        insertMessage(content, MessageType.SIGN.getIndex());
    }

    public void insertBuyHoldMessages(List<String> hold_trade_ids) {
        if (hold_trade_ids.size()>0) {
            StringBuilder sbd = new StringBuilder("");
            for (String id: hold_trade_ids) {
                StockHoldTrade trade = stockHoldTradeService.selectByPrimaryKey(Integer.valueOf(id));
                sbd.append("[").append(trade.getCode()).append("]，买入价：").append(String.format("%.2f",trade.getPrice()));
                sbd.append("，股票数：").append(trade.getAmount()).append(" ## ");
            }
            insertMessage(sbd.toString(), MessageType.BUY.getIndex());
        }
    }

    public void insertBuyHoldResultMessages(List<String> hold_trade_ids) {
        if (hold_trade_ids.size()>0) {
            StringBuilder sbd = new StringBuilder("");
            for (String id: hold_trade_ids) {
                StockHoldTrade trade = stockHoldTradeService.selectByPrimaryKey(Integer.valueOf(id));
                //委托状态、委托信息、任务状态、任务状态信息
                sbd.append("[").append(trade.getCode()).append("]，委托状态：").append(trade.getTaskstatusNote());
                sbd.append("，股票数：").append(trade.getAmount()).append(" ");
            }
            insertMessage(sbd.toString(), MessageType.BUY.getIndex());
        }
    }

    public void insertSellHoldMessages(Integer holdTradeId) {
        StringBuilder sbd = new StringBuilder("");
        StockHoldTrade trade = stockHoldTradeService.selectByPrimaryKey(holdTradeId);
        sbd.append("[").append(trade.getCode()).append("]，卖出价：").append(String.format("%.2f",trade.getPrice()));
        sbd.append("，股票数：").append(trade.getAmount());
        insertMessage(sbd.toString(), MessageType.SELL.getIndex());
    }

    /**
     * 读取所有待接收信息的消息发送配置
     * @param content
     */
    public void insertMessage(String content, Integer messageType) {
        List<StockMessageConf> confList = stockMessageConfService.getListByStatus(true);
        for (StockMessageConf conf : confList) {
            String send_type = conf.getSendType();
            String[] send_type_arr = send_type.split("");
            for (int i = 0; i < send_type_arr.length; i++) {
                if (send_type_arr[i].equals("1")) {
                    if (conf.getMessageType().substring(0, 1).equals("1")) {
                        //接收信号
                        String msgId = UUID.randomUUID().toString();
                        StockMessageLog log = new StockMessageLog();
                        log.setMsgid(msgId);
                        log.setSendType(i);
                        log.setMessageType(messageType);
                        log.setEmpid(conf.getEmpid());
                        log.setExchange(MailConstants.STOCK_EXCHANGE_NAME);
                        log.setRoutekey(MailConstants.STOCK_ROUTING_KEY_NAME);
                        log.setTimeCreate(new Date());
                        log.setTimeTry(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
                        log.setContent(content);
                        stockMessageLogMapper.insert(log);
                    }
                }
            }
        }
    }
}
