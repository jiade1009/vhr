package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockBuyRuleMapper;
import org.javaboy.vhr.model.StockBuyRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockBuyRuleService
 * @datetime : 2022年 10月 13日 22:02
 * @version: : 1.0
 */

@Service
public class StockBuyRuleService extends BaseService<StockBuyRule, Integer> {

    @Resource
    private StockBuyRuleMapper stockBuyRuleMapper;

    @Transactional
    public int runRuleById(Integer id) {
        // 1.先将现在运行的策略状态更改为过期
        int r_1 = stockBuyRuleMapper.closeRunRule();
        // 2.将当前的草稿策略状态更改为运行
        int r_2 = stockBuyRuleMapper.updateStatusById(id, 1);
        return r_2;
    }

    public List<StockBuyRule> getBeanlistByStatus(Integer status) {
        return stockBuyRuleMapper.getBeanlistByStatus(status);
    }
}
