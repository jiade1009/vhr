package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockHandHold;

import java.util.List;

/**
 * @ClassName   : StockHandHoldMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 06日 20:09
 * @version:    : 1.0
 */

@Mapper
public interface StockHandHoldMapper extends BaseMapper<StockHandHold, Integer> {
    List<StockHandHold> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                      @Param("keywords") String keywords, @Param("status") Integer status);

    Long getTotal(@Param("keywords") String keywords, @Param("status") Integer status);
}