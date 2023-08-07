package org.javaboy.vhr.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.model.util.MessageType;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
     * 消息分类
     */
    private MessageType messageType;

    /**
     * 发送形式（0短信、1邮件、2微信服务通知）
     */
    private Integer sendType;
    private String flag;

    private String content; //消息内容
    private String title; //消息标题

    public RabbitBean(Employee employee) {
        this(employee, "", "", null, null, null, "");
    }

    public RabbitBean(Employee employee, String content, String title, MessageType messageType, Integer sendType, String flag) {
        this(employee, content, title, messageType, sendType, null, flag);
    }

    public RabbitBean(Employee employee, String content, String title, MessageType messageType,
                      Integer sendType, Map dataMap, String flag) {
        this.employee = employee;
        this.content = content;
        this.title = title;
        this.dataMap = dataMap;
        this.messageType = messageType;
        this.sendType = sendType;
        this.flag = flag;
    }

    @Override
    public String toString() {
        String json = "RabbitBean{" +
                "messageType=" + messageType +
                ", sendType=" + sendType +
                ", flag=" + flag +
                ", title='" + title + '\'' +
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

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
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

    public static void main(String[] args) {
        LinkedHashMap<CommandType, Boolean> params = new LinkedHashMap();
        params.put(CommandType.WEEKLY_EMA, true);
        params.put(CommandType.WEEKLY, false);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json_str = mapper.writeValueAsString(params);
            System.out.println(json_str);
            Map map = mapper.readValue(json_str, Map.class);
            for (Object o : map.keySet()) {
                System.out.println(o.getClass());
                System.out.println(map.get(o).getClass());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
