package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockMessageConf;
import org.javaboy.vhr.service.StockMessageConfService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : sam
 * @description : 股票消息发送配置，确定谁接收，接收什么类型消息，以什么形式发送消息
 * @datetime : 2022-10-30 10:03:57
 * @version: : 1.0
 */

@RestController
@RequestMapping("/stock/messageconf")
public class StockMessageConfController {
    /**
     * 服务对象
     */
    @Resource
    private StockMessageConfService stockMessageConfService;

    @GetMapping("/")
    public RespPageBean getBeanlistByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          StockMessageConf bean) {
        RespPageBean result = stockMessageConfService.getBeanlistByBeanAndPage(page, size, bean);
        return result;
    }

    @PostMapping("/")
    public RespBean addBean(@RequestBody StockMessageConf bean) {
        // 可能传递多个 empid，因此采用批量保存多个ben的接口
        if (stockMessageConfService.batchInsert(bean) == bean.getEmpids().length) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteByEid(@PathVariable Integer id) {
        if (stockMessageConfService.deleteByPrimaryKey(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PutMapping("/")
    public RespBean updateBean(@RequestBody StockMessageConf bean) {
        if (stockMessageConfService.updateByPrimaryKeySelective(bean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
