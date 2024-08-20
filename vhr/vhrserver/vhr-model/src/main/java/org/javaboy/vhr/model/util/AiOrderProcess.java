package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : AiStrategyType
 * @description : Ai智能订单的进度：0：未触发、1：已触发1笔、2停止
 * @datetime : 2024年 05月 03日 15:05
 * @version: : 1.0
 */
public enum AiOrderProcess {
    NOTRIGGER(0, "未触发"),
    TRIGGER(1, "已触发1笔"),
    OVER(2, "停止");

    private String name;
    private int index;

    AiOrderProcess(int index, String name) {
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
        for (AiOrderProcess c : AiOrderProcess.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static int getIndex(String name) {
        for (AiOrderProcess c : AiOrderProcess.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return -1;
    }
}
