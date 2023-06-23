package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @ClassName   : UStockWeeklyLineResult
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 06月 17日 09:35
 * @version:    : 1.0
 */

public class UStockWeeklyLineResult {
    private Integer id;

    /**
    * 复权方式
    */
    private String rehabilitationWay;

    /**
    * 周线调研日期
    */
    private String dateWeeklyResearch;

    /**
    * 周线调研起始日期
    */
    private String dateWeeklyStart;

    /**
    * 周线导入结果，0成功，1失败
    */
    private Integer result;

    /**
    * expma生成状态（0未生成、1已生成、2生成失败）
    */
    private Integer emaResult;

    /**
    * 运行开始时间
    */
    private Date timeCreate;

    /**
    * 运行结束时间
    */
    private Date timeEnd;

    /**
    * 生成方式，0手动，1自动
    */
    private Integer generateType;

    /**
    * 生成结果备注
    */
    private String resultDesc;

    /**
    * ema生成结果备注
    */
    private String emaResultDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRehabilitationWay() {
        return rehabilitationWay;
    }

    public void setRehabilitationWay(String rehabilitationWay) {
        this.rehabilitationWay = rehabilitationWay;
    }

    public String getDateWeeklyResearch() {
        return dateWeeklyResearch;
    }

    public void setDateWeeklyResearch(String dateWeeklyResearch) {
        this.dateWeeklyResearch = dateWeeklyResearch;
    }

    public String getDateWeeklyStart() {
        return dateWeeklyStart;
    }

    public void setDateWeeklyStart(String dateWeeklyStart) {
        this.dateWeeklyStart = dateWeeklyStart;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getEmaResult() {
        return emaResult;
    }

    public void setEmaResult(Integer emaResult) {
        this.emaResult = emaResult;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getGenerateType() {
        return generateType;
    }

    public void setGenerateType(Integer generateType) {
        this.generateType = generateType;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getEmaResultDesc() {
        return emaResultDesc;
    }

    public void setEmaResultDesc(String emaResultDesc) {
        this.emaResultDesc = emaResultDesc;
    }
}