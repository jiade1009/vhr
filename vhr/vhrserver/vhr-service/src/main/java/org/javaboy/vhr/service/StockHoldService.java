package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockHoldMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockHoldService
 * @description : 股票持有
 * @author      : sam
 * @datetime    : 2022年 10月 20日 21:50
 * @version:    : 1.0
 */

@Service
public class StockHoldService extends BaseService<StockHold, Integer> {

    @Resource
    private StockHoldMapper stockHoldMapper;

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, Integer status) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockHold> data = stockHoldMapper.getBeanlistByPage(page, size, keywords, status);
        Long total = stockHoldMapper.getTotal(keywords, status);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
