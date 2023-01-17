package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockExecuteResult;

import java.util.List;

/**
 * @ClassName   : StockExecuteResultMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 01月 04日 10:23
 * @version:    : 1.0
 */

@Mapper
public interface StockExecuteResultMapper extends BaseMapper<StockExecuteResult, Integer> {

    List<StockExecuteResult> getBeanlistByCommand(@Param("command")String command, @Param("dateExec")String dateExec);
}