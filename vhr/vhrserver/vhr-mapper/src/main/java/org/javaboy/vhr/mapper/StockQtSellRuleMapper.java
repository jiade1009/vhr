package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtSellRule;

import java.util.List;

/**
 * @ClassName   : StockQtSellRuleMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 24日 21:51
 * @version:    : 1.0
 */

@Mapper
public interface StockQtSellRuleMapper extends BaseMapper<StockQtSellRule, Integer> {

    // 关闭当前正在运行的策略
    int closeRunRule();

    /**
     * 更新指定id的Bean，更改其状态为指定status
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    List<StockQtSellRule> getBeanlistByStatus(@Param("status") Integer status);
}