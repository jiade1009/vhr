package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
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
public interface StockBuyRuleMapper extends BaseMapper<StockBuyRule, Integer> {

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