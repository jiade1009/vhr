package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockReturnResult;

/**
 * @ClassName   : StockReturnResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 06日 21:21
 * @version:    : 1.0
 */

@Mapper
public interface StockReturnResultMapper extends BaseMapper<StockReturnResult, Integer> {
}