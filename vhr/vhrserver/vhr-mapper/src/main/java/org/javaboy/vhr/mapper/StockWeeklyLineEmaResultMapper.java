package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineEmaResultMapper
 * @description :
 * @datetime : 2022年 10月 15日 08:11
 * @version: : 1.0
 */

@Mapper
public interface StockWeeklyLineEmaResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockWeeklyLineEmaResult record);

    int insertSelective(StockWeeklyLineEmaResult record);

    StockWeeklyLineEmaResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockWeeklyLineEmaResult record);

    int updateByPrimaryKey(StockWeeklyLineEmaResult record);
}