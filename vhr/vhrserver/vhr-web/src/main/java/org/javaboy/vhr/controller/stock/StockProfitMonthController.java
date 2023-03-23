package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.StockProfitMonthService;
import org.javaboy.vhr.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (stock_profit_month)表控制层
 *
 * @author sam
 */
@RestController
public class StockProfitMonthController {
    /**
     * 服务对象
     */
    @Resource
    private StockProfitMonthService stockProfitMonthService;

    @GetMapping("/stock/profitmonth/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          Date[] beginDateScope) {
        String[] scope = null;
        if (beginDateScope!=null) {
            System.out.println(beginDateScope[0]);
            scope = new String[]{DateUtils.formatDate(beginDateScope[0], "yyyyMM"),
                    DateUtils.formatDate(beginDateScope[1], "yyyyMM")};
        }
        System.out.println(scope);
        return stockProfitMonthService.getBeanlistByPage(page, size, "", scope);
    }

}
