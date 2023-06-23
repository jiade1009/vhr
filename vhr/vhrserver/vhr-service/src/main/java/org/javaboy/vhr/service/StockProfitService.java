package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockProfitMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockProfit;
import org.javaboy.vhr.model.StockQtProfit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockProfitService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 03日 10:57
 * @version:    : 1.0
 */

@Service
public class StockProfitService extends BaseService<StockProfit, Integer> {

    @Resource
    private StockProfitMapper stockProfitMapper;

    public RespPageBean getBeanlistByHoldId(Integer holdId) {
        List<StockProfit> data = stockProfitMapper.getBeanlistByHoldId(holdId);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        return bean;
    }
}
