package org.javaboy.vhr.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : sam
 * @description : Rabbitmq 发送消息体对象
 * @datetime : 2022-10-27 19:53:52
 * @version: : 1.0
 */

public class RabbitBean implements Serializable {

    private static final long serialVersionUID = 1283902452775205894L;

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

    private String content; //消息内容

    public RabbitBean(Employee employee) {
        this(employee, "", null, null, null);
    }

    public RabbitBean(Employee employee, String content, Integer messageType, Integer sendType) {
        this(employee, content, messageType, sendType, null);
    }

    public RabbitBean(Employee employee, String content, Integer messageType, Integer sendType, Map dataMap) {
        this.employee = employee;
        this.content = content;
        this.dataMap = dataMap;
        this.messageType = messageType;
        this.sendType = sendType;
    }

    @Override
    public String toString() {
        String json = "RabbitBean{" +
                "messageType=" + messageType +
                ", sendType=" + sendType +
                ", content='" + content + '\'';
        if (this.employee != null) {
            json += ", employee=" + employee.toString();
        }
        if (this.dataMap !=null) {
            Iterator iter = dataMap.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                json += ", " + entry.getKey().toString() + "='" + entry.getValue().toString() + '\'' ;
            }
        }
        json += '}';
        return json;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
