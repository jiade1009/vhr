package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : MessageType
 * @description : 消息通知类型
 * @datetime : 2022年 11月 23日 16:55
 * @version: : 1.0
 */
public enum MessageType {
    SIGN(0,"信号发现"),
    BUY(1,"股票买入"),
    SELL(2,"股票卖出");
    // 成员变量
    private String name;
    private int index;

    MessageType(int index, String name) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (MessageType c : MessageType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
}
