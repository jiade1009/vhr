package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.UStockBuyRuleMapper;
import org.javaboy.vhr.model.UStockBuyRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : UStockBuyRuleService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Service
public class UStockBuyRuleService extends BaseService<UStockBuyRule, Integer> {

    @Resource
    private UStockBuyRuleMapper uStockBuyRuleMapper;

    @Transactional
    public int runRuleById(Integer id) {
        // 1.先将现在运行的策略状态更改为过期
        int r_1 = uStockBuyRuleMapper.closeRunRule();
        // 2.将当前的草稿策略状态更改为运行
        int r_2 = uStockBuyRuleMapper.updateStatusById(id, 1);
        return r_2;
    }

    public List<UStockBuyRule> getBeanlistByStatus(Integer status) {
        return uStockBuyRuleMapper.getBeanlistByStatus(status);
    }
}
