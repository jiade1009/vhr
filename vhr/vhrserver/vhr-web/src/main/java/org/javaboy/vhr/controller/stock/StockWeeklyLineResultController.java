package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockWeeklyLineResult;
import org.javaboy.vhr.service.StockWeeklyLineResultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022-10-12 15:16:23
 * @version:    : 1.0
 */

@RestController
@RequestMapping("/stock/weeklylineresult")
public class StockWeeklyLineResultController {
    /**
     * 服务对象
     */
    @Resource
    private StockWeeklyLineResultService stockWeeklyLineResultService;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        RespPageBean bean = stockWeeklyLineResultService.getBeanlistByPage(page, size);
        return bean;
    }

}
