package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.HStockWeeklyLineEmaResult;

import java.util.List;

/**
 * @ClassName   : HStockWeeklyLineEmaResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 15:21
 * @version:    : 1.0
 */

@Mapper
public interface HStockWeeklyLineEmaResultMapper extends BaseMapper<HStockWeeklyLineEmaResult, Integer> {
    List<HStockWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId);
    /**
     * 获取指定调研日期自动生成的EMA数据结果
     * @param dateResearch
     * @return
     */
    List<HStockWeeklyLineEmaResult> getBeanlistByDateResearch(@Param("dateResearch") String dateResearch);
}