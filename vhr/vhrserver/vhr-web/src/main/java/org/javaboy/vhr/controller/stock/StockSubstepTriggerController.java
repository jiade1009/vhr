package org.javaboy.vhr.controller.stock;
import org.javaboy.vhr.model.StockSubstepTrigger;
import org.javaboy.vhr.service.StockSubstepTriggerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (stock_substep_trigger)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/stock_substep_trigger")
public class StockSubstepTriggerController {
/**
* 服务对象
*/
@Resource
private StockSubstepTriggerService stockSubstepTriggerService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public StockSubstepTrigger selectOne(Integer id) {
return stockSubstepTriggerService.selectByPrimaryKey(id);
}

}
