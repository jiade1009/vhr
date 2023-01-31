package org.javaboy.vhr.model;

import java.io.Serializable;

/**
 * @author : sam
 * @ClassName : StockMessageConf
 * @description : 股票消息发送配置，确定谁接收，接收什么类型消息，以什么形式发送消息
 * @datetime : 2022年 10月 30日 10:00
 * @version: : 1.0
 */

public class StockMessageConf implements Serializable {

    private static final long serialVersionUID = 6789204026313043198L;

    /**
     * 员工id
     */
    private Integer empid;

    /**
     * 消息分类（信号发现、买入、卖出、巡检结果），采用二进制机制保存，例如1000，表示接收信号发现消息
     */
    private String messageType;

    /**
     * 发送形式（0短信、1邮件、2微信服务通知），采用二进制机制表示
     */
    private String sendType;

    /**
     * 是否生效
     */
    private Boolean status = true;

    private Integer[] empids; //用于在新增消息配置时，传递多个empid数组

    private Employee employee = new Employee();

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer[] getEmpids() {
        return empids;
    }

    public void setEmpids(Integer[] empids) {
        this.empids = empids;
    }
}