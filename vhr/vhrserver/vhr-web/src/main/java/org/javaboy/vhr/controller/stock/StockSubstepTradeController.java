package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockSubstepTrade;
import org.javaboy.vhr.model.util.TradeType;
import org.javaboy.vhr.service.StockSubstepTradeService;
import org.javaboy.vhr.utils.NumberUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (stock_substep_trade)表控制层
 *
 * @author xxxxx
 */
@RestController
public class StockSubstepTradeController {
    /**
     * 服务对象
     *
     */
    @Resource
    private StockSubstepTradeService stockSubstepTradeService;

    @GetMapping("/stock/substeptrade/{stepId}")
    public RespPageBean getBeanlistByWeeklyId(@PathVariable Integer stepId) {
        List<StockSubstepTrade> data = stockSubstepTradeService.getBeanlistByStepId(stepId);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        return bean;
    }

    @GetMapping("/stock/substeptrade/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        RespPageBean bean = stockSubstepTradeService.getBeanlistByPage(page, size, keyword);
        return bean;
    }

    @PostMapping("/stock/substeptrade/record")
    public RespBean addBean(@RequestBody Map<String, Object> map) {
        String recordText = (String) map.get("recordText");
        // 在Windows系统中，换行通常表示为\r\n（回车符后跟换行符），而在Unix/Linux系统和Mac OS X（10.9之前）中，换行仅表示为\n
        String[] lines = recordText.split("\\r?\\n");
        List<StockSubstepTrade> list = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
//           交易日期 代码	名称	成交价	成交量	操作
            String[] tradeInfo = line.split("\\s+");
            StockSubstepTrade trade = new StockSubstepTrade();
            trade.setDateTrade(tradeInfo[0]);
            trade.setCode(tradeInfo[1]);
            trade.setName(tradeInfo[2]);
            trade.setPrice(NumberUtils.ceil(tradeInfo[3], 2));
            trade.setAmount(Double.valueOf(tradeInfo[4]).intValue());
            trade.setType(TradeType.getIndex(tradeInfo[5]));
            list.add(trade);
        }
        int result = stockSubstepTradeService.addBeans(list);
        if (result == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

}
