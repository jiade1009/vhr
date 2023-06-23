package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtProfitMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockQtProfit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtProfitService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 21日 20:41
 * @version:    : 1.0
 */

@Service
public class StockQtProfitService extends BaseService<StockQtProfit, Integer> {

    @Resource
    private StockQtProfitMapper stockQtProfitMapper;

    public RespPageBean getBeanlistByHoldId(Integer holdId) {
        List<StockQtProfit> data = stockQtProfitMapper.getBeanlistByHoldId(holdId);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        return bean;
    }

}
