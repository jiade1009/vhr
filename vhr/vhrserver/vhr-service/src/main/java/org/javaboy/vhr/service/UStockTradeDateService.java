package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.UStockTradeDateMapper;
import org.javaboy.vhr.model.UStockTradeDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : UStockTradeDateService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Service
public class UStockTradeDateService extends BaseService<UStockTradeDate, String> {

    @Resource
    private UStockTradeDateMapper uStockTradeDateMapper;

    public UStockTradeDate getByDate(String date) {
        return uStockTradeDateMapper.getByDate(date);
    }
}
