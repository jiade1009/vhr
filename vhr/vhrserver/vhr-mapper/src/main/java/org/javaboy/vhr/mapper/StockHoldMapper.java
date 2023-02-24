package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockHold;

/**
 * @author : sam
 * @ClassName : StockHoldMapper
 * @description : TODO
 * @datetime : 2023年 02月 15日 09:59
 * @version: : 1.0
 */

@Mapper
public interface StockHoldMapper extends BaseMapper<StockHold, Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(StockHold record);

    StockHold selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(StockHold record);
}