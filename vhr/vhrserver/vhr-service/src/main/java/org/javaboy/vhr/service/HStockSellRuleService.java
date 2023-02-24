package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockSellRuleMapper;
import org.javaboy.vhr.model.HStockSellRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : HStockSellRuleService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 22:37
 * @version:    : 1.0
 */

@Service
public class HStockSellRuleService extends BaseService<HStockSellRule, Integer> {

    @Resource
    private HStockSellRuleMapper hStockSellRuleMapper;

    @Transactional
    public int runRuleById(Integer id) {
        // 1.先将现在运行的策略状态更改为过期
        int r_1 = hStockSellRuleMapper.closeRunRule();
        // 2.将当前的草稿策略状态更改为运行
        int r_2 = hStockSellRuleMapper.updateStatusById(id, 1);
        return r_2;
    }

    public List<HStockSellRule> getBeanlistByStatus(Integer status) {
        return hStockSellRuleMapper.getBeanlistByStatus(status);
    }


}
