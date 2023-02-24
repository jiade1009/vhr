package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockWeeklyLineResultMapper;
import org.javaboy.vhr.model.HStockWeeklyLineResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : HStockWeeklyLineResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 15:21
 * @version:    : 1.0
 */

@Service
public class HStockWeeklyLineResultService extends BaseService<HStockWeeklyLineResult, Integer> {

    @Resource
    private HStockWeeklyLineResultMapper hStockWeeklyLineResultMapper;


    /**
     * @description : 获取自动生成，且ema生成状态为生成成功的周线数据
     * @param dateWeeklyResearch: 周线生成日 yyyy
     * @param generateType: 0手动，1自动生成
     * @param emaStatus : 0未生成，1生成成功，2生成失败
     */
    public List<HStockWeeklyLineResult> getBeanListByPro(String dateWeeklyResearch, Integer generateType, Integer emaStatus) {
        return hStockWeeklyLineResultMapper.getBeanListByPro(dateWeeklyResearch, generateType, emaStatus);
    }

}
