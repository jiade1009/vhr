package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtHold;

import java.util.List;

/**
 * @ClassName   : StockQtHoldMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 20日 20:37
 * @version:    : 1.0
 */

@Mapper
public interface StockQtHoldMapper extends BaseMapper<StockQtHold, Integer> {
    List<StockQtHold> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                      @Param("keywords") String keywords, @Param("status") Integer status);

    Long getTotal(@Param("keywords") String keywords, @Param("status") Integer status);
}