package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockATradeDate;
import org.javaboy.vhr.model.StockMessageConf;

/**
 * @ClassName   : StockATradeDateMapper
 * @description : A股交易日历
 * @author      : sam
 * @datetime    : 2022年 11月 13日 08:37
 * @version:    : 1.0
 */

@Mapper
public interface StockATradeDateMapper extends BaseMapper<StockMessageConf, String> {
    StockATradeDate getByDate(String date);
}