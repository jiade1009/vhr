package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : AiStrategyType
 * @description : Ai智能订单的卖出策略：0：到价卖出
 * @datetime : 2024年 05月 03日 15:05
 * @version: : 1.0
 */
public enum AiStrategyType {
    SELL_AT_PRICE(0, "到价卖出");

    private String name;
    private int index;

    AiStrategyType(int index, String name) {
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
        for (AiStrategyType c : AiStrategyType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static int getIndex(String name) {
        for (AiStrategyType c : AiStrategyType.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return -1;
    }
}
