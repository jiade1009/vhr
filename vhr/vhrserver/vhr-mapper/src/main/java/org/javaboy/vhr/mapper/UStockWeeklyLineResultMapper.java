package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.UStockWeeklyLineResult;

import java.util.List;

/**
 * @ClassName   : UStockWeeklyLineResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Mapper
public interface UStockWeeklyLineResultMapper extends BaseMapper<UStockWeeklyLineResult, Integer> {
    List<UStockWeeklyLineResult> getBeanListByPro(@Param("dateWeeklyResearch") String dateWeeklyResearch,
                                                  @Param("generateType") Integer generateType,
                                                  @Param("emaStatus") Integer emaStatus);
}