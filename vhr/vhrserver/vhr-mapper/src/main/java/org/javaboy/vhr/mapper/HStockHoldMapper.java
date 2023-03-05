package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.HStockHold;

import java.util.List;

/**
 * @ClassName   : HStockHoldMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 21:48
 * @version:    : 1.0
 */

@Mapper
public interface HStockHoldMapper extends BaseMapper<HStockHold, Integer> {
    List<HStockHold> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                      @Param("keywords") String keywords, @Param("status") Integer status);
    Long getTotal(@Param("keywords") String keywords, @Param("status") Integer status);
}