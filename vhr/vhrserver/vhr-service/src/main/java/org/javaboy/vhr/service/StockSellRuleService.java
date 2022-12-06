package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockSellRuleMapper;
import org.javaboy.vhr.model.StockSellRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockSellRuleService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 12月 05日 17:04
 * @version:    : 1.0
 */

@Service
public class StockSellRuleService extends BaseService<StockSellRule, Integer> {

    @Resource
    private StockSellRuleMapper stockSellRuleMapper;

    @Transactional
    public int runRuleById(Integer id) {
        // 1.先将现在运行的策略状态更改为过期
        int r_1 = stockSellRuleMapper.closeRunRule();
        // 2.将当前的草稿策略状态更改为运行
        int r_2 = stockSellRuleMapper.updateStatusById(id, 1);
        return r_2;
    }

    public List<StockSellRule> getBeanlistByStatus(Integer status) {
        return stockSellRuleMapper.getBeanlistByStatus(status);
    }

}
