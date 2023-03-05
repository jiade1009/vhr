package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockProfitTotal;

/**
 * @ClassName   : StockProfitTotalMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 03日 10:57
 * @version:    : 1.0
 */

@Mapper
public interface StockProfitTotalMapper extends BaseMapper<StockProfitTotal, Integer> {

    StockProfitTotal getLatest();

    Double getTotalProfit();
}