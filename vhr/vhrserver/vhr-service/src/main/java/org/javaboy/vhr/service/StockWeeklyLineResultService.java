package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.StockWeeklyLineResultMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockWeeklyLineResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * @description : 获取自动生成，且ema生成状态为生成成功的周线数据
     * @param dateWeeklyResearch: 周线生成日 yyyy
     * @param generateType: 0手动，1自动生成
     * @param emaStatus : 0未生成，1生成成功，2生成失败
     */

    public List<StockWeeklyLineResult> getBeanListByPro(String dateWeeklyResearch, Integer generateType, Integer emaStatus) {
        return stockWeeklyLineResultMapper.getBeanListByPro(dateWeeklyResearch, generateType, emaStatus);
    }
}
