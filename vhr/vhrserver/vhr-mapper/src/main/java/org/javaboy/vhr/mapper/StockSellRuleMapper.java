package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockSellRule;

import java.util.List;

/**
 * @ClassName   : StockSellRuleMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 12月 05日 17:04
 * @version:    : 1.0
 */

@Mapper
public interface StockSellRuleMapper extends BaseMapper<StockSellRule, Integer> {

    // 关闭当前正在运行的策略
    int closeRunRule();

    /**
     * 更新指定id的Bean，更改其状态为指定status
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    List<StockSellRule> getBeanlistByStatus(@Param("status") Integer status);
}