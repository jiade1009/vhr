package org.javaboy.vhr.model;

import org.javaboy.vhr.model.util.MessageType;
import org.javaboy.vhr.model.util.SendType;

import java.util.Date;

/**
 * @ClassName   : StockMessageLog
 * @description : TODO
 * @author      : sam
 * @datetime    : 2022年 10月 26日 17:27
 * @version:    : 1.0
 */

public class StockMessageLog {
    private String msgid;

    /**
    * 员工id
    */
    private Integer empid;
    private Employee employee = new Employee();

    /**
    * 0发送中，1发送成功，2发送失败
    */
    private Integer status = 0;
    private String statusNote;

    /**
    * 路由键
    */
    private String routekey;

    /**
    * 交换机
    */
    private String exchange;

    /**
    * 重试次数
    */
    private Integer count = 0;

    /**
    * 第一次重试时间
    */
    private Date timeTry;

    private Date timeCreate;

    private Date timeUpdate;

    /**
    * 消息分类（信号发现、买入、卖出、巡检结果）
    */
    private Integer messageType;
    private String messageTypeNote;

    /**
    * 发送形式（0短信、1邮件、2微信服务通知）
    */
    private Integer sendType;
    private String sendTypeNote;
    // 消息内容
    private String content;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoutekey() {
        return routekey;
    }

    public void setRoutekey(String routekey) {
        this.routekey = routekey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTimeTry() {
        return timeTry;
    }

    public void setTimeTry(Date timeTry) {
        this.timeTry = timeTry;
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

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public String getStatusNote() {
        if (status==null) return "";
        switch (status) {
            case 0:
                return "发送中";
            case 1:
                return "成功";
            case 2:
                return "失败";
            default:
                return "未知";
        }
    }

    public String getMessageTypeNote() {
        if (messageType==null) return "";
        return MessageType.getName(messageType);
    }

    public String getSendTypeNote() {
        return SendType.getName(sendType);
    }
}