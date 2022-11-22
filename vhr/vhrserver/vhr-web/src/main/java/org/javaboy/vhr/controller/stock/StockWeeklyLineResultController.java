package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockWeeklyLineResultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description : 周线数据导入结果controller
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

    @Resource
    private ExecPython execPython;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        RespPageBean bean = stockWeeklyLineResultService.getBeanlistByPage(page, size);
        return bean;
    }

    @PostMapping("/")
    public RespBean newWeekly() {
        // 传递2个参数{"操作指令:生成周线", "操作方式：0手动，1自动"}
        execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_A_WEEKLY, "0"});
        return RespBean.ok("正在生成Weekly数据线，请耐心等待!");
    }

}
