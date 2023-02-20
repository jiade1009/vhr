
package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.StockMessageLogService;
import org.javaboy.vhr.utils.EncryptUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description : 统一对外的股票服务接口
 * @author      : sam
 * @datetime    : 2022-10-11 10:15:00
 * @version:    : 1.0
 */
@RestController
@RequestMapping("/rest/stock")
public class StockRestController {
    /**
     * 服务对象
     */

    @Resource
    private StockMessageLogService stockMessageLogService;
    @Resource
    private EncryptUtils encryptUtils;

    @PostMapping("/message/{method}")
    public RespBean addBeans(HttpServletRequest request, @PathVariable String method, @RequestBody Map<String, Object> maps) {
        /*Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            System.out.println(header+"=="+request.getHeader(header));
        }*/
        if (maps!=null) {
            Integer r = encryptUtils.checkSign(maps);
            if (r==0) {
                HashMap<String, Object> data = (HashMap<String, Object>) maps.get("data");
                List<String> hold_trade_id_list;
                switch (method) {
                    case "sign":
                        String white_stocks = (String) data.get("white_stock_list");
                        String buy_stocks = (String) data.get("buy_stock_list");
                        if (StringUtils.hasLength(white_stocks)) {
                            stockMessageLogService.insertSignalMessages(white_stocks, buy_stocks);
                        }
                        break;
                    case "uturn_sign":
                        List<String> uturn_stocks = (List<String>) data.get("list");
                        if (uturn_stocks!=null && uturn_stocks.size()>0) {
                            stockMessageLogService.insertUreturnSignalMessages(uturn_stocks);
                        }
                        break;
                    case "buy":
                        hold_trade_id_list = (List<String>) data.get("hold_trade_id_list");
                        if (hold_trade_id_list!=null && hold_trade_id_list.size()>0) {
                            stockMessageLogService.insertBuyHoldMessages(hold_trade_id_list);
                        }
                        break;
                    case "buy_result":
                        hold_trade_id_list = (List<String>) data.get("hold_trade_id_list");
                        if (hold_trade_id_list!=null && hold_trade_id_list.size()>0) {
                            stockMessageLogService.insertBuyHoldResultMessages(hold_trade_id_list);
                        }
                        break;
                    case "sell":
                        Integer holdTradeId = Integer.valueOf((String) data.get("hold_trade_id"));
                        stockMessageLogService.insertSellHoldMessages(holdTradeId);
                        break;
                    case "sell_result":
                        break;
                    case "revoke":
                        break;

                }
            } else {
                RespBean.error("签名验签失败", r);
            }
        }

        return RespBean.ok("success");
    }



}
