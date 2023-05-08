package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.HStockSellRuleService;
import org.javaboy.vhr.service.StockQtSellRuleService;
import org.javaboy.vhr.service.StockSellRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description : 卖出规则
 * @author      : sam
 * @datetime    : 2022-12-05 17:23:53
 * @version:    : 1.0
 */

@RestController
public class StockSellRuleController {
    /**
     * 服务对象
     */
    @Resource
    private StockSellRuleService stockSellRuleService;
    @Resource
    private HStockSellRuleService hStockSellRuleService;
    @Resource
    private StockQtSellRuleService stockQtSellRuleService;

    @GetMapping("/stock/sellrule/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return stockSellRuleService.getBeanlistByPage(page, size, "");
    }

    @PostMapping("/stock/sellrule/")
    public RespBean addBean(@RequestBody StockSellRule bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        bean.setTimeUpdate(now);
        int result = stockSellRuleService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/stock/sellrule/")
    public RespBean updateBean(@RequestBody StockSellRule bean) {
        bean.setTimeUpdate(new Date());
        if (stockSellRuleService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 运行该卖出规则策略，更改该策略状态为运行，且将当前运行的策略状态更改为过期
     *
     * @param id
     * @return
     */
    @GetMapping("/stock/sellrule/run/{id}")
    public RespBean runBeanById(@PathVariable Integer id) {
        if (stockSellRuleService.runRuleById(id) == 1) {
            return RespBean.ok("运行成功!");
        }
        return RespBean.error("运行失败!");
    }

    /**
     * 判断是否存在草稿类型的卖出规则，如果存在，返回OK，否则返回ERROR
     *
     * @return
     */
    @GetMapping("/stock/sellrule/getDraft")
    public RespBean getDraft() {
        List<StockSellRule> list = stockSellRuleService.getBeanlistByStatus(0);
        if (list.isEmpty()) {
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }

    @GetMapping("/stock/sellrule/getRunning")
    public RespBean getRunning() {
        List<StockSellRule> list = stockSellRuleService.getBeanlistByStatus(1);
        if (list.isEmpty()){
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }

    // --------H股方法定义 begin -----------
    @GetMapping("/hstock/sellrule/getRunning")
    public RespBean getHRunning() {
        List<HStockSellRule> list = hStockSellRuleService.getBeanlistByStatus(1);
        if (list.isEmpty()){
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }



    // --------Qt 预测 方法定义 begin -----------
    @GetMapping("/qtstock/sellrule/")
    public RespPageBean getQtBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return stockQtSellRuleService.getBeanlistByPage(page, size, "");
    }

    @PostMapping("/qtstock/sellrule/")
    public RespBean addQtBean(@RequestBody StockQtSellRule bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        bean.setTimeUpdate(now);
        int result = stockQtSellRuleService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/qtstock/sellrule/")
    public RespBean updateQtBean(@RequestBody StockQtSellRule bean) {
        bean.setTimeUpdate(new Date());
        if (stockQtSellRuleService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 运行该卖出规则策略，更改该策略状态为运行，且将当前运行的策略状态更改为过期
     *
     * @param id
     * @return
     */
    @GetMapping("/qtstock/sellrule/run/{id}")
    public RespBean runQtBeanById(@PathVariable Integer id) {
        if (stockQtSellRuleService.runRuleById(id) == 1) {
            return RespBean.ok("运行成功!");
        }
        return RespBean.error("运行失败!");
    }

    /**
     * 判断是否存在草稿类型的卖出规则，如果存在，返回OK，否则返回ERROR
     *
     * @return
     */
    @GetMapping("/qtstock/sellrule/getDraft")
    public RespBean getQtDraft() {
        List<StockQtSellRule> list = stockQtSellRuleService.getBeanlistByStatus(0);
        if (list.isEmpty()) {
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }

    @GetMapping("/qtstock/sellrule/getRunning")
    public RespBean getQtRunning() {
        List<StockQtSellRule> list = stockQtSellRuleService.getBeanlistByStatus(1);
        if (list.isEmpty()){
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }
}
