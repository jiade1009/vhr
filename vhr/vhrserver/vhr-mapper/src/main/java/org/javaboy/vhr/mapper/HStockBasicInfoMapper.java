package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.base.BaseMapper;
import org.javaboy.vhr.model.HStockBasicInfo;

/**
 * @ClassName   : HStockBasicInfoMapper
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 20日 16:27
 * @version:    : 1.0
 */

@Mapper
public interface HStockBasicInfoMapper extends BaseMapper<HStockBasicInfo, Integer> {
}