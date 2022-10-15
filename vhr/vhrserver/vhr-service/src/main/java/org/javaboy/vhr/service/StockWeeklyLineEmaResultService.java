package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.StockWeeklyLineEmaResultMapper;
import org.javaboy.vhr.model.StockWeeklyLineEmaResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineEmaResultService
 * @datetime : 2022年 10月 12日 16:33
 * @version: : 1.0
 */

@Service
public class StockWeeklyLineEmaResultService {

    @Resource
    private StockWeeklyLineEmaResultMapper stockWeeklyLineEmaResultMapper;


    public int deleteByPrimaryKey(Integer id) {
        return stockWeeklyLineEmaResultMapper.deleteByPrimaryKey(id);
    }


    public int insert(StockWeeklyLineEmaResult record) {
        return stockWeeklyLineEmaResultMapper.insert(record);
    }


    public int insertSelective(StockWeeklyLineEmaResult record) {
        return stockWeeklyLineEmaResultMapper.insertSelective(record);
    }


    public StockWeeklyLineEmaResult selectByPrimaryKey(Integer id) {
        return stockWeeklyLineEmaResultMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(StockWeeklyLineEmaResult record) {
        return stockWeeklyLineEmaResultMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(StockWeeklyLineEmaResult record) {
        return stockWeeklyLineEmaResultMapper.updateByPrimaryKey(record);
    }

}

