
package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description : TODO
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
        execPython.runPython(new String[]{"load_a_stock"});
        return RespBean.ok("正在重新更新数据，请耐心等待!");
    }

}
