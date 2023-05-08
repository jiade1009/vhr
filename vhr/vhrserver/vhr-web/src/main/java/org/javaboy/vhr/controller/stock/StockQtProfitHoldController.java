package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockQtProfitHold;
import org.javaboy.vhr.service.StockQtProfitHoldService;
import org.javaboy.vhr.utils.POIUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (stock_profit)表控制层
 *
 * @author sam
 */
@RestController
public class StockQtProfitHoldController {
    /**
     * 服务对象
     */
    @Resource
    private StockQtProfitHoldService stockQtProfitHoldService;


    // --------QT预测方法定义 begin -----------
    @GetMapping("/qtstock/profithold/")
    public RespPageBean getQtBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        if (size==-1){
            page=null;
            size=null;
        }
        return stockQtProfitHoldService.getBeanlistByPage(page, size, keyword);
    }

    @GetMapping("/qtstock/profithold/export")
    public ResponseEntity<byte[]> exportData() {
        List<StockQtProfitHold> list = (List<StockQtProfitHold>) stockQtProfitHoldService.getBeanlistByPage(null, null, "").getData();
        return POIUtils.stockQtProfitHold2Excel(list);
    }
}
