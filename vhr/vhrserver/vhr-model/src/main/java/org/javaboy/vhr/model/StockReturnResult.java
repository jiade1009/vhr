package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockReturnResult
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 06日 21:21
 * @version:    : 1.0
 */

/**
    * 回头草策略运行结果
    */
public class StockReturnResult {
    private Integer id;

    private Date timeCreate;

    private String dateResearch;

    private String resultDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getDateResearch() {
        return dateResearch;
    }

    public void setDateResearch(String dateResearch) {
        this.dateResearch = dateResearch;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}