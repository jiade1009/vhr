package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockProfitTotalMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockProfitTotal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockProfitTotalService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 03日 10:57
 * @version:    : 1.0
 */

@Service
public class StockProfitTotalService extends BaseService<StockProfitTotal, Integer> {

    @Resource
    private StockProfitTotalMapper stockProfitTotalMapper;

    public StockProfitTotal getLatest() {
        return stockProfitTotalMapper.getLatest();
    }
    public Double getTotalProfit() {
        return stockProfitTotalMapper.getTotalProfit();
    }

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, String[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockProfitTotal> data = stockProfitTotalMapper.getBeanlistByPage(page, size, keywords, beginDateScope);
        Long total = stockProfitTotalMapper.getTotal(keywords, beginDateScope);

        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

}
