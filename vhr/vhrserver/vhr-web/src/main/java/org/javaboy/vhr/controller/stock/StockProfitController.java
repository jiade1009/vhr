package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.StockProfitService;
import org.javaboy.vhr.service.StockQtProfitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Resource
    private StockQtProfitService stockQtProfitService;

    // --------A股方法定义 begin -----------
    @GetMapping("/stock/profit/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        if (size==-1){
            page=null;
            size=null;
        }
        return stockProfitService.getBeanlistByPage(page, size, keyword);
    }

    @GetMapping("/stock/profit/{holdId}")
    public RespPageBean getBeanlistByHoldId(@PathVariable Integer holdId) {
        return stockProfitService.getBeanlistByHoldId(holdId);
    }

    // --------QT预测方法定义 begin -----------
    @GetMapping("/qtstock/profit/")
    public RespPageBean getQtBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        if (size==-1){
            page=null;
            size=null;
        }
        return stockQtProfitService.getBeanlistByPage(page, size, keyword);
    }

    @GetMapping("/qtstock/profit/{holdId}")
    public RespPageBean getQtBeanlistByHoldId(@PathVariable String holdId) {
        return stockQtProfitService.getBeanlistByHoldId(Integer.valueOf(holdId));
    }

}
