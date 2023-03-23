package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockProfitMonthMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockProfitMonth;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockProfitMonthService
 * @description : TODO
 * @datetime : 2023年 03月 17日 19:08
 * @version: : 1.0
 */

@Service
public class StockProfitMonthService extends BaseService<StockProfitMonth, Integer> {

    @Resource
    private StockProfitMonthMapper stockProfitMonthMapper;

    public StockProfitMonth getLatest() {
        return stockProfitMonthMapper.getLatest();
    }

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, String[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockProfitMonth> data = stockProfitMonthMapper.getBeanlistByPage(page, size, keywords, beginDateScope);
        Long total = stockProfitMonthMapper.getTotal(keywords, beginDateScope);

        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

}


