package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockMessageLogMapper;
import org.javaboy.vhr.model.StockMessageLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : sam
 * @ClassName : StockMessageLogService
 * @description : TODO
 * @datetime : 2022年 10月 26日 17:27
 * @version: : 1.0
 */

@Service
public class StockMessageLogService extends BaseService<StockMessageLog, Integer> {

    @Resource
    private StockMessageLogMapper stockMessageLogMapper;

    public int insert(StockMessageLog record) {
        return stockMessageLogMapper.insert(record);
    }

    public Integer updateStockMessageLogStatus(String msgId, Integer status) {
        return stockMessageLogMapper.updateStockMessageLogStatus(msgId, status);
    }

    public List<StockMessageLog> getStockMessageLogByStatus() {
        return stockMessageLogMapper.getStockMessageLogByStatus();
    }

    public Integer updateCount(String msgId, Date date) {
        return stockMessageLogMapper.updateCount(msgId,date);
    }

}
