package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockReturnResultMapper;
import org.javaboy.vhr.model.StockReturnResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockReturnResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 06日 21:21
 * @version:    : 1.0
 */

@Service
public class StockReturnResultService extends BaseService<StockReturnResult, Integer> {

    @Resource
    private StockReturnResultMapper stockReturnResultMapper;

    public List<StockReturnResult> getBeanlistByDateResearch(String dateResearch) {
        return stockReturnResultMapper.getBeanlistByDateResearch(dateResearch);
    }
}
