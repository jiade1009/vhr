package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockFundFlowMapper;
import org.javaboy.vhr.model.StockFundFlow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : StockFundFlowService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 12日 21:41
 * @version:    : 1.0
 */

@Service
public class StockFundFlowService extends BaseService<StockFundFlow, Integer> {

    @Resource
    private StockFundFlowMapper stockFundFlowMapper;


}
