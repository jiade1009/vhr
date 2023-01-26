package org.javaboy.vhr.model;

import org.javaboy.vhr.model.util.CommandType;

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
    private String commandDesc;

    /**
    * 执行结果（-1失败，0执行中，1成功）
    */
    private Integer status;

    /**
     * 生成方式，0手动，1自动
     */
    private Integer generateType;

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

    public Integer getGenerateType() {
        return generateType;
    }

    public void setGenerateType(Integer generateType) {
        this.generateType = generateType;
    }

    public String getCommandDesc() {
        CommandType type = CommandType.valueOf(this.command);
        String desc = "";
        switch (type) {
            case WEEKLY:
                desc = "周线数据生成";
                break;
            case WEEKLY_EMA:
                desc = "ema数据生成";
                break;
            case DAILY_REFRESH:
                desc = "收盘数据更新";
                break;
            case BUY:
                desc = "每日开盘买入";
                break;
            case BUY_RULE:
                desc = "买入策略运行";
                break;
            case CODE_REFRESH:
                desc = "股票代码刷新";
                break;
            case TRADE_DATE:
                desc = "交易日数据更新";
                break;
            case CJCX:
                desc = "成交信息查询";
                break;
        }
        return desc;
    }
}