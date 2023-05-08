package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtHoldTrade;

import java.util.List;

/**
 * @ClassName   : StockQtHoldTradeMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 25日 22:54
 * @version:    : 1.0
 */

@Mapper
public interface StockQtHoldTradeMapper extends BaseMapper<StockQtHoldTrade, Integer> {

    List<StockQtHoldTrade> getBeanlistByHoldId(Integer hid);
}