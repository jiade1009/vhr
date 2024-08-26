package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockSubstepTriggerMapper;
import org.javaboy.vhr.model.StockSubstepTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : StockSubstepTriggerService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 25日 01:48
 * @version:    : 1.0
 */

@Service
public class StockSubstepTriggerService extends BaseService<StockSubstepTrigger, Integer> {

    @Resource
    private StockSubstepTriggerMapper stockSubstepTriggerMapper;

}
