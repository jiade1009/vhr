package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author : sam
 * @ClassName : StockWeeklyLineEmaResult
 * @description : TODO
 * @datetime : 2022年 10月 19日 11:04
 * @version: : 1.0
 */

public class StockWeeklyLineEmaResult {
    private Integer id;

    /**
     * ema_result创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    /**
     * ema买入规则运行状态，0未运行，1运行成功，-1运行失败
     */
    private Integer runStatus;

    /**
     * ema买入规则运行结果备注信息
     */
    private String runStatusDesc;

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