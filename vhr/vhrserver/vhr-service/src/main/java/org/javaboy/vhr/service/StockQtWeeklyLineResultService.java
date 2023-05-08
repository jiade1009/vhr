package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtWeeklyLineResultMapper;
import org.javaboy.vhr.model.StockQtWeeklyLineResult;
import org.javaboy.vhr.model.StockWeeklyLineResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtWeeklyLineResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 25日 22:55
 * @version:    : 1.0
 */

@Service
public class StockQtWeeklyLineResultService extends BaseService<StockQtWeeklyLineResult, Integer> {

    @Resource
    private StockQtWeeklyLineResultMapper stockQtWeeklyLineResultMapper;

    /*
     * @description : 获取自动生成，且ema生成状态为生成成功的周线数据
     * @param dateWeeklyResearch: 周线生成日 yyyy
     * @param generateType: 0手动，1自动生成
     * @param emaStatus : 0未生成，1生成成功，2生成失败
     */
    public List<StockQtWeeklyLineResult> getBeanListByPro(String dateWeeklyResearch, Integer generateType, Integer emaStatus) {
        return stockQtWeeklyLineResultMapper.getBeanListByPro(dateWeeklyResearch, generateType, emaStatus);
    }
}
