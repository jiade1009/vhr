package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.UStockHoldMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.UStockHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : UStockHoldService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Service
public class UStockHoldService extends BaseService<UStockHold, Integer> {

    @Resource
    private UStockHoldMapper uStockHoldMapper;


    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, Integer status) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<UStockHold> data = uStockHoldMapper.getBeanlistByPage(page, size, keywords, status);
        Long total = uStockHoldMapper.getTotal(keywords, status);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

}
