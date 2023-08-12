package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.javaboy.vhr.model.util.StockHoldStatus;

import java.util.Date;

/**
 * @author : sam
 * @ClassName : StockHold
 * @description : TODO
 * @datetime : 2023年 08月 08日 23:12
 * @version: : 1.0
 */

public class StockHold {
    private Integer id;

    /**
     * 股票代码
     */
    private String code;

    private Date timeCreate;

    private Date timeUpdate;

    /**
     * 状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束、7交易失败
     */
    private Integer status;

    /**
     * 买入股票数
     */
    private Integer buyAmount;

    /**
     * 买入价格
     */
    private Double buyPrice;

    /**
     * 持有股票数
     */
    private Integer holdAmount;

    /**
     * 卖出阶段，-1止损阶段，0初始阶段，1止盈1阶段，2止盈2阶段
     */
    private Integer sellStage;

    /**
     * 关联的ema_result_id
     */
    private Integer weeklyEmaResultId;

    /**
     * 备注
     */
    private String note;

    /**
     * 昨日收盘价
     */
    private Double priceClose;

    /**
     * 近期最高价
     */
    private Double priceHigh;

    /**
     * 近期最低价
     */
    private Double priceLow;

    /**
     * 是否发出增强白信号, 0未发出，1已发出
     */
    private Boolean enhanceWhiteSign;

    /**
     * 是否失去增强白信号, 0未失去，1已失去
     */
    private Boolean loseEnhanceWhiteSign;

    /**
     * 下一阶段价格或者是卖出最高价阀值
     */
    private Double priceSellHigh;

    /**
     * 卖出最低价阀值
     */
    private Double priceSellLow;

    /**
     * 最近一个交易日的close_ema_18值
     */
    private Double closeEma18;

    /**
     * 止损价格
     */
    private Double priceStop;

    /**
     * 加入方式，0直接，1回头草
     */
    private Integer generateType;

    /**
     * 买入总金额
     */
    private Double buyTotal;

    /**
     * 买入手续费
     */
    private Double buyFee;

    /**
     * 购买时间
     */
    private Date timeBuy;

    /**
     * 卖出时间
     */
    private Date timeSell;

    /**
     * 股票白色信号发起日期
     */
    private String timeWhiteSign;

    /**
     * 股票持股有效起始日期
     */
    @JsonFormat(pattern = "yyyyMMdd")
    private Date timeHoldBegin;

    /**
     * 第一次峰顶价格
     */
    private Double pricePeakTop;

    /**
     * 第一次涨幅比率
     */
    private Double priceUpRate1;

    /**
     * 第二次涨幅比率
     */
    private Double priceUpRate2;

    /**
     * 第三次涨幅比率，如果第三次涨幅比例为0，那么意味着第二次涨幅进行清仓操作
     */
    private Double priceUpRate3;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getHoldAmount() {
        return holdAmount;
    }

    public void setHoldAmount(Integer holdAmount) {
        this.holdAmount = holdAmount;
    }

    public Integer getSellStage() {
        return sellStage;
    }

    public void setSellStage(Integer sellStage) {
        this.sellStage = sellStage;
    }

    public Integer getWeeklyEmaResultId() {
        return weeklyEmaResultId;
    }

    public void setWeeklyEmaResultId(Integer weeklyEmaResultId) {
        this.weeklyEmaResultId = weeklyEmaResultId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(Double priceClose) {
        this.priceClose = priceClose;
    }

    public Double getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(Double priceHigh) {
        this.priceHigh = priceHigh;
    }

    public Double getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(Double priceLow) {
        this.priceLow = priceLow;
    }

    public Boolean getEnhanceWhiteSign() {
        return enhanceWhiteSign;
    }

    public void setEnhanceWhiteSign(Boolean enhanceWhiteSign) {
        this.enhanceWhiteSign = enhanceWhiteSign;
    }

    public Boolean getLoseEnhanceWhiteSign() {
        return loseEnhanceWhiteSign;
    }

    public void setLoseEnhanceWhiteSign(Boolean loseEnhanceWhiteSign) {
        this.loseEnhanceWhiteSign = loseEnhanceWhiteSign;
    }

    public Double getPriceSellHigh() {
        return priceSellHigh;
    }

    public void setPriceSellHigh(Double priceSellHigh) {
        this.priceSellHigh = priceSellHigh;
    }

    public Double getPriceSellLow() {
        return priceSellLow;
    }

    public void setPriceSellLow(Double priceSellLow) {
        this.priceSellLow = priceSellLow;
    }

    public Double getCloseEma18() {
        return closeEma18;
    }

    public void setCloseEma18(Double closeEma18) {
        this.closeEma18 = closeEma18;
    }

    public Double getPriceStop() {
        return priceStop;
    }

    public void setPriceStop(Double priceStop) {
        this.priceStop = priceStop;
    }

    public String getStatusNote() {
        return StockHoldStatus.getName(this.status);
    }

    public Integer getGenerateType() {
        return generateType;
    }

    public void setGenerateType(Integer generateType) {
        this.generateType = generateType;
    }

    public Double getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(Double buyTotal) {
        this.buyTotal = buyTotal;
    }

    public Double getBuyFee() {
        return buyFee;
    }

    public void setBuyFee(Double buyFee) {
        this.buyFee = buyFee;
    }

    public Date getTimeBuy() {
        return timeBuy;
    }

    public void setTimeBuy(Date timeBuy) {
        this.timeBuy = timeBuy;
    }

    public Date getTimeSell() {
        return timeSell;
    }

    public void setTimeSell(Date timeSell) {
        this.timeSell = timeSell;
    }

    public String getTimeWhiteSign() {
        return timeWhiteSign;
    }

    public void setTimeWhiteSign(String timeWhiteSign) {
        this.timeWhiteSign = timeWhiteSign;
    }

    public Date getTimeHoldBegin() {
        return timeHoldBegin;
    }

    public void setTimeHoldBegin(Date timeHoldBegin) {
        this.timeHoldBegin = timeHoldBegin;
    }

    public Double getPricePeakTop() {
        return pricePeakTop;
    }

    public void setPricePeakTop(Double pricePeakTop) {
        this.pricePeakTop = pricePeakTop;
    }

    public Double getPriceUpRate1() {
        return priceUpRate1;
    }

    public void setPriceUpRate1(Double priceUpRate1) {
        this.priceUpRate1 = priceUpRate1;
    }

    public Double getPriceUpRate2() {
        return priceUpRate2;
    }

    public void setPriceUpRate2(Double priceUpRate2) {
        this.priceUpRate2 = priceUpRate2;
    }

    public Double getPriceUpRate3() {
        return priceUpRate3;
    }

    public void setPriceUpRate3(Double priceUpRate3) {
        this.priceUpRate3 = priceUpRate3;
    }
}