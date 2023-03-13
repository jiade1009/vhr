package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.StockHandHoldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description : 股票池管理
 * @author      : sam
 * @datetime    : 2022-10-20 21:58:51
 * @version:    : 1.0
 */
@RestController
public class StockHandHoldController {
    /**
     * 服务对象
     */
    @Resource
    private StockHandHoldService stockHandHoldService;

    @GetMapping("/stock/handhold/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword,
                                          Integer status) {
        RespPageBean bean = stockHandHoldService.getBeanlistByPage(page, size, keyword, status);
        return bean;
    }
}
