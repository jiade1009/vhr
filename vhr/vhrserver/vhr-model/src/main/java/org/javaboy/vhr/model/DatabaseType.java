package org.javaboy.vhr.model;

import java.io.Serializable;

/**
 * basic data information
 */
public class DatabaseType implements Serializable {

    private static final long serialVersionUID = -1233635359115056864L;

    private Integer id;

    private String code;

    private String name;

    private String value;

    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 类型
     */
    private String type;
    private Integer sys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSys() {
        return sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
    }
}