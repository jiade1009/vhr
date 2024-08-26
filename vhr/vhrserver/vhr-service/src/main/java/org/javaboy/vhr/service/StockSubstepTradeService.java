package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockSubstepProfitMapper;
import org.javaboy.vhr.mapper.StockSubstepTradeMapper;
import org.javaboy.vhr.model.StockSubstepProfit;
import org.javaboy.vhr.model.StockSubstepTrade;
import org.javaboy.vhr.utils.SubstepUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName   : StockSubstepTradeService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 06日 08:33
 * @version:    : 1.0
 */

@Service
public class StockSubstepTradeService extends BaseService<StockSubstepTrade, Integer> {

    @Resource
    private StockSubstepTradeMapper stockSubstepTradeMapper;
    @Resource
    private StockSubstepProfitMapper stockSubstepProfitMapper;

    public List<StockSubstepTrade> getBeanlistByStepId(Integer hid) {
        return stockSubstepTradeMapper.getBeanlistByStepId(hid);
    }

    public int addBeans(List<StockSubstepTrade> list) {
        for (StockSubstepTrade trade: list) {
            String code = trade.getCode();
            StockSubstepProfit profit = stockSubstepProfitMapper.getRunningByCode(code);
            if (profit == null) {
                // 没有对应的记录，则新增
                profit = new StockSubstepProfit();
                profit.setCode(code);
                profit.setName(trade.getName());
                SubstepUtils.updatePrice(profit, trade);
                stockSubstepProfitMapper.insert(profit);
            } else {
                SubstepUtils.updatePrice(profit, trade);
                profit.setTimeUpdate(new Date());
                stockSubstepProfitMapper.updateByPrimaryKey(profit);
            }
            trade.setTimeCreate(new Date());
            trade.setTimeUpdate(trade.getTimeCreate());
            trade.setSubstepProfitId(profit.getId());
            stockSubstepTradeMapper.insert(trade);
        }
        return 1;
    }
}
