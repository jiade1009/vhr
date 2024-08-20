package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockSubstepProfit;
import org.javaboy.vhr.model.util.ProfitStage;
import org.javaboy.vhr.service.StockSubstepProfitService;
import org.javaboy.vhr.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (stock_substep_profit)表控制层
 *
 * @author xxxxx
 */
@RestController
public class StockSubstepProfitController {
    /**
     * 服务对象
     */
    @Resource
    private StockSubstepProfitService stockSubstepProfitService;

    @GetMapping("/stock/substepprofit/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword, Integer status) {
        return stockSubstepProfitService.getBeanlistByPage(page, size, keyword, status);
    }

    @PostMapping("/stock/substepprofit/")
    public RespBean addBean(@RequestBody StockSubstepProfit bean) {
        Date now = new Date();
        bean.setTimeCreate(now);
        bean.setTimeUpdate(now);
        int result = stockSubstepProfitService.insert(bean);
        if (result == 1) {
            return RespBean.ok("添加成功", bean);
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/stock/substepprofit/")
    public RespBean updateBean(@RequestBody StockSubstepProfit bean) {
        bean.setTimeUpdate(new Date());
        if (stockSubstepProfitService.updateByPrimaryKey(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @PostMapping("/stock/substepprofit/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<StockSubstepProfit> list = POIUtils.excel2SubstepProfit(file);
        if (list != null && list.size() > 0) {
            stockSubstepProfitService.addProfits(list);
            return RespBean.ok("上传成功");
        } else {
            return RespBean.error("上传失败，文件格式不正确");
        }
    }

    @GetMapping("/stock/substepprofit/stagetype")
    public RespBean getStageType() {
        List list = ProfitStage.getNames();
        Map<String, Object> map = new HashMap<>();
        map.put("stage_type", list);
        return RespBean.ok("加载成功", map, false);
    }
    @DeleteMapping("/stock/substepprofit/{id}")
    public RespBean deleteByEid(@PathVariable Integer id) {
        if (stockSubstepProfitService.deleteById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
