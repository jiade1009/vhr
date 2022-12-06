package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockHoldService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description : 股票池管理
 * @author      : sam
 * @datetime    : 2022-10-20 21:58:51
 * @version:    : 1.0
 */

@RestController
@RequestMapping("/stock/hold")
public class StockHoldController {
    /**
     * 服务对象
     */
    @Resource
    private StockHoldService stockHoldService;
    @Resource
    private ExecPython execPython;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          @RequestParam String keyword) {
        RespPageBean bean = stockHoldService.getBeanlistByPage(page, size, keyword);
        return bean;
    }

    @PutMapping("/runBuy")
    public RespBean runBuy(@RequestParam Integer holdId) {
        execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY, String.valueOf(holdId)});
        return RespBean.ok("正在执行股票买入操作，请耐心等待!");
    }

}
