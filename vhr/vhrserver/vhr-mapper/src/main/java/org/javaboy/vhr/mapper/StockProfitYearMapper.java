package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockProfitYear;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockProfitYearMapper
 * @description : TODO
 * @datetime : 2023年 03月 22日 14:12
 * @version: : 1.0
 */

@Mapper
public interface StockProfitYearMapper extends BaseMapper<StockProfitYear, Integer> {

    List<StockProfitYear> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                            @Param("keywords") String keywords, @Param("beginDateScope") String[] beginDateScope);

    Long getTotal(@Param("keywords") String keywords, @Param("beginDateScope") String[] beginDateScope);

    StockProfitYear getLatest();
}