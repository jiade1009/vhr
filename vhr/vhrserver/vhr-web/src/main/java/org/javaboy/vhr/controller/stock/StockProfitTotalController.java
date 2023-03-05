package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockProfitTotal;
import org.javaboy.vhr.service.StockProfitTotalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (stock_profit_total)表控制层
 *
 * @author sam
 */
@RestController
public class StockProfitTotalController {
    /**
     * 服务对象
     */
    @Resource
    private StockProfitTotalService stockProfitTotalService;

    @GetMapping("/stock/profittotal/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return stockProfitTotalService.getBeanlistByPage(page, size, "");
    }

    @GetMapping("/stock/profittotal/latest")
    public RespBean getLatest() {

        StockProfitTotal bean = stockProfitTotalService.getLatest();
        if (bean == null){
            return RespBean.error("不存在最新盈亏记录", false);
        } else {
            Double totalProfit = stockProfitTotalService.getTotalProfit();
            bean.setTotalProfit(totalProfit);
            return RespBean.ok("运行成功!", bean, false);
        }
    }

}
