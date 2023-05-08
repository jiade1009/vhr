package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockQtProfit
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 21日 20:41
 * @version:    : 1.0
 */

public class StockQtProfit {
    private Integer id;

    /**
    * 股票池id
    */
    private Integer holdId;

    /**
    * 盈亏调研日期
    */
    private String dateResearch;

    /**
    * 股票代码
    */
    private String code;

    /**
    * 收盘价
    */
    private Double priceClose;

    /**
    * 持股数
    */
    private Integer amountHold;

    /**
    * 总市值
    */
    private Double total;

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

    public String getDateResearch() {
        return dateResearch;
    }

    public void setDateResearch(String dateResearch) {
        this.dateResearch = dateResearch;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(Double priceClose) {
        this.priceClose = priceClose;
    }

    public Integer getAmountHold() {
        return amountHold;
    }

    public void setAmountHold(Integer amountHold) {
        this.amountHold = amountHold;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
}