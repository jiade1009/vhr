package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.StockBuyRuleMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockBuyRule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockBuyRuleService
 * @description : TODO
 * @datetime : 2022年 10月 13日 22:02
 * @version: : 1.0
 */

@Service
public class StockBuyRuleService {

    @Resource
    private StockBuyRuleMapper stockBuyRuleMapper;


    public int deleteByPrimaryKey(Integer id) {
        return stockBuyRuleMapper.deleteByPrimaryKey(id);
    }


    public int insert(StockBuyRule record) {
        return stockBuyRuleMapper.insert(record);
    }


    public int insertSelective(StockBuyRule record) {
        return stockBuyRuleMapper.insertSelective(record);
    }


    public StockBuyRule selectByPrimaryKey(Integer id) {
        return stockBuyRuleMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(StockBuyRule record) {
        return stockBuyRuleMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(StockBuyRule record) {
        return stockBuyRuleMapper.updateByPrimaryKey(record);
    }

    public RespPageBean getBeanlistByPage(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockBuyRule> data = stockBuyRuleMapper.getBeanlistByPage(page, size);
        Long total = stockBuyRuleMapper.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
