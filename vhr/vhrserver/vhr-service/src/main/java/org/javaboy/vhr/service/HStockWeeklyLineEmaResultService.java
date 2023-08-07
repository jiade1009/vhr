package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockWeeklyLineEmaResultMapper;
import org.javaboy.vhr.model.HStockWeeklyLineEmaResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : HStockWeeklyLineEmaResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 15:21
 * @version:    : 1.0
 */

@Service
public class HStockWeeklyLineEmaResultService extends BaseService<HStockWeeklyLineEmaResult, Integer> {

    @Resource
    private HStockWeeklyLineEmaResultMapper hStockWeeklyLineEmaResultMapper;


    public List<HStockWeeklyLineEmaResult> getBeanlistByWeeklyId(Integer weeklyId) {
        return hStockWeeklyLineEmaResultMapper.getBeanlistByWeeklyId(weeklyId);
    }

    /**
     * 获取指定调研日期自动生成的EMA数据结果
     * @param dateResearch
     * @return
     */
    public List<HStockWeeklyLineEmaResult> getBeanlistByDateResearch(String dateResearch) {
        return hStockWeeklyLineEmaResultMapper.getBeanlistByDateResearch(dateResearch);
    }

}
