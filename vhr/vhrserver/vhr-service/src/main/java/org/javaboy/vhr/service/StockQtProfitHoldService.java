package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtProfitHoldMapper;
import org.javaboy.vhr.model.StockQtProfitHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : StockQtProfitHoldService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 24日 21:51
 * @version:    : 1.0
 */

@Service
public class StockQtProfitHoldService extends BaseService<StockQtProfitHold, Integer> {

    @Resource
    private StockQtProfitHoldMapper stockQtProfitHoldMapper;


}
