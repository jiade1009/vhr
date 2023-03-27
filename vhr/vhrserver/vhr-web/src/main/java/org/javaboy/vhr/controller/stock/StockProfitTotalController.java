package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.StockProfitMonthService;
import org.javaboy.vhr.service.StockProfitTotalService;
import org.javaboy.vhr.service.StockProfitYearService;
import org.javaboy.vhr.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Resource
    private StockProfitMonthService stockProfitMonthService;
    @Resource
    private StockProfitYearService stockProfitYearService;

    @GetMapping("/stock/profittotal/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          Date[] beginDateScope) {
        String[] scope = null;
        if (beginDateScope!=null) {
            System.out.println(beginDateScope[0]);
            scope = new String[]{DateUtils.formatDate(beginDateScope[0], DateUtils.yyyyMMdd),
                    DateUtils.formatDate(beginDateScope[1], DateUtils.yyyyMMdd)};
        }
        return stockProfitTotalService.getBeanlistByPage(page, size, "", scope);
    }

    @GetMapping("/stock/profittotal/latest")
    public RespBean getLatest() {
        Map result = new HashMap();
        // 获取最新的日盈亏记录
        StockProfitTotal bean = stockProfitTotalService.getLatest();
        StockProfitMonth monthBean = stockProfitMonthService.getLatest();
        StockProfitYear yearBean = stockProfitYearService.getLatest();
        if (bean != null){
            result.put("profitDay", bean);
        }
        if (monthBean != null){
            result.put("profitMonth", monthBean);
        }
        if (yearBean != null){
            result.put("profitYear", yearBean);
        }
        Double totalProfit = stockProfitTotalService.getTotalProfit();
        bean.setTotalProfit(totalProfit);
        return RespBean.ok("运行成功!", result, false);
    }

}
