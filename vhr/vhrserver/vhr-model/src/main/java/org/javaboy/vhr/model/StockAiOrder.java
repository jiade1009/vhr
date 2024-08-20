package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.javaboy.vhr.model.util.AiOrderProcess;

import java.util.Date;

/**
 * @ClassName   : StockAiOrder
 * @description : TODO
 * @author      : sam
 * @datetime    : 2024年 05月 02日 17:40
 * @version:    : 1.0
 */

public class StockAiOrder {
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
    * 策略类型，0：到价买出
    */
    private Integer strategyType;

    /**
    * 状态，0：已结束、1：监控中
    */
    private Integer status;

    /**
    * 进度，0：未触发、1：已触发1笔、2停止
    */
    private Integer process;

    /**
    * 触发条件，eg:当股价 ≤ 2.95触发委托;股价≥9.00元
    */
    private String triggerCondition;

    /**
    * 委托价格
    */
    private String priceEntrust;

    /**
    * 委托数量
    */
    private String amountEntrust;

    /**
    * 自动委托，0：否，1：是
    */
    private Integer autoEntrust;

    /**
    * 开始日期
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBegin;

    /**
    * 截止日期
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateExpire;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 来源备注
    */
    private String sourceNote;

    /**
    * 订单来源：国泰君安
    */
    private String orderSource;

    /**
    * 系统备注
    */
    private String note;

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

    public Integer getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Integer strategyType) {
        this.strategyType = strategyType;
    }

    public Integer getStatus() {
        return status;
    }
    public String getStatusNote() {
        return status==1?"监控中":"已结束";
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProcess() {
        return process;
    }
    public String getProcessNote() {
        return AiOrderProcess.getName(process);
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getTriggerCondition() {
        return triggerCondition;
    }

    public void setTriggerCondition(String triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public String getPriceEntrust() {
        return priceEntrust;
    }

    public void setPriceEntrust(String priceEntrust) {
        this.priceEntrust = priceEntrust;
    }

    public String getAmountEntrust() {
        return amountEntrust;
    }

    public void setAmountEntrust(String amountEntrust) {
        this.amountEntrust = amountEntrust;
    }

    public Integer getAutoEntrust() {
        return autoEntrust;
    }

    public void setAutoEntrust(Integer autoEntrust) {
        this.autoEntrust = autoEntrust;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSourceNote() {
        return sourceNote;
    }

    public void setSourceNote(String sourceNote) {
        this.sourceNote = sourceNote;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}