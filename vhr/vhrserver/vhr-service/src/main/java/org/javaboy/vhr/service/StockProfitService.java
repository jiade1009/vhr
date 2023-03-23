package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockProfitMapper;
import org.javaboy.vhr.model.StockProfit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
