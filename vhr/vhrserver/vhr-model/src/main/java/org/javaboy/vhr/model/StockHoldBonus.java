package org.javaboy.vhr.model;

/**
 * @ClassName   : StockHoldBonus
 * @description : TODO
 * @author      : sam
 * @datetime    : 2023年 03月 12日 21:43
 * @version:    : 1.0
 */

public class StockHoldBonus {
    private Integer id;

    /**
    * 股票代码
    */
    private String code;

    /**
    * 送股比例
    */
    private Double donateRate;

    /**
    * 转增比例
    */
    private Double increaseRate;

    /**
    * 派息比例
    */
    private Double bonusRate;

    /**
    * 股权登记日
    */
    private String dateRegister;

    /**
    * 除权日期
    */
    private String dateExRight;

    /**
    * 除息日
    */
    private String dateExBonus;

    /**
    * 分红说明
    */
    private String bonusNote;

    /**
    * 分红类型(年度分红/中期分红)
    */
    private String bonusType;

    /**
    * 报告时间
    */
    private String dateReport;

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

    public Double getDonateRate() {
        return donateRate;
    }

    public void setDonateRate(Double donateRate) {
        this.donateRate = donateRate;
    }

    public Double getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(Double increaseRate) {
        this.increaseRate = increaseRate;
    }

    public Double getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getDateExRight() {
        return dateExRight;
    }

    public void setDateExRight(String dateExRight) {
        this.dateExRight = dateExRight;
    }

    public String getDateExBonus() {
        return dateExBonus;
    }

    public void setDateExBonus(String dateExBonus) {
        this.dateExBonus = dateExBonus;
    }

    public String getBonusNote() {
        return bonusNote;
    }

    public void setBonusNote(String bonusNote) {
        this.bonusNote = bonusNote;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public String getDateReport() {
        return dateReport;
    }

    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }
}