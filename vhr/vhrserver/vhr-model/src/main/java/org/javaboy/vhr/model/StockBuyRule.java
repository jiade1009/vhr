package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @author : sam
 * @ClassName : StockBuyRule
 * @description : TODO
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
    private Integer timeMarketOption;

    /**
     * 策略周期，单位为周
     */
    private Integer rulePeriod;

    /**
     * 成交量额度
     */
    private Integer turnoverLimit;

    /**
     * 成交量额度是否必要，0否，1是
     */
    private Integer turnoverLimitOption;

    /**
     * 收敛度，最大EMA75/当前ema18<conver_limit
     */
    private Double converLimit;

    /**
     * 收敛度是否必要，0否，1是
     */
    private Integer converLimitOption;

    /**
     * 下跌幅度,最低价/最高价<=shock_limit
     */
    private Double shockLimit;

    /**
     * 下跌幅度是否必要，0否，1是
     */
    private Integer shockLimitOption;

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

    public Integer getTimeMarketOption() {
        return timeMarketOption;
    }

    public void setTimeMarketOption(Integer timeMarketOption) {
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

    public Integer getTurnoverLimitOption() {
        return turnoverLimitOption;
    }

    public void setTurnoverLimitOption(Integer turnoverLimitOption) {
        this.turnoverLimitOption = turnoverLimitOption;
    }

    public Double getConverLimit() {
        return converLimit;
    }

    public void setConverLimit(Double converLimit) {
        this.converLimit = converLimit;
    }

    public Integer getConverLimitOption() {
        return converLimitOption;
    }

    public void setConverLimitOption(Integer converLimitOption) {
        this.converLimitOption = converLimitOption;
    }

    public Double getShockLimit() {
        return shockLimit;
    }

    public void setShockLimit(Double shockLimit) {
        this.shockLimit = shockLimit;
    }

    public Integer getShockLimitOption() {
        return shockLimitOption;
    }

    public void setShockLimitOption(Integer shockLimitOption) {
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
}