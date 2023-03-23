package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockProfitYearMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockProfitYear;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockProfitYearService
 * @description : TODO
 * @datetime : 2023年 03月 17日 19:09
 * @version: : 1.0
 */

@Service
public class StockProfitYearService extends BaseService<StockProfitYear, Integer> {

    @Resource
    private StockProfitYearMapper stockProfitYearMapper;

    public StockProfitYear getLatest() {
        return stockProfitYearMapper.getLatest();
    }

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, String[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockProfitYear> data = stockProfitYearMapper.getBeanlistByPage(page, size, keywords, beginDateScope);
        Long total = stockProfitYearMapper.getTotal(keywords, beginDateScope);

        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

}


