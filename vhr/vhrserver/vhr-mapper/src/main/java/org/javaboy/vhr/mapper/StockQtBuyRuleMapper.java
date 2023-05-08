package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtBuyRule;

import java.util.List;

/**
 * @ClassName   : StockQtBuyRuleMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 21日 21:06
 * @version:    : 1.0
 */

@Mapper
public interface StockQtBuyRuleMapper extends BaseMapper<StockQtBuyRule, Integer> {
    // 关闭当前正在运行的策略
    int closeRunRule();

    /**
     * 更新指定id的Bean，更改其状态为指定status
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    List<StockQtBuyRule> getBeanlistByStatus(@Param("status") Integer status);
}