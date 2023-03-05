package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.StockProfitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (stock_profit)表控制层
 *
 * @author sam
 */
@RestController
public class StockProfitController {
    /**
     * 服务对象
     */
    @Resource
    private StockProfitService stockProfitService;

    @GetMapping("/stock/profit/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        return stockProfitService.getBeanlistByPage(page, size, keyword);
    }

}
