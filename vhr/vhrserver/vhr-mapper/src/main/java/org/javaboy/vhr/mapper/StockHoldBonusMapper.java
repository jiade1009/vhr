package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockHoldBonus;

/**
 * @ClassName   : StockHoldBonusMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 12日 21:43
 * @version:    : 1.0
 */

@Mapper
public interface StockHoldBonusMapper extends BaseMapper<StockHoldBonus, Integer> {
}