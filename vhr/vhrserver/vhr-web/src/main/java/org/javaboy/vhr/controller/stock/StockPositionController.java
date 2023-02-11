package org.javaboy.vhr.controller.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.config.BaseConstants;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.util.StockPositionVO;
import org.javaboy.vhr.pythonutil.ExecPython;
import org.javaboy.vhr.service.StockHoldService;
import org.javaboy.vhr.utils.DateUtils;
import org.javaboy.vhr.utils.StockPositionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @description : 股票持仓情况
 * @author      : sam
 * @datetime    : 2022-10-20 21:58:51
 * @version:    : 1.0
 */

@RestController
@RequestMapping("/stock/position")
public class StockPositionController {
    /**
     * 服务对象
     */
    @Resource
    private StockHoldService stockHoldService;
    @Resource
    private ExecPython execPython;
    private Pattern p = Pattern.compile("\\[(.*?)\\]");

    @GetMapping("/")
    public RespBean cctj() {
        String now = DateUtils.formatDate(new Date(), DateUtils.yyyyMMdd);
        List<String> responseList = execPython.runPython(new String[]{BaseConstants.PY_API_RUN_A_CCTJ, now});
        ObjectMapper mapper = new ObjectMapper();
        List<StockPositionVO> list = new ArrayList<>();
        if (responseList.size()>0) {
            String content = responseList.get(0);
            list = StockPositionUtils.getStockPositionList(content);
        }

        return RespBean.ok("", list, false);
    }

}
