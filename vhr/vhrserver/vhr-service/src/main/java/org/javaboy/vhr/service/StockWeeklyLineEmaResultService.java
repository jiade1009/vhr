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

    /**
     * 获取指定调研日期自动生成的EMA数据结果
     * @param dateResearch
     * @return
     */
    public List<StockWeeklyLineEmaResult> getBeanlistByDateResearch(String dateResearch) {
        return stockWeeklyLineEmaResultMapper.getBeanlistByDateResearch(dateResearch);
    }
}


