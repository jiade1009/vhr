package org.javaboy.vhr.model;

import java.util.Map;

/**
 * @author : sam
 * @description : Rabbitmq 发送消息体对象
 * @datetime : 2022-10-27 19:53:52
 * @version: : 1.0
 */

public class RabbitBean {

    private Employee employee;

    private Map dataMap;

    /**
     * 消息分类（例如：0信号发现、1买入、2卖出）
     */
    private Integer messageType;

    /**
     * 发送形式（0短信、1邮件、2微信服务通知）
     */
    private Integer sendType;

    public RabbitBean(Employee employee) {
        new RabbitBean(employee, null, null, null);
    }

    public RabbitBean(Employee employee, Integer messageType, Integer sendType) {
        new RabbitBean(employee, messageType, sendType, null);
    }

    public RabbitBean(Employee employee, Integer messageType, Integer sendType, Map dataMap) {
        this.employee = employee;
        this.dataMap = dataMap;
        this.messageType = messageType;
        this.sendType = sendType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Map getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map dataMap) {
        this.dataMap = dataMap;
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
}
