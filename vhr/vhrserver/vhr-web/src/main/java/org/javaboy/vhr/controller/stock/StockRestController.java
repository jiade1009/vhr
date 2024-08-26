
package org.javaboy.vhr.controller.stock;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.StockMessageLogService;
import org.javaboy.vhr.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(StockRestController.class);
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
                String flag = "";
                String date_research = "";
                LOGGER.info("开始调用{}消息处理", method);
                switch (method) {
                    case "sign":
                        String white_stocks = (String) data.get("white_stock_list");
                        String buy_stocks = (String) data.get("buy_stock_list");
                        flag = (String) data.get("flag");
                        date_research = (String) data.get("date_research");
                        stockMessageLogService.insertSignalMessages(white_stocks, buy_stocks, date_research, flag);
                        break;
                    case "uturn_sign":
                        List<String> uturn_stocks = (List<String>) data.get("list");
                        List<String> enhance_uturn_stocks = (List<String>) data.get("enlist");  //加强回头草
                        flag = (String) data.get("flag");
                        date_research = (String) data.get("date_research");
                        stockMessageLogService.insertUreturnSignalMessages(uturn_stocks, enhance_uturn_stocks, date_research, flag);
                        break;
                    case "buy":
                        hold_trade_id_list = (List<String>) data.get("hold_trade_id_list");
                        flag = (String) data.get("flag");
                        date_research = (String) data.get("date_research");
                        if (hold_trade_id_list!=null && hold_trade_id_list.size()>0) {
                            stockMessageLogService.insertBuyHoldMessages(hold_trade_id_list, date_research, flag);
                        }
                        break;
                    case "buy_result":
                        hold_trade_id_list = (List<String>) data.get("hold_trade_id_list");
                        flag = (String) data.get("flag");
                        date_research = (String) data.get("date_research");
                        if (hold_trade_id_list!=null && hold_trade_id_list.size()>0) {
                            stockMessageLogService.insertBuyHoldResultMessages(hold_trade_id_list, date_research, flag);
                        }
                        break;
                    case "sell":
                        Integer holdTradeId = Integer.valueOf((String) data.get("hold_trade_id"));
                        flag = (String) data.get("flag");
                        date_research = (String) data.get("date_research");
                        stockMessageLogService.insertSellHoldMessages(holdTradeId, date_research, flag);
                        break;
                    case "ai_order":
                        List<String> ai_order_id_list = (List<String>) data.get("ai_order_id_list");
                        flag = (String) data.get("flag");
                        date_research = (String) data.get("date_research");
                        if (ai_order_id_list!=null && ai_order_id_list.size()>0) {
                            stockMessageLogService.insertAiOrderMessages(ai_order_id_list, date_research, flag);
                        }
                        break;
                    case "substep":
                        List<List<String>> substep_list = (List<List<String>>) data.get("substep_list");
                        flag = (String) data.get("flag");
                        date_research = (String) data.get("date_research");
                        if (substep_list!=null && substep_list.size()>0) {
                            stockMessageLogService.insertSubstepMessages(substep_list, date_research, flag);
                        }
                        break;
                    case "sell_result":
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
