package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.UStockWeeklyLineEmaResult;

import java.util.List;

/**
 * @ClassName   : UStockWeeklyLineEmaResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Mapper
public interface UStockWeeklyLineEmaResultMapper extends BaseMapper<UStockWeeklyLineEmaResult, Integer> {
    List<UStockWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId);

    /**
     * 获取指定调研日期自动生成的EMA数据结果
     * @param dateResearch
     * @return
     */
    List<UStockWeeklyLineEmaResult> getBeanlistByDateResearch(@Param("dateResearch") String dateResearch);
}