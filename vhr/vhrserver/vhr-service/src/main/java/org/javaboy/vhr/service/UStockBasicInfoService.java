package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.UStockBasicInfoMapper;
import org.javaboy.vhr.model.UStockBasicInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @ClassName   : UStockBasicInfoService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Service
public class UStockBasicInfoService extends BaseService<UStockBasicInfo, Integer> {

    @Resource
    private UStockBasicInfoMapper uStockBasicInfoMapper;

}
