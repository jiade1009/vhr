package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.UStockWeeklyLineEmaResultMapper;
import org.javaboy.vhr.model.UStockWeeklyLineEmaResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : UStockWeeklyLineEmaResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Service
public class UStockWeeklyLineEmaResultService extends BaseService<UStockWeeklyLineEmaResult, Integer> {

    @Resource
    private UStockWeeklyLineEmaResultMapper uStockWeeklyLineEmaResultMapper;

    public List<UStockWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId) {
        return uStockWeeklyLineEmaResultMapper.getBeanlistByWeeklyId(weeklyId);
    }

}
