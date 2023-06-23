package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.UStockBuyRule;

import java.util.List;

/**
 * @ClassName   : UStockBuyRuleMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Mapper
public interface UStockBuyRuleMapper extends BaseMapper<UStockBuyRule, Integer> {
    // 关闭当前正在运行的策略
    int closeRunRule();

    /**
     * 更新指定id的Bean，更改其状态为指定status
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    List<UStockBuyRule> getBeanlistByStatus(@Param("status") Integer status);
}