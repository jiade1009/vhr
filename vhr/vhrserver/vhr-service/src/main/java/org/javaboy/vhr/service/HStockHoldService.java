package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockHoldMapper;
import org.javaboy.vhr.model.HStockHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : HStockHoldService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 21:48
 * @version:    : 1.0
 */

@Service
public class HStockHoldService extends BaseService<HStockHold, Integer> {

    @Resource
    private HStockHoldMapper hStockHoldMapper;

}
