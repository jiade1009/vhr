package org.javaboy.vhr.model;

/**
 * @ClassName   : HStockBasicInfo
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 02月 20日 16:27
 * @version:    : 1.0
 */

public class HStockBasicInfo {
    private String code;

    private String name;

    private String datePublish;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(String datePublish) {
        this.datePublish = datePublish;
    }
}