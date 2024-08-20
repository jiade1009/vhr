package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockSubstepTrade;

import java.util.List;

/**
 * @ClassName   : StockSubstepTradeMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 06日 08:33
 * @version:    : 1.0
 */

@Mapper
public interface StockSubstepTradeMapper extends BaseMapper<StockSubstepTrade, Integer> {
    List<StockSubstepTrade> getBeanlistByStepId(Integer hid);
    void deleteByProfitId(Integer pid);
}