package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockFundFlow;
import org.javaboy.vhr.model.util.FundTradeType;
import org.javaboy.vhr.service.StockFundFlowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * (stock_fund_flow)表控制层
 *
 * @author sam
 */
@RestController
public class StockFundFlowController {
    /**
     * 服务对象
     */
    @Resource
    private StockFundFlowService stockFundFlowService;

    @GetMapping("/stock/fundflow/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return stockFundFlowService.getBeanlistByPage(page, size, "");
    }

    @PostMapping("/stock/fundflow/")
    public RespBean addBean(@RequestBody StockFundFlow bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        System.out.println(bean.getTradeType());
        Integer tradeType = bean.getTradeType();
        FundTradeType type = FundTradeType.getFundTradeType(tradeType);
        bean.setIncomeExpense(type.getFlag());  // 根据FundTradeType的flag字段，判断是收入还是支出
        int result = stockFundFlowService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/stock/fundflow/")
    public RespBean updateBean(@RequestBody StockFundFlow bean) {
        if (stockFundFlowService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @GetMapping("/stock/fundtradetype")
    public List<Map> getFundTradeType() {
        List<Map> bean = new ArrayList<>();
        for (FundTradeType vo: FundTradeType.values()) {
            Map map = new HashMap<String, String>();
            map.put("id", vo.getIndex());
            map.put("name", vo.getName());
            bean.add(map);
        }
        return bean;
    }
}
