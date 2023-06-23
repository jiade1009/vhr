package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockHoldTrade;
import org.javaboy.vhr.model.StockQtHoldTrade;
import org.javaboy.vhr.service.StockHoldTradeService;
import org.javaboy.vhr.service.StockQtHoldTradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (stock_hold_trade)表控制层
 *
 * @author xxxxx
 */
@RestController
public class StockHoldTradeController {
    /**
     * 服务对象
     */
    @Resource
    private StockHoldTradeService stockHoldTradeService;

    @Resource
    private StockQtHoldTradeService stockQtHoldTradeService;

    @GetMapping("/stock/holdtrade/byholdid")
    public List<StockHoldTrade> getBeanlistByWeeklyId(@RequestParam Integer hid) {
        return stockHoldTradeService.getBeanlistByHoldId(hid);
    }

    @GetMapping("/stock/holdtrade/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        RespPageBean bean = stockHoldTradeService.getBeanlistByPage(page, size, keyword);
        return bean;
    }

    @GetMapping("/qtstock/holdtrade/byholdid")
    public List<StockQtHoldTrade> getQtBeanlistByWeeklyId(@RequestParam Integer hid) {
        return stockQtHoldTradeService.getBeanlistByHoldId(hid);
    }

}
