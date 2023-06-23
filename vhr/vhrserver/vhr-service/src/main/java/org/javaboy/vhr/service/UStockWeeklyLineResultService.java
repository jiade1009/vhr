package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.UStockWeeklyLineResultMapper;
import org.javaboy.vhr.model.UStockWeeklyLineResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : UStockWeeklyLineResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Service
public class UStockWeeklyLineResultService extends BaseService<UStockWeeklyLineResult, Integer> {

    @Resource
    private UStockWeeklyLineResultMapper uStockWeeklyLineResultMapper;

    /**
     * @description : 获取自动生成，且ema生成状态为生成成功的周线数据
     * @param dateWeeklyResearch: 周线生成日 yyyy
     * @param generateType: 0手动，1自动生成
     * @param emaStatus : 0未生成，1生成成功，2生成失败
     */
    public List<UStockWeeklyLineResult> getBeanListByPro(String dateWeeklyResearch, Integer generateType, Integer emaStatus) {
        return uStockWeeklyLineResultMapper.getBeanListByPro(dateWeeklyResearch, generateType, emaStatus);
    }

}
