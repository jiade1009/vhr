package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockBuyRule;
import org.javaboy.vhr.service.StockBuyRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (stock_buy_rule)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/stock_buy_rule")
public class StockBuyRuleController {
    /**
     * 服务对象
     */
    @Resource
    private StockBuyRuleService stockBuyRuleService;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return stockBuyRuleService.getBeanlistByPage(page, size);
    }

    @PostMapping("/")
    public RespBean addBean(@RequestBody StockBuyRule bean) {
        int result = stockBuyRuleService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateBean(@RequestBody StockBuyRule bean) {
        if (stockBuyRuleService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

}
