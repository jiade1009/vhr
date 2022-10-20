package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockWeeklyLineEmaResultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (stock_weekly_line_ema_result)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/stock/weeklylineemaresult")
public class StockWeeklyLineEmaResultController {
    /**
     * 服务对象
     */
    @Resource
    private StockWeeklyLineEmaResultService stockWeeklyLineEmaResultService;
    @Resource
    private ExecPython execPython;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        RespPageBean bean = stockWeeklyLineEmaResultService.getBeanlistByPage(page, size, "");
        return bean;
    }

    @GetMapping("/byweekly")
    public List<StockWeeklyLineEmaResult> getBeanlistByWeeklyId(@RequestParam Integer wid) {
        return stockWeeklyLineEmaResultService.getBeanlistByWeeklyId(wid);
    }

    @PutMapping("/runBuyRule")
    public RespBean runBuyRule(@RequestParam Integer emaid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY_RULE, String.valueOf(emaid)});
        return RespBean.ok("正在执行买入策略，请耐心等待!");
    }

    @PutMapping("/newema")
    public RespBean newEma(@RequestParam Integer wid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_A_EMA, String.valueOf(wid)});
        return RespBean.ok("正在生成EMA数据线，请耐心等待!");
    }

}
