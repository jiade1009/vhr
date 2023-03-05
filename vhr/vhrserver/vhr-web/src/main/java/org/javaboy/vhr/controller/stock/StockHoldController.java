package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.HStockHold;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockHold;
import org.javaboy.vhr.model.util.StockHoldStatus;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.HStockHoldService;
import org.javaboy.vhr.service.StockHoldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description : 股票池管理
 * @author      : sam
 * @datetime    : 2022-10-20 21:58:51
 * @version:    : 1.0
 */
@RestController
public class StockHoldController {
    /**
     * 服务对象
     */
    @Resource
    private StockHoldService stockHoldService;
    @Resource
    private HStockHoldService hStockHoldService;
    @Resource
    private ExecPython execPython;

    @GetMapping("/stock/hold/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword,
                                          Integer status) {
        RespPageBean bean = stockHoldService.getBeanlistByPage(page, size, keyword, status);
        return bean;
    }

    @GetMapping("/stock/holdstatus")
    public List<Map> getHoldStatus() {
        List<Map> bean = new ArrayList<>();
        for (StockHoldStatus status: StockHoldStatus.values()) {
            Map map = new HashMap<String, String>();
            map.put("id", status.getIndex());
            map.put("name", status.getName());
            bean.add(map);
        }
        return bean;
    }

    @PutMapping("/stock/hold/runBuy")
    public RespBean runBuy(@RequestParam Integer holdId) {
        execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_BUY, String.valueOf(holdId)});
        return RespBean.ok("正在执行股票买入操作，请耐心等待!");
    }

    /**
     * 关闭交易
     * @param holdId
     * @return
     */
    @PutMapping("/stock/hold/close")
    public RespBean closeStock(@RequestParam Integer holdId) {
        StockHold hold = stockHoldService.selectByPrimaryKey(holdId);
        if (hold.getStatus()==0) {
            hold.setStatus(StockHoldStatus.FINISH.getIndex());
            stockHoldService.updateByPrimaryKey(hold);
            return RespBean.ok("操作成功");
        } else {
            return RespBean.error("股票状态不正确，无法关闭买入交易");
        }
    }


    // --------H股方法定义 begin -----------
    @GetMapping("/hstock/hold/")
    public RespPageBean getHBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                           String keyword,
                                           Integer status) {
        RespPageBean bean = hStockHoldService.getBeanlistByPage(page, size, keyword, status);
        return bean;
    }

    /**
     * 关闭交易
     * @param holdId
     * @return
     */
    @PutMapping("/hstock/hold/close")
    public RespBean closeHStock(@RequestParam Integer holdId) {
        HStockHold hold = hStockHoldService.selectByPrimaryKey(holdId);
        if (hold.getStatus()==0) {
            hold.setStatus(StockHoldStatus.FINISH.getIndex());
            hStockHoldService.updateByPrimaryKey(hold);
            return RespBean.ok("操作成功");
        } else {
            return RespBean.error("股票状态不正确，无法关闭买入交易");
        }
    }
}
