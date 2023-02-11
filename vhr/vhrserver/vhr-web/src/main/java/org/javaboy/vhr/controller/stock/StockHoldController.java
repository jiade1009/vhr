package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockHold;
import org.javaboy.vhr.model.util.StockHoldStatus;
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

    /**
     * 关闭交易
     * @param holdId
     * @return
     */
    @PutMapping("/close")
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

    /**
     * 关闭交易
     * @return
     */
    @PutMapping("/pauseStocks")
    public RespBean pauseStocks() {
        return RespBean.error("股票状态不正确，无法关闭买入交易");
    }
}
