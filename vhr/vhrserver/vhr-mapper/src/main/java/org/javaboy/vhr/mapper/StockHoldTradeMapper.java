package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockHoldTrade;

import java.util.List;

/**
 * @ClassName   : StockHoldTradeMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 10月 23日 21:39
 * @version:    : 1.0
 */

@Mapper
public interface StockHoldTradeMapper extends BaseMapper<StockHoldTrade, Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(StockHoldTrade record);

    StockHoldTrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(StockHoldTrade record);

    List<StockHoldTrade> getBeanlistByHoldId(Integer hid);
}