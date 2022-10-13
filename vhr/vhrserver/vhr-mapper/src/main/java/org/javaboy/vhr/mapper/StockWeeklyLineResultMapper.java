package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.StockBasicInfo;
import org.javaboy.vhr.model.StockWeeklyLineResult;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineResultMapper
 * @datetime : 2022年 10月 12日 15:12
 * @version: : 1.0
 */

@Mapper
public interface StockWeeklyLineResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockWeeklyLineResult record);

    int insertSelective(StockWeeklyLineResult record);

    StockWeeklyLineResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockWeeklyLineResult record);

    int updateByPrimaryKey(StockWeeklyLineResult record);

    List<StockWeeklyLineResult> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size);

    Long getTotal();
}