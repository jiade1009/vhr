package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtProfit;

import java.util.List;

/**
 * @ClassName   : StockQtProfitMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 21日 20:41
 * @version:    : 1.0
 */

@Mapper
public interface StockQtProfitMapper extends BaseMapper<StockQtProfit, Integer> {
    List<StockQtProfit> getBeanlistByHoldId(@Param("holdId") Integer holdId);
}