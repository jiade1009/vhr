package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.StockMessageConf;

import java.util.List;

/**
 * @ClassName   : StockMessageConfMapper
 * @description : 股票消息发送配置，确定谁接收，接收什么类型消息，以什么形式发送消息
 * @author      : sam
 * @datetime    : 2022年 10月 30日 10:00
 * @version:    : 1.0
 */

@Mapper
public interface StockMessageConfMapper extends BaseMapper<StockMessageConf, Integer> {
    int deleteByEmpid(Integer empid);
    int insert(StockMessageConf record);

    List<StockMessageConf> getListByStatus(@Param("status") boolean status);
}