package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockATradeDateMapper;
import org.javaboy.vhr.model.StockATradeDate;
import org.javaboy.vhr.model.StockMessageConf;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : StockATradeDateService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 11月 13日 08:37
 * @version:    : 1.0
 */

@Service
public class StockATradeDateService extends BaseService<StockMessageConf, String> {

    @Resource
    private StockATradeDateMapper stockATradeDateMapper;

    public StockATradeDate getByDate(String date) {
        return stockATradeDateMapper.getByDate(date);
    }

}
