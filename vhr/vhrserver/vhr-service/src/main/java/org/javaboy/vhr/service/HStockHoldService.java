package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockHoldMapper;
import org.javaboy.vhr.model.HStockHold;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : HStockHoldService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 22日 21:48
 * @version:    : 1.0
 */

@Service
public class HStockHoldService extends BaseService<HStockHold, Integer> {

    @Resource
    private HStockHoldMapper hStockHoldMapper;

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, Integer status) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<HStockHold> data = hStockHoldMapper.getBeanlistByPage(page, size, keywords, status);
        Long total = hStockHoldMapper.getTotal(keywords, status);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

}
