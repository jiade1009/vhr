package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockTradeDateMapper;
import org.javaboy.vhr.model.HStockTradeDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : HStockTradeDateService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 10:48
 * @version:    : 1.0
 */

@Service
public class HStockTradeDateService extends BaseService<HStockTradeDate, String> {

    @Resource
    private HStockTradeDateMapper hStockTradeDateMapper;

    public HStockTradeDate getByDate(String date) {
        return hStockTradeDateMapper.getByDate(date);
    }
}
