package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockHoldBonusMapper;
import org.javaboy.vhr.model.StockHoldBonus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : StockHoldBonusService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 12日 21:43
 * @version:    : 1.0
 */

@Service
public class StockHoldBonusService extends BaseService<StockHoldBonus, Integer> {

    @Resource
    private StockHoldBonusMapper stockHoldBonusMapper;

}
