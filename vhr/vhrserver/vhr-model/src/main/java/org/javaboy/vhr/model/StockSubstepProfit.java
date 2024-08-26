package org.javaboy.vhr.model;

import org.javaboy.vhr.model.util.ProfitStage;
import org.javaboy.vhr.model.util.TradeType;

import java.util.Date;

/**
 * @ClassName   : StockSubstepProfit
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 08月 06日 08:33
 * @version:    : 1.0
 */

/**
    * 分级止盈表
    */
public class StockSubstepProfit {
    private Integer id;

    private Date timeCreate = new Date();

    private Date timeUpdate = new Date();

    /**
    * 股票名称
    */
    private String name;

    /**
    * 股票代码
    */
    private String code;

    /**
    * 止盈阶段0：未止盈、1：p3阶段、2：p5阶段、3：p8阶段、4：p10阶段、5：p1051阶段、6：p1052阶段、7：p1053阶段、8：终极
    */
    private Integer profitStage = 0;

    /**
    * 状态，0：已结束、1：运行中
    */
    private Integer status = 1;

    /**
    * 成本价
    */
    private Double priceCost = 0d;

    /**
    * 成本股票数，即统计入成本中的股票数
    */
    private Integer amountCost = 0;

    /**
    * 可用股票数
    */
    private Integer amountAble = 0;

    /**
     * 分级统计股票数，用于计算每次分级止盈的股票数
     */
    private Integer amountSubstep = 0;

    private Double priceP3 = 0d; //P3成交价格
    private Integer amountP3 = 0; // P3成交股票数

    private Double priceP5 = 0d; //P5成交价格
    private Integer amountP5 = 0; // P5成交股票数

    private Double priceP8 = 0d; //P8成交价格
    private Integer amountP8 = 0; // P8成交股票数

    private Double priceP10 = 0d; //P10成交价格
    private Integer amountP10 = 0; // P10成交股票数

    private Double priceP1051 = 0d; //P105-1阶段成交价格
    private Integer amountP1051 = 0; // P105-1阶段成交股票数

    private Double priceP1052 = 0d; //P105-2阶段成交价格
    private Integer amountP1052 = 0; // P105-2阶段成交股票数

    private Double priceP1053 = 0d; //P105-3阶段成交价格
    private Integer amountP1053 = 0; // P105-3阶段成交股票数

    private Integer lastTradeType = 0; //上次交易类型，默认是加仓
    private String lastTradeTypeNote;
    private Double priceStopLoss = 0d; //止损价格

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getProfitStage() {
        return profitStage;
    }

    public String getProfitStageNote() {
        if (profitStage==null) return "";
        return ProfitStage.getName(profitStage);
    }

    public void setProfitStage(Integer profitStage) {
        this.profitStage = profitStage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusNote() {
        return status==1?"运行中":"已结束";
    }

    public Double getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Double priceCost) {
        this.priceCost = priceCost;
    }

    public Integer getAmountCost() {
        return amountCost;
    }

    public void setAmountCost(Integer amountCost) {
        this.amountCost = amountCost;
    }

    public Integer getAmountAble() {
        return amountAble;
    }

    public void setAmountAble(Integer amountAble) {
        this.amountAble = amountAble;
    }

    public Integer getAmountSubstep() {
        return amountSubstep;
    }

    public void setAmountSubstep(Integer amountSubstep) {
        this.amountSubstep = amountSubstep;
    }

    public Double getPriceP3() {
        return priceP3;
    }

    public void setPriceP3(Double priceP3) {
        this.priceP3 = priceP3;
    }

    public Integer getAmountP3() {
        return amountP3;
    }

    public void setAmountP3(Integer amountP3) {
        this.amountP3 = amountP3;
    }

    public Double getPriceP5() {
        return priceP5;
    }

    public void setPriceP5(Double priceP5) {
        this.priceP5 = priceP5;
    }

    public Integer getAmountP5() {
        return amountP5;
    }

    public void setAmountP5(Integer amountP5) {
        this.amountP5 = amountP5;
    }

    public Double getPriceP8() {
        return priceP8;
    }

    public void setPriceP8(Double priceP8) {
        this.priceP8 = priceP8;
    }

    public Integer getAmountP8() {
        return amountP8;
    }

    public void setAmountP8(Integer amountP8) {
        this.amountP8 = amountP8;
    }

    public Double getPriceP10() {
        return priceP10;
    }

    public void setPriceP10(Double priceP10) {
        this.priceP10 = priceP10;
    }

    public Integer getAmountP10() {
        return amountP10;
    }

    public void setAmountP10(Integer amountP10) {
        this.amountP10 = amountP10;
    }

    public Double getPriceP1051() {
        return priceP1051;
    }

    public void setPriceP1051(Double priceP1051) {
        this.priceP1051 = priceP1051;
    }

    public Integer getAmountP1051() {
        return amountP1051;
    }

    public void setAmountP1051(Integer amountP1051) {
        this.amountP1051 = amountP1051;
    }

    public Double getPriceP1052() {
        return priceP1052;
    }

    public void setPriceP1052(Double priceP1052) {
        this.priceP1052 = priceP1052;
    }

    public Integer getAmountP1052() {
        return amountP1052;
    }

    public void setAmountP1052(Integer amountP1052) {
        this.amountP1052 = amountP1052;
    }

    public Double getPriceP1053() {
        return priceP1053;
    }

    public void setPriceP1053(Double priceP1053) {
        this.priceP1053 = priceP1053;
    }

    public Integer getAmountP1053() {
        return amountP1053;
    }

    public void setAmountP1053(Integer amountP1053) {
        this.amountP1053 = amountP1053;
    }

    public Integer getLastTradeType() {
        return lastTradeType;
    }
    public String getLastTradeTypeNote() {
        return TradeType.getName(lastTradeType);
    }

    public void setLastTradeType(Integer lastTradeType) {
        this.lastTradeType = lastTradeType;
    }

    public Double getPriceStopLoss() {
        return priceStopLoss;
    }

    public void setPriceStopLoss(Double priceStopLoss) {
        this.priceStopLoss = priceStopLoss;
    }

}