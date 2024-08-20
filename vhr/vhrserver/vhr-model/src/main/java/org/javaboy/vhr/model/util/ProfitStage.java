package org.javaboy.vhr.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sam
 * @ClassName : ProfitStage
 * @description : 止盈阶段
 * @datetime : 2024年 08月 07日 16:55
 * @version: : 1.0
 */
public enum ProfitStage {
    P0(0,"P0"),
    P3(1,"P3"),
    P5(2,"P5"),
    P8(3,"P8"),
    P10(4,"P10"),
    P1051(5,"P1051"),
    P1052(6,"P1052"),
    P1053(7,"P1053"),
    END(8,"END");

    // 成员变量
    private String name;
    private int index;

    ProfitStage(int index, String name) {
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
        for (ProfitStage c : ProfitStage.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    public static int getIndex(String name) {
        for (ProfitStage c : ProfitStage.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return -1;
    }

    public static ProfitStage getProfitStage(int index) {
        for (ProfitStage c : ProfitStage.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }

    public static List getNames() {
        List names = new ArrayList();
        for (ProfitStage c : ProfitStage.values()) {
            names.add(c.name);
        }
        return names;
    }

}
