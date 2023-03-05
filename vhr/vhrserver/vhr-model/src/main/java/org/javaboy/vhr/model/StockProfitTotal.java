package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockProfitTotal
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 03日 10:57
 * @version:    : 1.0
 */

public class StockProfitTotal {
    private Integer id;

    /**
    * 盈亏调研日期
    */
    private String dateResearch;

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

    private Double totalProfit;  //总盈亏

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateResearch() {
        return dateResearch;
    }

    public void setDateResearch(String dateResearch) {
        this.dateResearch = dateResearch;
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

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }
}