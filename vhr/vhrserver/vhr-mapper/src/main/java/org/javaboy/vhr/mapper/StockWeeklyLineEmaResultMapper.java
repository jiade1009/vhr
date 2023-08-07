package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 获取指定调研日期自动生成的EMA数据结果
     * @param dateResearch
     * @return
     */
    List<StockWeeklyLineEmaResult> getBeanlistByDateResearch(@Param("dateResearch") String dateResearch);
}