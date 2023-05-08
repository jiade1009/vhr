package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.HStockBuyRuleService;
import org.javaboy.vhr.service.StockBuyRuleService;
import org.javaboy.vhr.service.StockQtBuyRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (stock_buy_rule)表控制层
 *
 * @author sam
 */
@RestController
public class StockBuyRuleController {
    /**
     * 服务对象
     */
    @Resource
    private StockBuyRuleService stockBuyRuleService;
    @Resource
    private HStockBuyRuleService hStockBuyRuleService;
    @Resource
    private StockQtBuyRuleService stockQtBuyRuleService;

    @GetMapping("/stock/buyrule/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return stockBuyRuleService.getBeanlistByPage(page, size, "");
    }

    @PostMapping("/stock/buyrule/")
    public RespBean addBean(@RequestBody StockBuyRule bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        bean.setTimeUpdate(now);
        int result = stockBuyRuleService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/stock/buyrule/")
    public RespBean updateBean(@RequestBody StockBuyRule bean) {
        bean.setTimeUpdate(new Date());
        if (stockBuyRuleService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 运行该买入规则策略，更改该策略状态为运行，且将当前运行的策略状态更改为过期
     * @param id
     * @return
     */
    @GetMapping("/stock/buyrule/run/{id}")
    public RespBean runBeanById(@PathVariable Integer id) {
        if (stockBuyRuleService.runRuleById(id) == 1){
            return RespBean.ok("运行成功!");
        }
        return RespBean.error("运行失败!");
    }

    /**
     * 判断是否存在草稿类型的买入规则，如果存在，返回OK，否则返回ERROR
     * @return
     */
    @GetMapping("/stock/buyrule/getDraft")
    public RespBean getDraft() {
        List<StockBuyRule> list = stockBuyRuleService.getBeanlistByStatus(0);
        if (list.isEmpty()){
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }


    // --------H股方法定义 begin -----------
    @GetMapping("/hstock/buyrule/")
    public RespPageBean getHBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return hStockBuyRuleService.getBeanlistByPage(page, size, "");
    }

    @PostMapping("/hstock/buyrule/")
    public RespBean addHBean(@RequestBody HStockBuyRule bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        bean.setTimeUpdate(now);
        int result = hStockBuyRuleService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/hstock/buyrule/")
    public RespBean updateHBean(@RequestBody HStockBuyRule bean) {
        bean.setTimeUpdate(new Date());
        if (hStockBuyRuleService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 运行该买入规则策略，更改该策略状态为运行，且将当前运行的策略状态更改为过期
     * @param id
     * @return
     */
    @GetMapping("/hstock/buyrule/run/{id}")
    public RespBean runHBeanById(@PathVariable Integer id) {
        if (hStockBuyRuleService.runRuleById(id) == 1){
            return RespBean.ok("运行成功!");
        }
        return RespBean.error("运行失败!");
    }

    /**
     * 判断是否存在草稿类型的买入规则，如果存在，返回OK，否则返回ERROR
     * @return
     */
    @GetMapping("/hstock/buyrule/getDraft")
    public RespBean getHDraft() {
        List<HStockBuyRule> list = hStockBuyRuleService.getBeanlistByStatus(0);
        if (list.isEmpty()){
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }


    // --------Qt预测方法定义 begin -----------
    @GetMapping("/qtstock/buyrule/")
    public RespPageBean getQtBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
        return stockQtBuyRuleService.getBeanlistByPage(page, size, "");
    }

    @PostMapping("/qtstock/buyrule/")
    public RespBean addQtBean(@RequestBody StockQtBuyRule bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        bean.setTimeUpdate(now);
        int result = stockQtBuyRuleService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/qtstock/buyrule/")
    public RespBean updateQtBean(@RequestBody StockQtBuyRule bean) {
        bean.setTimeUpdate(new Date());
        if (stockQtBuyRuleService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 运行该买入规则策略，更改该策略状态为运行，且将当前运行的策略状态更改为过期
     * @param id
     * @return
     */
    @GetMapping("/qtstock/buyrule/run/{id}")
    public RespBean runQtBeanById(@PathVariable Integer id) {
        if (stockQtBuyRuleService.runRuleById(id) == 1){
            return RespBean.ok("运行成功!");
        }
        return RespBean.error("运行失败!");
    }

    /**
     * 判断是否存在草稿类型的买入规则，如果存在，返回OK，否则返回ERROR
     * @return
     */
    @GetMapping("/qtstock/buyrule/getDraft")
    public RespBean getQtDraft() {
        List<StockQtBuyRule> list = stockQtBuyRuleService.getBeanlistByStatus(0);
        if (list.isEmpty()){
            return RespBean.ok("运行成功!", false);
        } else {
            return RespBean.ok("运行成功!", list.get(0), false);
        }
    }
}
