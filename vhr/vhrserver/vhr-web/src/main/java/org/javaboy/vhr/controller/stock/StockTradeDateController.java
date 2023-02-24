
package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.HStockTradeDateService;
import org.javaboy.vhr.service.StockATradeDateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description : 股票基本信息
 * @author      : sam
 * @datetime    : 2022-10-11 10:15:00
 * @version:    : 1.0
 */
@RestController
public class StockTradeDateController {
    /**
     * 服务对象
     */
    @Resource
    private StockATradeDateService stockATradeDateService;
    @Resource
    private HStockTradeDateService hStockTradeDateService;

    @GetMapping("/stock/tradedate/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size, @RequestParam String keyword) {
        return stockATradeDateService.getBeanlistByPage(page, size, keyword);
    }

    @GetMapping("/hstock/tradedate/")
    public RespPageBean getHBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size, @RequestParam String keyword) {
        return hStockTradeDateService.getBeanlistByPage(page, size, keyword);
    }
}
