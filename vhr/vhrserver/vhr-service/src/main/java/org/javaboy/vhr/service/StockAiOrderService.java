package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.StockAiOrderMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.StockAiOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName   : StockFundFlowService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 12日 21:41
 * @version:    : 1.0
 */

@Service
public class StockAiOrderService extends BaseService<StockAiOrder, Integer> {

    @Resource
    private StockAiOrderMapper stockAiOrderMapper;

    public RespPageBean getBeanlistByPage(Integer page, Integer size, String keywords, Integer status) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<StockAiOrder> data = stockAiOrderMapper.getBeanlistByPage(page, size, keywords, status);
        Long total = stockAiOrderMapper.getTotal(keywords, status);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
    /**
     *
     * 批量新增vo，先批量关闭存量的vo。然后处理本批次的订单，如果order_no已经存在，那么更新最新的记录信息，
     * 如果未存在order_no，则新增vo
     * @param list
     * @return
     */
    public Integer addOrders(List<StockAiOrder> list, String orderSource) {
        List<StockAiOrder> addList = new ArrayList<>();
        if (list.size() > 0) {
            // 关闭存量的订单
            stockAiOrderMapper.closeStatus(orderSource);
            for (StockAiOrder order: list) {
                StockAiOrder tmp = stockAiOrderMapper.getByOrderNo(order.getOrderNo(), orderSource);
                if (tmp != null) {
                    order.setId(tmp.getId());
                    stockAiOrderMapper.updateByPrimaryKey(order);
                } else {
                    addList.add(order);
                }
            }
            int addAmount = addList.size();
            if (addAmount > 0) {
                stockAiOrderMapper.addOrders(addList);
            }
            return  addAmount;
        } else {
            return 0;
        }

    }

}
