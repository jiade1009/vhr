package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtWeeklyLineEmaResult;

import java.util.List;

/**
 * @ClassName   : StockQtWeeklyLineEmaResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 25日 22:55
 * @version:    : 1.0
 */

@Mapper
public interface StockQtWeeklyLineEmaResultMapper extends BaseMapper<StockQtWeeklyLineEmaResult, Integer> {
    List<StockQtWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId);
}