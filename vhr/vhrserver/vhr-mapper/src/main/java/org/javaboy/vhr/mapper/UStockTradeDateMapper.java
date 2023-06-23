package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.UStockTradeDate;

/**
 * @ClassName   : UStockTradeDateMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Mapper
public interface UStockTradeDateMapper extends BaseMapper<UStockTradeDate, String> {
    UStockTradeDate getByDate(String date);
}