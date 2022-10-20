package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineEmaResultMapper
 * @description : TODO
 * @datetime : 2022年 10月 19日 11:04
 * @version: : 1.0
 */

@Mapper
public interface StockWeeklyLineEmaResultMapper extends BaseMapper<StockWeeklyLineEmaResult, Integer> {

    List<StockWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId);
}