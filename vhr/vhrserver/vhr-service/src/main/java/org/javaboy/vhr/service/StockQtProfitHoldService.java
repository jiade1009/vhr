package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockQtProfitHoldMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockQtProfitHold;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : StockQtProfitHoldService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 24日 21:51
 * @version:    : 1.0
 */

@Service
public class StockQtProfitHoldService extends BaseService<StockQtProfitHold, Integer> {

    @Resource
    private StockQtProfitHoldMapper stockQtProfitHoldMapper;

    /**
     * 获取包括股票加入方式字段（直接或者回头草）
     * @param page
     * @param size
     * @param keywords
     * @return
     */
    public RespPageBean getExtendBeanlistByPage(Integer page, Integer size, String keywords) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockQtProfitHold> data = stockQtProfitHoldMapper.getExtendBeanlistByPage(page, size, keywords);
        Long total = stockQtProfitHoldMapper.getTotal(keywords);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
