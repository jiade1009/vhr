package org.javaboy.vhr.model;

import org.javaboy.vhr.model.util.FundTradeType;

import java.util.Date;

/**
 * @ClassName   : StockFundFlow
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 12日 21:41
 * @version:    : 1.0
 */

public class StockFundFlow {
    private Integer id;

    /**
    * 操作金额
    */
    private Double amount;

    /**
    * 1.资金转入2.资金转出3.股票买入4.股票卖出5.股票红利（股票除息）6.利息归本7.股票交税
    */
    private Integer tradeType;
    private String tradeTypeNote;

    /**
    * 收支类型（进1，出-1）
    */
    private Integer incomeExpense;

    private Date timeCreate;

    /**
    * 备注
    */
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getIncomeExpense() {
        return incomeExpense;
    }

    public void setIncomeExpense(Integer incomeExpense) {
        this.incomeExpense = incomeExpense;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getNote() {
        return note;
    }

    public String getTradeTypeNote() {
        return FundTradeType.getName(this.tradeType);
    }

    public void setNote(String note) {
        this.note = note;
    }

}