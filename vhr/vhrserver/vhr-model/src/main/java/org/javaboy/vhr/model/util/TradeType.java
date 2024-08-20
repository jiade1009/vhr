package org.javaboy.vhr.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sam
 * @ClassName : TradeType
 * @description : 交易类型
 * @datetime : 2024年 08月 07日 16:55
 * @version: : 1.0
 */
public enum TradeType {
    BUY(0,"加仓"),
    PROFIT(1,"止盈"),
    LOSS(2,"止损"),
    REDUCE(3,"降仓"),
    SELLOFF(4,"清仓"),
    ADJUST(5,"调仓");

    // 成员变量
    private String name;
    private int index;

    TradeType(int index, String name) {
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
        for (TradeType c : TradeType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    public static int getIndex(String name) {
        for (TradeType c : TradeType.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return -1;
    }

    public static TradeType getTradeType(int index) {
        for (TradeType c : TradeType.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }
    public static TradeType getTradeTypeByName(String name) {
        for (TradeType c : TradeType.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public static List getNames() {
        List names = new ArrayList();
        for (TradeType c : TradeType.values()) {
            names.add(c.name);
        }
        return names;
    }

}
