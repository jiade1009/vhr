
package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockBasicInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description : 股票基本信息
 * @author      : sam
 * @datetime    : 2022-10-11 10:15:00
 * @version:    : 1.0
 */
@RestController
@RequestMapping("/stock/basicinfo")
public class StockBasicInfoController {
    /**
     * 服务对象
     */
    @Resource
    private StockBasicInfoService stockBasicInfoService;
    @Resource
    private ExecPython execPython;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size, @RequestParam String keyword) {
        return stockBasicInfoService.getBeanlistByPage(page, size, keyword);
    }

    /**
     * @description : 重新更新股票信息，调用Python脚本
     */
    @DeleteMapping("/")
    public RespBean reloadBeanlist() {
        execPython.runPython(new String[]{BaseConstants.PY_API_LOAD_A_STOCK});
        return RespBean.ok("正在重新更新数据，请耐心等待!");
    }

    @PostMapping("/calendar")
    public RespBean reloadCalendar() {
        execPython.runPython(new String[]{BaseConstants.PY_API_LOAD_A_CALENDAR});
        return RespBean.ok("正在重新更新交易日历，请耐心等待!");
    }

}
