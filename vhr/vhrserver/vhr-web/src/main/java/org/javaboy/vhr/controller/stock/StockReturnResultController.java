package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.HStockReturnResultService;
import org.javaboy.vhr.service.StockReturnResultService;
import org.javaboy.vhr.service.UStockReturnResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : sam
 * @ClassName : StockReturnResultController
 * @description : TODO
 * @datetime : 2023年 06月 06日 21:26
 * @version: : 1.0
 */
@RestController
public class StockReturnResultController {
    @Resource
    private StockReturnResultService stockReturnResultService;
    @Resource
    private HStockReturnResultService hStockReturnResultService;
    @Resource
    private UStockReturnResultService uStockReturnResultService;

    @GetMapping("/stock/returnresult/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return stockReturnResultService.getBeanlistByPage(page, size, "");
    }

    // --------H股方法定义 begin -----------
    @GetMapping("/hstock/returnresult/")
    public RespPageBean getHBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
        return hStockReturnResultService.getBeanlistByPage(page, size, "");
    }
    // --------U股方法定义 begin -----------
    @GetMapping("/ustock/returnresult/")
    public RespPageBean getUBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
        return uStockReturnResultService.getBeanlistByPage(page, size, "");
    }
}
