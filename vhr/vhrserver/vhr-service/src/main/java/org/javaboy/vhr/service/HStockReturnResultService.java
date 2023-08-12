package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.HStockReturnResultMapper;
import org.javaboy.vhr.model.HStockReturnResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : HStockReturnResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 06日 22:06
 * @version:    : 1.0
 */

@Service
public class HStockReturnResultService extends BaseService<HStockReturnResult, Integer> {

    @Resource
    private HStockReturnResultMapper hStockReturnResultMapper;

    public List<HStockReturnResult> getBeanlistByDateResearch(String dateResearch) {
        return hStockReturnResultMapper.getBeanlistByDateResearch(dateResearch);
    }

}
