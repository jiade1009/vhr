package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.HStockWeeklyLineResult;

import java.util.List;

/**
 * @ClassName   : HStockWeeklyLineResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 15:21
 * @version:    : 1.0
 */

@Mapper
public interface HStockWeeklyLineResultMapper extends BaseMapper<HStockWeeklyLineResult, Integer> {
    List<HStockWeeklyLineResult> getBeanListByPro(@Param("dateWeeklyResearch") String dateWeeklyResearch,
                                                  @Param("generateType") Integer generateType,
                                                  @Param("emaStatus") Integer emaStatus);
}