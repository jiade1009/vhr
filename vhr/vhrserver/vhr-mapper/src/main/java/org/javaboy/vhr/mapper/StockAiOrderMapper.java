package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockAiOrder;

import java.util.List;

/**
 * @ClassName   : StockAiOrderMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 05月 02日 17:40
 * @version:    : 1.0
 */

@Mapper
public interface StockAiOrderMapper extends BaseMapper<StockAiOrder, Integer> {
    Integer addOrders(@Param("list") List<StockAiOrder> list);
    StockAiOrder getByOrderNo(@Param("orderNo") String orderNo, @Param("orderSource") String orderSource);

    List<StockAiOrder> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size,
                                         @Param("keywords") String keywords, @Param("status") Integer status);
    Long getTotal(@Param("keywords") String keywords, @Param("status") Integer status);
    /**
     * 停止所有指定订单来源的智能订单
     * @param orderSource
     * @return
     */
    Integer closeStatus(@Param("orderSource") String orderSource);
}