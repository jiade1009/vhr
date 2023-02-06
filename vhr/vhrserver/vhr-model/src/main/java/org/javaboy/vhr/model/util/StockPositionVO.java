package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : StockPositionVO
 * @description : TODO
 * @datetime : 2023年 02月 06日 09:25
 * @version: : 1.0
 */
public class StockPositionVO {

    // 代码
    private String code;
    // 股票名称
    private String name;
    // 持股数量
    private Integer amount;
    // 持股成本
    private Double cost;
    // 成本价
    private Double costPrice;
    // 盈亏
    private Double profitLoss;
    // 市值
    private Double marketValue;
    // 可用数
    private Integer usable;
    // 最新价
    private Double lastestPrice;
    // 当日盈亏
    private Double currentProfitLoss;
    // 当日涨幅(百分比）
    private Double currentIncrease;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(Double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Integer getUsable() {
        return usable;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }

    public Double getLastestPrice() {
        return lastestPrice;
    }

    public void setLastestPrice(Double lastestPrice) {
        this.lastestPrice = lastestPrice;
    }

    public Double getCurrentProfitLoss() {
        return currentProfitLoss;
    }

    public void setCurrentProfitLoss(Double currentProfitLoss) {
        this.currentProfitLoss = currentProfitLoss;
    }

    public Double getCurrentIncrease() {
        return currentIncrease;
    }

    public void setCurrentIncrease(Double currentIncrease) {
        this.currentIncrease = currentIncrease;
    }
}
