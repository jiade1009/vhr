package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.StockProfitYearService;
import org.javaboy.vhr.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (stock_profit_year)表控制层
 *
 * @author sam
 */
@RestController
public class StockProfitYearController {
    /**
     * 服务对象
     */
    @Resource
    private StockProfitYearService stockProfitYearService;

    @GetMapping("/stock/profityear/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          Date[] beginDateScope) {
        String[] scope = null;
        if (beginDateScope!=null) {
            System.out.println(beginDateScope[0]);
            scope = new String[]{DateUtils.formatDate(beginDateScope[0], "yyyy"),
                    DateUtils.formatDate(beginDateScope[0], "yyyy")};
        }
        System.out.println(scope);
        return stockProfitYearService.getBeanlistByPage(page, size, "", scope);
    }

}
