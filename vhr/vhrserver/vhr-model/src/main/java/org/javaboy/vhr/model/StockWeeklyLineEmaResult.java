package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineEmaResult
 * @description :
 * @datetime : 2022年 10月 15日 08:11
 * @version: : 1.0
 */

public class StockWeeklyLineEmaResult {
    private Integer id;

    private Date timeCreate;

    private Date timeEnd;

    /**
     * ema生成状态，0成功，1失败
     */
    private Integer status;

    /**
     * ema生成结果备注信息
     */
    private String statusDesc;

    private Integer weeklyLineId;

    /**
     * ema数据线，例如18_25_75
     */
    private String emaIndex;

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

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getWeeklyLineId() {
        return weeklyLineId;
    }

    public void setWeeklyLineId(Integer weeklyLineId) {
        this.weeklyLineId = weeklyLineId;
    }

    public String getEmaIndex() {
        return emaIndex;
    }

    public void setEmaIndex(String emaIndex) {
        this.emaIndex = emaIndex;
    }
}