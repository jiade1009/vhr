package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtWeeklyLineResult;

import java.util.List;

/**
 * @ClassName   : StockQtWeeklyLineResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 25日 22:55
 * @version:    : 1.0
 */

@Mapper
public interface StockQtWeeklyLineResultMapper extends BaseMapper<StockQtWeeklyLineResult, Integer> {
    /**
     *
     * @param dateWeeklyResearch 周线生成日 yyyyMMdd
     * @param generateType
     * @param emaStatus
     * @return
     */
    List<StockQtWeeklyLineResult> getBeanListByPro(@Param("dateWeeklyResearch") String dateWeeklyResearch,
                                                   @Param("generateType") Integer generateType,
                                                   @Param("emaStatus") Integer emaStatus);
}