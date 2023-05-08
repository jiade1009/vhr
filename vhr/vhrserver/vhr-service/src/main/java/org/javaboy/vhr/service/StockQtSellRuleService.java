package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtSellRuleMapper;
import org.javaboy.vhr.model.StockQtSellRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtSellRuleService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 24日 21:51
 * @version:    : 1.0
 */

@Service
public class StockQtSellRuleService extends BaseService<StockQtSellRule, Integer> {

    @Resource
    private StockQtSellRuleMapper stockQtSellRuleMapper;

    @Transactional
    public int runRuleById(Integer id) {
        // 1.先将现在运行的策略状态更改为过期
        int r_1 = stockQtSellRuleMapper.closeRunRule();
        // 2.将当前的草稿策略状态更改为运行
        int r_2 = stockQtSellRuleMapper.updateStatusById(id, 1);
        return r_2;
    }

    public List<StockQtSellRule> getBeanlistByStatus(Integer status) {
        return stockQtSellRuleMapper.getBeanlistByStatus(status);
    }
}
