package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtWeeklyLineEmaResultMapper;
import org.javaboy.vhr.model.StockQtWeeklyLineEmaResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtWeeklyLineEmaResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 25日 22:55
 * @version:    : 1.0
 */

@Service
public class StockQtWeeklyLineEmaResultService extends BaseService<StockQtWeeklyLineEmaResult, Integer> {

    @Resource
    private StockQtWeeklyLineEmaResultMapper stockQtWeeklyLineEmaResultMapper;

    public List<StockQtWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId) {
        return stockQtWeeklyLineEmaResultMapper.getBeanlistByWeeklyId(weeklyId);
    }
}
