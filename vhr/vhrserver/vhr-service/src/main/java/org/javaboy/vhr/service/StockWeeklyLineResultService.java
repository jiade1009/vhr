package org.javaboy.vhr.service;

import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockBasicInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.javaboy.vhr.model.StockWeeklyLineResult;
import org.javaboy.vhr.mapper.StockWeeklyLineResultMapper;

import java.util.List;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineResultService
 * @datetime : 2022年 10月 12日 15:12
 * @version: : 1.0
 */

@Service
public class StockWeeklyLineResultService {

    @Resource
    private StockWeeklyLineResultMapper stockWeeklyLineResultMapper;


    public int deleteByPrimaryKey(Integer id) {
        return stockWeeklyLineResultMapper.deleteByPrimaryKey(id);
    }


    public int insert(StockWeeklyLineResult record) {
        return stockWeeklyLineResultMapper.insert(record);
    }


    public int insertSelective(StockWeeklyLineResult record) {
        return stockWeeklyLineResultMapper.insertSelective(record);
    }


    public StockWeeklyLineResult selectByPrimaryKey(Integer id) {
        return stockWeeklyLineResultMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(StockWeeklyLineResult record) {
        return stockWeeklyLineResultMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(StockWeeklyLineResult record) {
        return stockWeeklyLineResultMapper.updateByPrimaryKey(record);
    }

    public RespPageBean getBeanlistByPage(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockWeeklyLineResult> data = stockWeeklyLineResultMapper.getBeanlistByPage(page, size);
        Long total = stockWeeklyLineResultMapper.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
