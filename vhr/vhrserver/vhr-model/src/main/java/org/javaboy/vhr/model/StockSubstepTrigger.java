package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockSubstepTrigger
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 25日 01:48
 * @version:    : 1.0
 */

/**
    * 分级触发结果表
    */
public class StockSubstepTrigger {
    private Integer id;

    private Date timeCreate = new Date();

    /**
    * 股票代码
    */
    private String code;

    /**
    * 触发哪个止盈阶段，止盈阶段-1:清仓，0：未止盈、1：p3阶段、2：p5阶段、3：p8阶段、4：p10阶段、5：p105-1阶段、6：p105-2阶段、7：p105-3阶段、8：终极
    */
    private Integer stageTrigger;

    /**
    * 触发日期
    */
    private String dateTrigger;

    /**
    * 分级止盈id
    */
    private Integer substepProfitId;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStageTrigger() {
        return stageTrigger;
    }

    public void setStageTrigger(Integer stageTrigger) {
        this.stageTrigger = stageTrigger;
    }

    public String getDateTrigger() {
        return dateTrigger;
    }

    public void setDateTrigger(String dateTrigger) {
        this.dateTrigger = dateTrigger;
    }

    public Integer getSubstepProfitId() {
        return substepProfitId;
    }

    public void setSubstepProfitId(Integer substepProfitId) {
        this.substepProfitId = substepProfitId;
    }
}