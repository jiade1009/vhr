package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtHoldMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockQtHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtHoldService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 20日 20:37
 * @version:    : 1.0
 */

@Service
public class StockQtHoldService extends BaseService<StockQtHold, Integer> {

    @Resource
    private StockQtHoldMapper stockQtHoldMapper;

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, Integer status) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockQtHold> data = stockQtHoldMapper.getBeanlistByPage(page, size, keywords, status);
        Long total = stockQtHoldMapper.getTotal(keywords, status);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

}
