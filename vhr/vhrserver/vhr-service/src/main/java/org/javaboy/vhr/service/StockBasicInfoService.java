package org.javaboy.vhr.service;

import org.javaboy.vhr.model.DatabaseType;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.javaboy.vhr.model.StockBasicInfo;
import org.javaboy.vhr.mapper.StockBasicInfoMapper;

import java.util.List;

@Service
public class StockBasicInfoService {

    @Resource
    private StockBasicInfoMapper stockBasicInfoMapper;


    public int deleteByPrimaryKey(String code) {
        return stockBasicInfoMapper.deleteByPrimaryKey(code);
    }


    public int insert(StockBasicInfo record) {
        return stockBasicInfoMapper.insert(record);
    }


    public int insertSelective(StockBasicInfo record) {
        return stockBasicInfoMapper.insertSelective(record);
    }


    public StockBasicInfo selectByPrimaryKey(String code) {
        return stockBasicInfoMapper.selectByPrimaryKey(code);
    }


    public int updateByPrimaryKeySelective(StockBasicInfo record) {
        return stockBasicInfoMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(StockBasicInfo record) {
        return stockBasicInfoMapper.updateByPrimaryKey(record);
    }

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockBasicInfo> data = stockBasicInfoMapper.getBeanlistByPage(page, size, keywords);
        Long total = stockBasicInfoMapper.getTotal(keywords);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}

