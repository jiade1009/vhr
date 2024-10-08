package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockProfit;

import java.util.List;

/**
 * @ClassName   : StockProfitMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 03日 10:57
 * @version:    : 1.0
 */

@Mapper
public interface StockProfitMapper extends BaseMapper<StockProfit, Integer> {
    List<StockProfit> getBeanlistByHoldId(@Param("holdId") Integer holdId);

}