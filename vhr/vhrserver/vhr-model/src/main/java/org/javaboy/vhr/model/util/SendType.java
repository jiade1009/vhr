package org.javaboy.vhr.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sam
 * @ClassName : MessageType
 * @description : 通知类型
 * @datetime : 2022年 11月 23日 16:55
 * @version: : 1.0
 */
public enum SendType {
    MESSAGE(0,"短信"),
    EMAIL(1,"邮件"),
    WX_NOTICE(2,"微信通知");

    // 成员变量
    private String name;
    private int index;

    SendType(int index, String name) {
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
        for (SendType c : SendType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static List getNames() {
        List names = new ArrayList();
        for (SendType c : SendType.values()) {
            names.add(c.name);
        }
        return names;
    }
}
