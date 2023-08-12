package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.UStockReturnResult;

import java.util.List;

/**
 * @ClassName   : UStockReturnResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Mapper
public interface UStockReturnResultMapper extends BaseMapper<UStockReturnResult, Integer> {

    List<UStockReturnResult> getBeanlistByDateResearch(@Param("dateResearch") String dateResearch);
}