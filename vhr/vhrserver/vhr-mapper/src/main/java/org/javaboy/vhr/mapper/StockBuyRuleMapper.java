package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.StockBuyRule;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockBuyRuleMapper
 * @description :
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

    // 关闭当前正在运行的策略
    int closeRunRule();

    /**
     * 更新指定id的Bean，更改其状态为指定status
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    List<StockBuyRule> getBeanlistByStatus(@Param("status") Integer status);
}