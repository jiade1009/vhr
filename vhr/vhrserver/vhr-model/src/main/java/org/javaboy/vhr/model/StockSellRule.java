package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockSellRule
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 12月 05日 17:04
 * @version:    : 1.0
 */

public class StockSellRule {
    private Integer id;

    /**
    * 第一次止盈卖出比例，默认0.5
    */
    private Double sellRatio;

    /**
    * 止盈阶段计算公式比例
    */
    private Double p1Ratio;

    /**
    * 止盈阶段计算公式比例
    */
    private Double p2Ratio;

    /**
    * 止盈阶段计算公式比例
    */
    private Double p3Ratio;

    /**
    * 止盈阶段计算公式比例
    */
    private Double p4Ratio;

    /**
    * 止盈阶段计算公式比例
    */
    private Double p5Ratio;

    /**
    * 止盈阶段卖出价格计算公式比例
    */
    private Double sp1Ratio;

    /**
    * 止盈阶段卖出价格计算公式比例
    */
    private Double sp2Ratio;

    /**
    * 止盈阶段卖出价格计算公式比例
    */
    private Double sp3Ratio;

    /**
    * 止盈阶段卖出价格计算公式比例
    */
    private Double sp4Ratio;

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

    public Double getSellRatio() {
        return sellRatio;
    }

    public void setSellRatio(Double sellRatio) {
        this.sellRatio = sellRatio;
    }

    public Double getP1Ratio() {
        return p1Ratio;
    }

    public void setP1Ratio(Double p1Ratio) {
        this.p1Ratio = p1Ratio;
    }

    public Double getP2Ratio() {
        return p2Ratio;
    }

    public void setP2Ratio(Double p2Ratio) {
        this.p2Ratio = p2Ratio;
    }

    public Double getP3Ratio() {
        return p3Ratio;
    }

    public void setP3Ratio(Double p3Ratio) {
        this.p3Ratio = p3Ratio;
    }

    public Double getP4Ratio() {
        return p4Ratio;
    }

    public void setP4Ratio(Double p4Ratio) {
        this.p4Ratio = p4Ratio;
    }

    public Double getP5Ratio() {
        return p5Ratio;
    }

    public void setP5Ratio(Double p5Ratio) {
        this.p5Ratio = p5Ratio;
    }

    public Double getSp1Ratio() {
        return sp1Ratio;
    }

    public void setSp1Ratio(Double sp1Ratio) {
        this.sp1Ratio = sp1Ratio;
    }

    public Double getSp2Ratio() {
        return sp2Ratio;
    }

    public void setSp2Ratio(Double sp2Ratio) {
        this.sp2Ratio = sp2Ratio;
    }

    public Double getSp3Ratio() {
        return sp3Ratio;
    }

    public void setSp3Ratio(Double sp3Ratio) {
        this.sp3Ratio = sp3Ratio;
    }

    public Double getSp4Ratio() {
        return sp4Ratio;
    }

    public void setSp4Ratio(Double sp4Ratio) {
        this.sp4Ratio = sp4Ratio;
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