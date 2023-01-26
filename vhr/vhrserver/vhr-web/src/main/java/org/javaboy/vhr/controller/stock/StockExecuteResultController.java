package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockExecuteResult;
import org.javaboy.vhr.service.StockExecuteResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022-11-25 18:11:15
 * @version:    : 1.0
 */
@RestController
@RequestMapping("/stock/executeresult")
public class StockExecuteResultController {
    /**
     * 服务对象
     */
    @Resource
    private StockExecuteResultService stockExecuteResultService;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          StockExecuteResult bean) {
        RespPageBean result = stockExecuteResultService.getBeanlistByBeanAndPage(page, size, bean);
        return result;
    }
}
