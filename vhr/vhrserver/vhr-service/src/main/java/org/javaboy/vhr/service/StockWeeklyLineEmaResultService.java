package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockWeeklyLineEmaResultMapper;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineEmaResultService
 * @datetime : 2022年 10月 12日 16:33
 * @version: : 1.0
 */

@Service
public class StockWeeklyLineEmaResultService extends BaseService<StockWeeklyLineEmaResult, Integer> {

    @Resource
    private StockWeeklyLineEmaResultMapper stockWeeklyLineEmaResultMapper;

    public List<StockWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId) {
        return stockWeeklyLineEmaResultMapper.getBeanlistByWeeklyId(weeklyId);
    }
}


