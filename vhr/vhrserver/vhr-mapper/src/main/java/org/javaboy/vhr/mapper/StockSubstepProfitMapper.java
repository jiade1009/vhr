package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockSubstepProfit;

import java.util.List;

/**
 * @ClassName   : StockSubstepProfitMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 06日 08:33
 * @version:    : 1.0
 */

@Mapper
public interface StockSubstepProfitMapper extends BaseMapper<StockSubstepProfit, Integer> {
    List<StockSubstepProfit> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                               @Param("keywords") String keywords, @Param("status") Integer status);
    Long getTotal(@Param("keywords") String keywords, @Param("status") Integer status);

    StockSubstepProfit getRunningByCode(@Param("code") String code);
}