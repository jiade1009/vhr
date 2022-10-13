package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.StockBuyRule;
import org.javaboy.vhr.model.StockWeeklyLineResult;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockBuyRuleMapper
 * @description : TODO
 * @datetime : 2022年 10月 13日 22:02
 * @version: : 1.0
 */

@Mapper
public interface StockBuyRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockBuyRule record);

    int insertSelective(StockBuyRule record);

    StockBuyRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockBuyRule record);

    int updateByPrimaryKey(StockBuyRule record);

    List<StockBuyRule> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size);

    Long getTotal();
}