package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.StockHoldTrade;
import org.javaboy.vhr.service.StockHoldTradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/stock/holdtrade")
public class StockHoldTradeController {
    /**
     * 服务对象
     */
    @Resource
    private StockHoldTradeService stockHoldTradeService;

    @GetMapping("/byholdid")
    public List<StockHoldTrade> getBeanlistByWeeklyId(@RequestParam Integer hid) {
        return stockHoldTradeService.getBeanlistByHoldId(hid);
    }
}
