package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockHoldTradeMapper;
import org.javaboy.vhr.model.StockHoldTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockHoldTradeService
 * @description : TODO
 * @datetime : 2022年 10月 23日 21:39
 * @version: : 1.0
 */

@Service
public class StockHoldTradeService extends BaseService<StockHoldTrade, Integer> {

    @Resource
    private StockHoldTradeMapper stockHoldTradeMapper;

    public List<StockHoldTrade> getBeanlistByHoldId(Integer hid) {
        return stockHoldTradeMapper.getBeanlistByHoldId(hid);
    }
}
