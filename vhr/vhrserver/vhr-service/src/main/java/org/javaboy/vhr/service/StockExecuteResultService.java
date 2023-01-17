package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockExecuteResultMapper;
import org.javaboy.vhr.model.StockExecuteResult;
import org.javaboy.vhr.model.StockSellRule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockExecuteResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 01月 04日 10:23
 * @version:    : 1.0
 */

@Service
public class StockExecuteResultService extends BaseService<StockExecuteResult, Integer> {

    @Resource
    private StockExecuteResultMapper stockExecuteResultMapper;

    public List<StockExecuteResult> getBeanlistByCommand(String command, String dateExec) {
        return stockExecuteResultMapper.getBeanlistByCommand(command, dateExec);
    }
}
