package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockHold;

/**
 * @ClassName   : StockHoldMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 10月 20日 21:50
 * @version:    : 1.0
 */

@Mapper
public interface StockHoldMapper extends BaseMapper<StockHold, Integer> {
}