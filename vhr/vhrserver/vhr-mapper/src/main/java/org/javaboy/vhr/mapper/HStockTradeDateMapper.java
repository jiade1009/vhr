package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.HStockTradeDate;

/**
 * @ClassName   : HStockTradeDateMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 10:48
 * @version:    : 1.0
 */

@Mapper
public interface HStockTradeDateMapper extends BaseMapper<HStockTradeDate, String> {
    HStockTradeDate getByDate(String date);
}