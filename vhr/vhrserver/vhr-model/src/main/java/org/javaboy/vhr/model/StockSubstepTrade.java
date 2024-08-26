package org.javaboy.vhr.model;

import org.javaboy.vhr.model.util.TradeType;

import java.util.Date;

/**
 * @ClassName   : StockSubstepTrade
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 06日 08:33
 * @version:    : 1.0
 */

/**
    * 分级交易表
    */
public class StockSubstepTrade {
    private Integer id;

    private Date timeCreate = new Date();

    private Date timeUpdate = new Date();


    /**
    * 股票代码
    */
    private String code;
    private String name;

    /**
    * 成交价
    */
    private Double price;

    /**
    * 成交数量
    */
    private Integer amount;

    /**
    * 交易类型（加仓0、止盈1、止损2、减仓3、清仓4、调仓5）
    */
    private Integer type;
    private String typeNote;

    /**
    * 交易日期
    */
    private String dateTrade;

    /**
    * 分级止盈id
    */
    private Integer substepProfitId;

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

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }
    public String getTypeNote() {
        return TradeType.getName(type);
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDateTrade() {
        return dateTrade;
    }

    public void setDateTrade(String dateTrade) {
        this.dateTrade = dateTrade;
    }

    public Integer getSubstepProfitId() {
        return substepProfitId;
    }

    public void setSubstepProfitId(Integer substepProfitId) {
        this.substepProfitId = substepProfitId;
    }
}