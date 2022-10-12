package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;

/**
 * @ClassName   : StockWeeklyLineEmaResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 10月 12日 16:33
 * @version:    : 1.0
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