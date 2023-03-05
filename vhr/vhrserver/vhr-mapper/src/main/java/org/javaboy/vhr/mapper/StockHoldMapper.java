package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockHold;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockHoldMapper
 * @description : TODO
 * @datetime : 2023年 02月 15日 09:59
 * @version: : 1.0
 */

@Mapper
public interface StockHoldMapper extends BaseMapper<StockHold, Integer> {

    List<StockHold> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                      @Param("keywords") String keywords, @Param("status") Integer status);

    Long getTotal(@Param("keywords") String keywords, @Param("status") Integer status);

    int deleteByPrimaryKey(Integer id);

    int insert(StockHold record);

    StockHold selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(StockHold record);
}