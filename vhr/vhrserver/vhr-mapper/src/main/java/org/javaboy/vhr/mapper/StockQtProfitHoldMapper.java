package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockQtProfitHold;

import java.util.List;

/**
 * @ClassName   : StockQtProfitHoldMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 24日 21:51
 * @version:    : 1.0
 */

@Mapper
public interface StockQtProfitHoldMapper extends BaseMapper<StockQtProfitHold, Integer> {

    /**
     * 获取包括股票加入方式字段（直接或者回头草）
     * @param page
     * @param size
     * @param keywords
     * @return
     */
    List<StockQtProfitHold> getExtendBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keywords") String keywords);

}