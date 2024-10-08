package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.HStockWeeklyLineEmaResultService;
import org.javaboy.vhr.service.StockQtWeeklyLineEmaResultService;
import org.javaboy.vhr.service.StockWeeklyLineEmaResultService;
import org.javaboy.vhr.service.UStockWeeklyLineEmaResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (stock_weekly_line_ema_result)表控制层
 *
 * @author xxxxx
 */
@RestController
public class StockWeeklyLineEmaResultController {
    /**
     * 服务对象
     */
    @Resource
    private StockWeeklyLineEmaResultService stockWeeklyLineEmaResultService;
    @Resource
    private HStockWeeklyLineEmaResultService hStockWeeklyLineEmaResultService;
    @Resource
    private UStockWeeklyLineEmaResultService uStockWeeklyLineEmaResultService;
    @Resource
    private StockQtWeeklyLineEmaResultService stockQtWeeklyLineEmaResultService;
    @Resource
    private ExecPython execPython;

    @GetMapping("/stock/weeklylineemaresult/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        RespPageBean bean = stockWeeklyLineEmaResultService.getBeanlistByPage(page, size, "");
        return bean;
    }

    @GetMapping("/stock/weeklylineemaresult/byweekly")
    public List<StockWeeklyLineEmaResult> getBeanlistByWeeklyId(@RequestParam Integer wid) {
        return stockWeeklyLineEmaResultService.getBeanlistByWeeklyId(wid);
    }

    @PutMapping("/stock/weeklylineemaresult/runBuyRule")
    public RespBean runBuyRule(@RequestParam Integer emaid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY_RULE, String.valueOf(emaid)});
        return RespBean.ok("正在执行买入策略，请耐心等待!");
    }

    @PutMapping("/stock/weeklylineemaresult/newema")
    public RespBean newEma(@RequestParam Integer wid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_A_EMA, String.valueOf(wid)});
        return RespBean.ok("正在生成EMA数据线，请耐心等待!");
    }

    // ----------- H股配置 begin---------
    @GetMapping("/hstock/weeklylineemaresult/")
    public RespPageBean getHBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        RespPageBean bean = hStockWeeklyLineEmaResultService.getBeanlistByPage(page, size, "");
        return bean;
    }

    @GetMapping("/hstock/weeklylineemaresult/byweekly")
    public List<HStockWeeklyLineEmaResult> getHBeanlistByWeeklyId(@RequestParam Integer wid) {
        return hStockWeeklyLineEmaResultService.getBeanlistByWeeklyId(wid);
    }

    @PutMapping("/hstock/weeklylineemaresult/runBuyRule")
    public RespBean runHBuyRule(@RequestParam Integer emaid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_RUN_H_BUY_RULE, String.valueOf(emaid)});
        return RespBean.ok("正在执行买入策略，请耐心等待!");
    }

    @PutMapping("/hstock/weeklylineemaresult/newema")
    public RespBean newHEma(@RequestParam Integer wid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_H_EMA, String.valueOf(wid)});
        return RespBean.ok("正在生成EMA数据线，请耐心等待!");
    }

    // ----------- U股配置 begin---------
    @GetMapping("/ustock/weeklylineemaresult/")
    public RespPageBean getUBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
        RespPageBean bean = uStockWeeklyLineEmaResultService.getBeanlistByPage(page, size, "");
        return bean;
    }

    @GetMapping("/ustock/weeklylineemaresult/byweekly")
    public List<UStockWeeklyLineEmaResult> getUBeanlistByWeeklyId(@RequestParam Integer wid) {
        return uStockWeeklyLineEmaResultService.getBeanlistByWeeklyId(wid);
    }

    @PutMapping("/ustock/weeklylineemaresult/runBuyRule")
    public RespBean runUBuyRule(@RequestParam Integer emaid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_RUN_U_BUY_RULE, String.valueOf(emaid)});
        return RespBean.ok("正在执行买入策略，请耐心等待!");
    }

    @PutMapping("/ustock/weeklylineemaresult/newema")
    public RespBean newUEma(@RequestParam Integer wid) {
        execPython.runPython(new String[]{BaseConstants.PY_API_CREATE_U_EMA, String.valueOf(wid)});
        return RespBean.ok("正在生成EMA数据线，请耐心等待!");
    }

    // ----------- Qt股配置 begin---------
    @GetMapping("/qtstock/weeklylineemaresult/")
    public RespPageBean getQtBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
        RespPageBean bean = stockQtWeeklyLineEmaResultService.getBeanlistByPage(page, size, "");
        return bean;
    }

    @GetMapping("/qtstock/weeklylineemaresult/byweekly")
    public List<StockQtWeeklyLineEmaResult> getQtBeanlistByWeeklyId(@RequestParam Integer wid) {
        return stockQtWeeklyLineEmaResultService.getBeanlistByWeeklyId(wid);
    }

}
