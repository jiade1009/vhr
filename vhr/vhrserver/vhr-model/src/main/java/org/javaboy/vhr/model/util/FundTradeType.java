package org.javaboy.vhr.model.util;

/**
 * @author : sam
 * @ClassName : FundTradeType
 * @description : 资金交易类型
 * @datetime : 2022年 11月 23日 16:55
 * @version: : 1.0
 */
public enum FundTradeType {
    INCOME(1, "资金转入", 1),
    ROLLOUT(2,"资金转出", -1),
    STOCK_BUY(3,"股票买入", -1),
    STOCK_SELL(4,"股票卖出", 1),
    STOCK_BONUS(5,"股票红利", 1),
    INTEREST(6,"利息归本", 1),
    STOCK_TAX(7,"股票交税", -1);

    // 成员变量
    private String name;
    private int index;
    private int flag;  // 标示信息，1表示收入，-1表示支出

    FundTradeType(int index, String name, int flag) {
        this.name = name;
        this.index = index;
        this.flag = flag;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    // 普通方法
    public static String getName(int index) {
        for (FundTradeType c : FundTradeType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static FundTradeType getFundTradeType(int index) {
        for (FundTradeType c : FundTradeType.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }
}
