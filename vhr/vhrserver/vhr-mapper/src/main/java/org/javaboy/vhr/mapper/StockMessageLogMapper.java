package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockMessageLog;

import java.util.Date;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockMessageLogMapper
 * @description : TODO
 * @datetime : 2022年 10月 26日 17:27
 * @version: : 1.0
 */

@Mapper
public interface StockMessageLogMapper extends BaseMapper<StockMessageLog, Integer> {
    int insert(StockMessageLog record);

    Integer updateStockMessageLogStatus(@Param("msgId") String msgId, @Param("status") Integer status);

    List<StockMessageLog> getStockMessageLogByStatus();

    Integer updateCount(@Param("msgId") String msgId, @Param("date") Date date);
}