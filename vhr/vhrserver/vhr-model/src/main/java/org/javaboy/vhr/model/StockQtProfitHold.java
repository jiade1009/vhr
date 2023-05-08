package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockQtProfitHold
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 24日 21:51
 * @version:    : 1.0
 */

public class StockQtProfitHold {
    private Integer id;

    /**
    * 股票池id
    */
    private Integer holdId;

    /**
    * 股票代码
    */
    private String code;

    /**
    * 成本
    */
    private Double totalBegin;

    /**
    * 创建时间
    */
    private Date timeCreate;

    /**
    * 盈亏值
    */
    private Double profit;

    /**
    * 盈亏率
    */
    private Double profitRate;

    /**
    * 购买时间
    */
    private Date timeBuy;

    /**
    * 清仓时间
    */
    private Date timeSell;

    /**
    * 持股天数
    */
    private Integer holdDays;

    private StockBasicInfo stockBasicInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHoldId() {
        return holdId;
    }

    public void setHoldId(Integer holdId) {
        this.holdId = holdId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getTotalBegin() {
        return totalBegin;
    }

    public void setTotalBegin(Double totalBegin) {
        this.totalBegin = totalBegin;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
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

    public Date getTimeBuy() {
        return timeBuy;
    }

    public void setTimeBuy(Date timeBuy) {
        this.timeBuy = timeBuy;
    }

    public Date getTimeSell() {
        return timeSell;
    }

    public void setTimeSell(Date timeSell) {
        this.timeSell = timeSell;
    }

    public Integer getHoldDays() {
        return holdDays;
    }

    public void setHoldDays(Integer holdDays) {
        this.holdDays = holdDays;
    }

    public StockBasicInfo getStockBasicInfo() {
        return stockBasicInfo;
    }

    public void setStockBasicInfo(StockBasicInfo stockBasicInfo) {
        this.stockBasicInfo = stockBasicInfo;
    }
}