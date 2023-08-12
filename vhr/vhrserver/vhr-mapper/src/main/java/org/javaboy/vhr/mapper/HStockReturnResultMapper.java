package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.HStockReturnResult;

import java.util.List;

/**
 * @ClassName   : HStockReturnResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 06日 22:06
 * @version:    : 1.0
 */

@Mapper
public interface HStockReturnResultMapper extends BaseMapper<HStockReturnResult, Integer> {
    List<HStockReturnResult> getBeanlistByDateResearch(@Param("dateResearch") String dateResearch);
}