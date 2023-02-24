package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockBasicInfoMapper;
import org.javaboy.vhr.model.HStockBasicInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : StockHBasicInfoService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 20日 16:27
 * @version:    : 1.0
 */

@Service
public class HStockBasicInfoService extends BaseService<HStockBasicInfo, Integer> {

    @Resource
    private HStockBasicInfoMapper stockHBasicInfoMapper;

}
