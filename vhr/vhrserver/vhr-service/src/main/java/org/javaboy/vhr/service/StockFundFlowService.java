package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockFundFlowMapper;
import org.javaboy.vhr.model.DatabaseType;
import org.javaboy.vhr.model.StockFundFlow;
import org.javaboy.vhr.model.util.FundTradeType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/**
 * @ClassName   : StockFundFlowService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 12日 21:41
 * @version:    : 1.0
 */

@Service
public class StockFundFlowService extends BaseService<StockFundFlow, Integer> {

    @Resource
    private StockFundFlowMapper stockFundFlowMapper;
    @Resource
    private DatabaseTypeService databaseTypeService;

    @Transactional
    public int insert(StockFundFlow record) {
        int i = stockFundFlowMapper.insert(record);
//        需要更新 可用资金
        DatabaseType bean = databaseTypeService.getFundUsable();
        Double fundUsable = Double.valueOf(bean.getValue());
        FundTradeType type = FundTradeType.getFundTradeType(record.getTradeType());
        Double total = record.getAmount()*type.getFlag() + fundUsable;
        bean.setValue(total.toString());
        databaseTypeService.updateByPrimaryKey(bean);
        return i;
    }
}
