package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockProfitTotal;

import java.util.List;

/**
 * @ClassName   : StockProfitTotalMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 03日 10:57
 * @version:    : 1.0
 */

@Mapper
public interface StockProfitTotalMapper extends BaseMapper<StockProfitTotal, Integer> {

    StockProfitTotal getLatest();

    Double getTotalProfit();

    List<StockProfitTotal> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                        @Param("keywords") String keywords, @Param("beginDateScope") String[] beginDateScope);

    Long getTotal(@Param("keywords") String keywords, @Param("beginDateScope") String[] beginDateScope);
}