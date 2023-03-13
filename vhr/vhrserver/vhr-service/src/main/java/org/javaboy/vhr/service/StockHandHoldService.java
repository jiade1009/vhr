package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockHandHoldMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockHandHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockHandHoldService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 06日 20:09
 * @version:    : 1.0
 */

@Service
public class StockHandHoldService extends BaseService<StockHandHold, Integer> {

    @Resource
    private StockHandHoldMapper stockHandHoldMapper;


    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, Integer status) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockHandHold> data = stockHandHoldMapper.getBeanlistByPage(page, size, keywords, status);
        Long total = stockHandHoldMapper.getTotal(keywords, status);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

}
