package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : UStockWeeklyLineEmaResult
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

public class UStockWeeklyLineEmaResult {
    private Integer id;

    /**
    * ema_result创建时间
    */
    private Date timeCreate;

    /**
    * ema_result创建成功时间
    */
    private Date timeEnd;

    /**
    * ema生成状态，0成功，-1失败
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

    /**
    * ema数据运行买入规则开始时间
    */
    private Date timeRunBegin;

    /**
    * ema数据运行买入规则结束时间
    */
    private Date timeRunEnd;

    /**
    * ema运行买入规则状态，0未运行，1运行成功，-1运行失败
    */
    private Integer runStatus;

    /**
    * ema运行买入规则结果备注
    */
    private String runStatusDesc;

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

    public Date getTimeRunBegin() {
        return timeRunBegin;
    }

    public void setTimeRunBegin(Date timeRunBegin) {
        this.timeRunBegin = timeRunBegin;
    }

    public Date getTimeRunEnd() {
        return timeRunEnd;
    }

    public void setTimeRunEnd(Date timeRunEnd) {
        this.timeRunEnd = timeRunEnd;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public String getRunStatusDesc() {
        return runStatusDesc;
    }

    public void setRunStatusDesc(String runStatusDesc) {
        this.runStatusDesc = runStatusDesc;
    }
}