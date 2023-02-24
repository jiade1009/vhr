package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockBuyRuleMapper;
import org.javaboy.vhr.model.HStockBuyRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : HStockBuyRuleService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 13:23
 * @version:    : 1.0
 */

@Service
public class HStockBuyRuleService extends BaseService<HStockBuyRule, Integer> {

    @Resource
    private HStockBuyRuleMapper hStockBuyRuleMapper;

    @Transactional
    public int runRuleById(Integer id) {
        // 1.先将现在运行的策略状态更改为过期
        int r_1 = hStockBuyRuleMapper.closeRunRule();
        // 2.将当前的草稿策略状态更改为运行
        int r_2 = hStockBuyRuleMapper.updateStatusById(id, 1);
        return r_2;
    }

    public List<HStockBuyRule> getBeanlistByStatus(Integer status) {
        return hStockBuyRuleMapper.getBeanlistByStatus(status);
    }

}
