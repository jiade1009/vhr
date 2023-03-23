package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @author : sam
 * @ClassName : StockProfitMonth
 * @description : TODO
 * @datetime : 2023年 03月 22日 14:12
 * @version: : 1.0
 */

public class StockProfitMonth {
    private Integer id;

    /**
     * 盈亏调研月份
     */
    private String monthResearch;

    /**
     * 月初始总市值
     */
    private Double totalBegin;

    /**
     * 盈亏值
     */
    private Double profit;

    /**
     * 盈亏率
     */
    private Double profitRate;

    /**
     * 更新时间
     */
    private Date timeUpdate;

    /**
     * 创建时间
     */
    private Date timeCreate;

    /**
     * 总市值
     */
    private Double totalEnd;

    /**
     * 盈利交易笔数
     */
    private Integer profitAmount;

    /**
     * 亏损交易笔数
     */
    private Integer lossAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonthResearch() {
        return monthResearch;
    }

    public void setMonthResearch(String monthResearch) {
        this.monthResearch = monthResearch;
    }

    public Double getTotalBegin() {
        return totalBegin;
    }

    public void setTotalBegin(Double totalBegin) {
        this.totalBegin = totalBegin;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Double profitRate) {
        this.profitRate = profitRate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Double getTotalEnd() {
        return totalEnd;
    }

    public void setTotalEnd(Double totalEnd) {
        this.totalEnd = totalEnd;
    }

    public Integer getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(Integer profitAmount) {
        this.profitAmount = profitAmount;
    }

    public Integer getLossAmount() {
        return lossAmount;
    }

    public void setLossAmount(Integer lossAmount) {
        this.lossAmount = lossAmount;
    }
}