package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @author : sam
 * @ClassName : StockBuyRule
 * @datetime : 2022年 10月 13日 22:02
 * @version: : 1.0
 */

public class StockBuyRule {
    private Integer id;

    /**
     * 上市时长要求
     */
    private Integer timeMarket;

    /**
     * 上市时长是否必要，0否，1是
     */
    private Boolean timeMarketOption;

    /**
     * 策略周期，单位为周
     */
    private Integer rulePeriod;

    /**
     * 成交金额额度
     */
    private Integer turnoverLimit;

    /**
     * 成交金额额度是否必要，0否，1是
     */
    private Boolean turnoverLimitOption;

    /**
     * 收敛度，最大EMA75/当前ema18<conver_limit
     */
    private Double converLimit;

    /**
     * 收敛度是否必要，0否，1是
     */
    private Boolean converLimitOption;

    /**
     * 下跌幅度,最低价/最高价<=shock_limit
     */
    private Double shockLimit;

    /**
     * 下跌幅度是否必要，0否，1是
     */
    private Boolean shockLimitOption;

    /**
     * 买入价限价，买入价格/昨日的EMA_75的价格，默认1.03
     */
    private Double buyPriceLimit;

    /**
     * 买入价限价开关，0否，1是
     */
    private Boolean buyPriceLimitOption;

    private Date timeCreate;

    private Date timeUpdate;

    /**
     * 2结束，1运行，0草稿
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeMarket() {
        return timeMarket;
    }

    public void setTimeMarket(Integer timeMarket) {
        this.timeMarket = timeMarket;
    }

    public Boolean getTimeMarketOption() {
        return timeMarketOption;
    }

    public void setTimeMarketOption(Boolean timeMarketOption) {
        this.timeMarketOption = timeMarketOption;
    }

    public Integer getRulePeriod() {
        return rulePeriod;
    }

    public void setRulePeriod(Integer rulePeriod) {
        this.rulePeriod = rulePeriod;
    }

    public Integer getTurnoverLimit() {
        return turnoverLimit;
    }

    public void setTurnoverLimit(Integer turnoverLimit) {
        this.turnoverLimit = turnoverLimit;
    }

    public Boolean getTurnoverLimitOption() {
        return turnoverLimitOption;
    }

    public void setTurnoverLimitOption(Boolean turnoverLimitOption) {
        this.turnoverLimitOption = turnoverLimitOption;
    }

    public Double getConverLimit() {
        return converLimit;
    }

    public void setConverLimit(Double converLimit) {
        this.converLimit = converLimit;
    }

    public Boolean getConverLimitOption() {
        return converLimitOption;
    }

    public void setConverLimitOption(Boolean converLimitOption) {
        this.converLimitOption = converLimitOption;
    }

    public Double getShockLimit() {
        return shockLimit;
    }

    public void setShockLimit(Double shockLimit) {
        this.shockLimit = shockLimit;
    }

    public Boolean getShockLimitOption() {
        return shockLimitOption;
    }

    public void setShockLimitOption(Boolean shockLimitOption) {
        this.shockLimitOption = shockLimitOption;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getBuyPriceLimit() {
        return buyPriceLimit;
    }

    public void setBuyPriceLimit(Double buyPriceLimit) {
        this.buyPriceLimit = buyPriceLimit;
    }

    public Boolean getBuyPriceLimitOption() {
        return buyPriceLimitOption;
    }

    public void setBuyPriceLimitOption(Boolean buyPriceLimitOption) {
        this.buyPriceLimitOption = buyPriceLimitOption;
    }
}