package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtHoldTradeMapper;
import org.javaboy.vhr.model.StockQtHoldTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtHoldTradeService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 25日 22:54
 * @version:    : 1.0
 */

@Service
public class StockQtHoldTradeService extends BaseService<StockQtHoldTrade, Integer> {

    @Resource
    private StockQtHoldTradeMapper stockQtHoldTradeMapper;

    public List<StockQtHoldTrade> getBeanlistByHoldId(Integer hid) {
        return stockQtHoldTradeMapper.getBeanlistByHoldId(hid);
    }
}
