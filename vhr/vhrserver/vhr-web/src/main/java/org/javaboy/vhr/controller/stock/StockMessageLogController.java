package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockMessageLog;
import org.javaboy.vhr.service.StockMessageLogService;
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
@RequestMapping("/stock/messagelog")
public class StockMessageLogController {
    /**
     * 服务对象
     */
    @Resource
    private StockMessageLogService stockMessageLogService;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          StockMessageLog bean) {
        RespPageBean result = stockMessageLogService.getBeanlistByBeanAndPage(page, size, bean);
        return result;
    }
}
