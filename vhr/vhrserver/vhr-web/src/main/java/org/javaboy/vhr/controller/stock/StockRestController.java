
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
                if (method.equals("sign")) { //发现信号
                    String white_stocks = (String) data.get("white_stock_list");
                    String buy_stocks = (String) data.get("buy_stock_list");
                    if (StringUtils.hasLength(white_stocks)) {
                        stockMessageLogService.insertSignalMessages(white_stocks, buy_stocks);
                    }
                } else if (method.equals("buy")) { //股票买入
                    List<String> hold_trade_id_list = (List<String>) data.get("hold_trade_id_list");
                    if (hold_trade_id_list!=null && hold_trade_id_list.size()>0) {
                        stockMessageLogService.insertBuyHoldMessages(hold_trade_id_list);
                    }
                } else if (method.equals("buy_result")) { //股票买入结果
                    List<String> hold_trade_id_list = (List<String>) data.get("hold_trade_id_list");
                    if (hold_trade_id_list!=null && hold_trade_id_list.size()>0) {
                        stockMessageLogService.insertBuyHoldResultMessages(hold_trade_id_list);
                    }
                }

            } else {
                RespBean.error("签名验签失败", r);
            }
        }

        return RespBean.ok("success");
    }



}
