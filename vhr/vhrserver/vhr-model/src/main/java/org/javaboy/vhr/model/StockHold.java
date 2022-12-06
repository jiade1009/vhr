package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockHold
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 10月 20日 21:50
 * @version:    : 1.0
 */

public class StockHold {
    private Integer id;

    /**
    * 股票代码
    */
    private String code;

    private Date timeCreate;

    private Date timeUpdate;

    /**
    * 状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束
    */
    private Integer status;

    private String statusNote;

    private String note;

    /**
    * 买入股票数
    */
    private Integer buyAmount;

    /**
    * 买入价格
    */
    private Double buyPrice;

    /**
    * 持有股票数
    */
    private Integer holdAmount;

    /**
    * 卖出阶段
    */
    private Integer sellStage;

    /**
    * 关联的ema_result_id
    */
    private Integer weeklyEmaResultId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getHoldAmount() {
        return holdAmount;
    }

    public void setHoldAmount(Integer holdAmount) {
        this.holdAmount = holdAmount;
    }

    public Integer getSellStage() {
        return sellStage;
    }

    public void setSellStage(Integer sellStage) {
        this.sellStage = sellStage;
    }

    public Integer getWeeklyEmaResultId() {
        return weeklyEmaResultId;
    }

    public void setWeeklyEmaResultId(Integer weeklyEmaResultId) {
        this.weeklyEmaResultId = weeklyEmaResultId;
    }

    public String getStatusNote() {
//        状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束
        if (status==null) return "";
        switch (status) {
            case 0:
                return "未购买";
            case 1:
                return "购买中";
            case 2:
                return "暂停购买";
            case 3:
                return "已购买";
            case 4:
                return "卖出中";
            case 5:
                return "暂定卖出";
            case 6:
                return "交易结束";
            default:
                return "未知";
        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}