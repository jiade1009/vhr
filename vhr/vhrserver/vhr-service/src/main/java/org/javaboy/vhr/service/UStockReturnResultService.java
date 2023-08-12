package org.javaboy.vhr.service;

import org.javaboy.vhr.base.BaseService;
import org.javaboy.vhr.mapper.UStockReturnResultMapper;
import org.javaboy.vhr.model.UStockReturnResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName   : UStockReturnResultService
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

@Service
public class UStockReturnResultService extends BaseService<UStockReturnResult, Integer> {

    @Resource
    private UStockReturnResultMapper uStockReturnResultMapper;

    public List<UStockReturnResult> getBeanlistByDateResearch(String dateResearch) {
        return uStockReturnResultMapper.getBeanlistByDateResearch(dateResearch);
    }

}
