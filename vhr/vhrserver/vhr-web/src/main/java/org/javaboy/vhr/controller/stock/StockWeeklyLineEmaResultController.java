package org.javaboy.vhr.controller.stock;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;
import org.javaboy.vhr.service.StockWeeklyLineEmaResultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (stock_weekly_line_ema_result)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/stock_weekly_line_ema_result")
public class StockWeeklyLineEmaResultController {
/**
* 服务对象
*/
@Resource
private StockWeeklyLineEmaResultService stockWeeklyLineEmaResultService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public StockWeeklyLineEmaResult selectOne(Integer id) {
return stockWeeklyLineEmaResultService.selectByPrimaryKey(id);
}

}
