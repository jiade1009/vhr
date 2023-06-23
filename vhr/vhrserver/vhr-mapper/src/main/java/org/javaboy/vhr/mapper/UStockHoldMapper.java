package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.UStockHold;

import java.util.List;

/**
 * @ClassName   : UStockHoldMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Mapper
public interface UStockHoldMapper extends BaseMapper<UStockHold, Integer> {

    List<UStockHold> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                       @Param("keywords") String keywords, @Param("status") Integer status);
    Long getTotal(@Param("keywords") String keywords, @Param("status") Integer status);
}