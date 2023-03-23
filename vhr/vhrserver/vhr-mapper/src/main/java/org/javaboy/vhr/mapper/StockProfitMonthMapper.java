package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockProfitMonth;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockProfitMonthMapper
 * @description : TODO
 * @datetime : 2023年 03月 22日 14:12
 * @version: : 1.0
 */

@Mapper
public interface StockProfitMonthMapper extends BaseMapper<StockProfitMonth, Integer> {

    List<StockProfitMonth> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                             @Param("keywords") String keywords, @Param("beginDateScope") String[] beginDateScope);

    Long getTotal(@Param("keywords") String keywords, @Param("beginDateScope") String[] beginDateScope);

    StockProfitMonth getLatest();
}