package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockSubstepProfitMapper;
import org.javaboy.vhr.mapper.StockSubstepTradeMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockSubstepProfit;
import org.javaboy.vhr.model.StockSubstepTrade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName   : StockSubstepProfitService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 06日 08:33
 * @version:    : 1.0
 */

@Service
public class StockSubstepProfitService extends BaseService<StockSubstepProfit, Integer> {

    @Resource
    private StockSubstepProfitMapper stockSubstepProfitMapper;
    @Resource
    private StockSubstepTradeMapper stockSubstepTradeMapper;

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, Integer status) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockSubstepProfit> data = stockSubstepProfitMapper.getBeanlistByPage(page, size, keywords, status);
        Long total = stockSubstepProfitMapper.getTotal(keywords, status);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    /**
     *
     * 新增股票分级止盈记录
     * 1.根据code，判断数据库中是否有存在未结束的分级记录，如果存在，关闭
     * 2.新增新的分级止盈记录(交易价格和交易数均为0，只是方便后续交易新增是，获取其上次的交易类型，方便成本价的计算）
     * 3.根据分级止盈记录，补充对应的交易记录信息
     * @param vo
     * @return
     */
    @Transactional
    public Integer addProfit(StockSubstepProfit vo) {
        StockSubstepProfit dbVO = stockSubstepProfitMapper.getRunningByCode(vo.getCode());
        if (dbVO != null) {
            dbVO.setStatus(0);
            dbVO.setTimeUpdate(new Date());
            stockSubstepProfitMapper.updateByPrimaryKey(dbVO);
        }
        vo.setTimeCreate(new Date());
        vo.setTimeUpdate(vo.getTimeCreate());
        stockSubstepProfitMapper.insert(vo);
        // 新增交易记录
        StockSubstepTrade trade = new StockSubstepTrade();
        trade.setSubstepProfitId(vo.getId());
        trade.setCode(vo.getCode());
//                trade.setPrice(0d);
//                trade.setAmount(0);
        trade.setPrice(vo.getPriceCost());
        trade.setAmount(vo.getAmountCost());
        trade.setType(vo.getLastTradeType());
        stockSubstepTradeMapper.insert(trade);
        return 1;
    }

    /**
     *
     * 批量新增股票分级止盈记录
     * 1.根据code，判断数据库中是否有存在未结束的分级记录，如果存在，关闭
     * 2.新增新的分级止盈记录(交易价格和交易数均为0，只是方便后续交易新增是，获取其上次的交易类型，方便成本价的计算）
     * 3.根据分级止盈记录，补充对应的交易记录信息
     * @param list
     * @return
     */
    @Transactional
    public Integer addList(List<StockSubstepProfit> list) {
        List<StockSubstepProfit> addList = new ArrayList<>();
        if (list.size() > 0) {
            // 关闭存量的订单
            for (StockSubstepProfit vo: list) {
                addProfit(vo);
            }
            return  1;
        } else {
            return 0;
        }
    }

    @Transactional
    public int deleteById(Integer id) {
        stockSubstepTradeMapper.deleteByProfitId(id);
        stockSubstepProfitMapper.deleteByPrimaryKey(id);
        return 1;
    }
}
