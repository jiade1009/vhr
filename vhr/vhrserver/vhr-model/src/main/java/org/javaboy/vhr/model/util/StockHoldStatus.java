package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : MessageType
 * @description : 股票池状态
 * @datetime : 2022年 11月 23日 16:55
 * @version: : 1.0
 */
public enum StockHoldStatus {
    NO_BOUGHT(0,"未购买"),
    BUYING(1,"购买中"),
    PAUSE_BUY(2,"暂停购买"),
    BOUGHT(3,"已购买"),
    SELLING(4,"卖出中"),
    PAUSE_SELL(5,"暂定卖出"),
    FINISH(6,"交易结束"),
    FAIL(7,"交易失败");

    // 成员变量
    private String name;
    private int index;

    StockHoldStatus(int index, String name) {
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
        for (StockHoldStatus c : StockHoldStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
}
