package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : StockExecuteResult
 * @description : 股票每日定时操作结果
 * @author      : sam
 * @datetime    : 2023年 01月 04日 10:23
 * @version:    : 1.0
 */

/**
    * 每日定时操作执行结果表
    */
public class StockExecuteResult {
    private Integer id;

    /**
    * 执行任务日期，格式%Y%m%d
    */
    private String dateExec;

    /**
    * 任务命令
    */
    private String command;

    /**
    * 执行结果（-1失败，0执行中，1成功）
    */
    private Integer status;

    private Date timeCreate;

    private Date timeUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateExec() {
        return dateExec;
    }

    public void setDateExec(String dateExec) {
        this.dateExec = dateExec;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}