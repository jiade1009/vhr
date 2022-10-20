package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockHoldMapper;
import org.javaboy.vhr.model.StockHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
