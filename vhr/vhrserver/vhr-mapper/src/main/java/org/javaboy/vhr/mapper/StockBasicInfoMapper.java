package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.DatabaseType;
import org.javaboy.vhr.model.StockBasicInfo;

import java.util.List;

@Mapper
public interface StockBasicInfoMapper {
    int deleteByPrimaryKey(String code);

    int insert(StockBasicInfo record);

    int insertSelective(StockBasicInfo record);

    StockBasicInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(StockBasicInfo record);

    int updateByPrimaryKey(StockBasicInfo record);

    List<StockBasicInfo> getBeanlistByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keywords") String keywords);

    Long getTotal(@Param("keywords") String keywords);
}