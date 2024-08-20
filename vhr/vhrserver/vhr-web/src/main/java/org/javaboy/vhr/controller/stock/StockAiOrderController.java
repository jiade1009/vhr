package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockAiOrder;
import org.javaboy.vhr.service.StockAiOrderService;
import org.javaboy.vhr.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * (stock_buy_rule)表控制层
 *
 * @author sam
 */
@RestController
public class StockAiOrderController {
    /**
     * 服务对象
     */
    @Resource
    private StockAiOrderService stockAiOrderService;

    @GetMapping("/stock/aiorder/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword, Integer status) {
        System.out.println("........." + status);
        return stockAiOrderService.getBeanlistByPage(page, size, keyword, status);
    }

    @PostMapping("/stock/aiorder/")
    public RespBean addBean(@RequestBody StockAiOrder bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        bean.setTimeUpdate(now);
        int result = stockAiOrderService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/stock/aiorder/")
    public RespBean updateBean(@RequestBody StockAiOrder bean) {
        bean.setTimeUpdate(new Date());
        if (stockAiOrderService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @PostMapping("/stock/aiorder/import")
    public RespBean importData(MultipartFile file, @RequestParam("orderSource") String orderSource) throws IOException {
        List<StockAiOrder> list = POIUtils.excel2AiOrder(file, orderSource);
        if (list != null && list.size() > 0) {
            stockAiOrderService.addOrders(list, orderSource);
            return RespBean.ok("上传成功");
        } else {
            return RespBean.error("上传失败，文件格式不正确");
        }

    }

}
