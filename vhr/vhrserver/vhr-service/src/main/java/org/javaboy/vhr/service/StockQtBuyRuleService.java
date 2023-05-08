package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtBuyRuleMapper;
import org.javaboy.vhr.model.StockQtBuyRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtBuyRuleService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 21日 21:06
 * @version:    : 1.0
 */

@Service
public class StockQtBuyRuleService extends BaseService<StockQtBuyRule, Integer> {

    @Resource
    private StockQtBuyRuleMapper stockQtBuyRuleMapper;


    @Transactional
    public int runRuleById(Integer id) {
        // 1.先将现在运行的策略状态更改为过期
        int r_1 = stockQtBuyRuleMapper.closeRunRule();
        // 2.将当前的草稿策略状态更改为运行
        int r_2 = stockQtBuyRuleMapper.updateStatusById(id, 1);
        return r_2;
    }

    public List<StockQtBuyRule> getBeanlistByStatus(Integer status) {
        return stockQtBuyRuleMapper.getBeanlistByStatus(status);
    }
}
