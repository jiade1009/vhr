package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockQtHoldTrade
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 04月 25日 22:54
 * @version:    : 1.0
 */

public class StockQtHoldTrade {
    private Integer id;

    private Integer holdId;

    private String code;

    /**
    * 委托数量
    */
    private Integer amount;

    /**
    * 交易类型：0买入，1卖出，2买入撤销，3卖出撤销，4分红配送
    */
    private Integer tradeType;

    /**
    * 交易备注
    */
    private String tradeNote;

    /**
    * 委托合同编号
    */
    private String orderid;

    /**
    * 委托/撤单状态信息
    */
    private String message;

    /**
    * 委托/撤单状态，1结束，0执行中，-1失败
    */
    private Integer status;

    /**
    * 投资备注
    */
    private String note;

    /**
    * 任务/指令编号
    */
    private String taskid;

    /**
    * 任务/指令状态，0 未知,1 等待,2 提交中,3 执行中,4 暂停,5 撤销中,6 异常撤销中,7 完成,8 已撤,9 打回,10  异常终止,11  放弃，目前用于组合交易中，放弃补单,12  强制终止
    */
    private Integer taskstatus;
    private String taskstatusNote;

    /**
    * 任务/指令状态信息
    */
    private String taskmsg;

    /**
    * 任务进度
    */
    private String taskpro;

    /**
    * 订单编号
    */
    private String ordernum;

    /**
    * 创建时间
    */
    private Date timeCreate;

    /**
    * 更新时间
    */
    private Date timeUpdate;

    private Double price;

    /**
    * 查询结果计数器，默认为0
    */
    private Integer counter;

    /**
    * 委托价格类型：1最新价、2对手价、3指定价、B1买1价、B2买2价、S1卖1价、S2卖2价
    */
    private String priceType;

    /**
    * 成交价格
    */
    private Double cjPrice;

    /**
    * 成交数量
    */
    private Integer cjAmount;

    /**
    * 成交时间
    */
    private String cjTime;

    /**
    * 成交手续费
    */
    private Double cjFee;

    /**
    * 成交金额
    */
    private Double cjTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHoldId() {
        return holdId;
    }

    public void setHoldId(Integer holdId) {
        this.holdId = holdId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeNote() {
        return tradeNote;
    }

    public void setTradeNote(String tradeNote) {
        this.tradeNote = tradeNote;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public Integer getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getTaskmsg() {
        return taskmsg;
    }

    public void setTaskmsg(String taskmsg) {
        this.taskmsg = taskmsg;
    }

    public String getTaskpro() {
        return taskpro;
    }

    public void setTaskpro(String taskpro) {
        this.taskpro = taskpro;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Double getCjPrice() {
        return cjPrice;
    }

    public void setCjPrice(Double cjPrice) {
        this.cjPrice = cjPrice;
    }

    public Integer getCjAmount() {
        return cjAmount;
    }

    public void setCjAmount(Integer cjAmount) {
        this.cjAmount = cjAmount;
    }

    public String getCjTime() {
        return cjTime;
    }

    public void setCjTime(String cjTime) {
        this.cjTime = cjTime;
    }

    public Double getCjFee() {
        return cjFee;
    }

    public void setCjFee(Double cjFee) {
        this.cjFee = cjFee;
    }

    public Double getCjTotal() {
        return cjTotal;
    }

    public void setCjTotal(Double cjTotal) {
        this.cjTotal = cjTotal;
    }

    public String getTaskstatusNote() {
        if (taskstatus==null) return "";
        switch (taskstatus) {
            case 1:
                return "等待";
            case 2:
                return "提交中";
            case 3:
                return "执行中";
            case 4:
                return "暂停";
            case 5:
                return "撤销中";
            case 6:
                return "异常撤销中";
            case 7:
                return "完成";
            case 8:
                return "已撤";
            case 9:
                return "打回";
            case 10:
                return "异常终止";
            case 11:
                return "放弃，目前用于组合交易中，放弃补单";
            case 12:
                return "强制终止";
            default:
                return "未知";
        }
    }

    public String getStatusNote() {
        if (status==null) return "";
        switch (status) {
            case 0:
                return "执行中";
            case 1:
                return "结束";
            case -1:
                return "失败";
            default:
                return "未知";
        }
    }
}