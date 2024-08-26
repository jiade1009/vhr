package org.javaboy.vhr.utils;

import org.javaboy.vhr.model.StockSubstepProfit;
import org.javaboy.vhr.model.StockSubstepTrade;
import org.javaboy.vhr.model.util.TradeType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sam
 * @ClassName : SubstepUtils
 * @description : TODO
 * @datetime : 2024年 08月 09日 17:31
 * @version: : 1.0
 */
public class SubstepUtils {

    /**
     * 根据股票的成本价和分级止盈时的持股数，计算股票在不同止盈期间的卖出价和卖出股票数
     * 358止盈规则：
     * 盈利3%，卖10%
     * 盈利5%，卖10%
     * 盈利8%，卖10%
     * 盈利10%，卖20%
     * 后续则按照上一次价格的1.05止盈，分别卖出1/3, 1/4, 1/5
     * 1.分级止盈针对的是5w以上（重仓股）的股票，
     * 2. 2w以下，按照10%清仓止盈，
     * 3.2w到5w之间的，按照10%止盈一半，后续按照1.05%止盈
     * @param vo
     */
    public static void updateSubstepPrice(StockSubstepProfit vo) {
        //计算其对应的分级止盈价格和交易股票数
        Double priceCost = vo.getPriceCost();
        Integer amountSubstep = vo.getAmountSubstep();

        vo.setPriceP3(NumberUtils.ceil(priceCost*1.03, 2));
        vo.setAmountP3((int)(NumberUtils.floor(amountSubstep*0.1/100, 0))*100);
        vo.setPriceP5(NumberUtils.ceil(priceCost*1.05, 2));
        vo.setAmountP5((int)(NumberUtils.floor(amountSubstep*0.1/100, 0))*100);
        vo.setPriceP8(NumberUtils.ceil(priceCost*1.08, 2));
        vo.setAmountP8((int)(NumberUtils.floor(amountSubstep*0.1/100, 0))*100);
        vo.setPriceP10(NumberUtils.ceil(priceCost*1.1, 2));
        vo.setAmountP10((int)(NumberUtils.floor(amountSubstep*0.2/100, 0))*100);
        Integer remainder = amountSubstep - vo.getAmountP3() - vo.getAmountP5() - vo.getAmountP8() - vo.getAmountP10();
        vo.setPriceP1051(NumberUtils.ceil(vo.getPriceP10()*1.05, 2));
        vo.setAmountP1051((int)NumberUtils.floor(remainder/3/100, 0)*100);
        vo.setPriceP1052(NumberUtils.ceil(vo.getPriceP1051()*1.05, 2));
        remainder -= vo.getAmountP1051();
        vo.setAmountP1052((int)NumberUtils.floor(remainder/4/100, 0)*100);
        vo.setPriceP1053(NumberUtils.ceil(vo.getPriceP1052()*1.05, 2));
        remainder -= vo.getAmountP1052();
        vo.setAmountP1053((int)NumberUtils.floor(remainder/5/100, 0)*100);
    }


    /**
     * 汇总：
     * 1.止盈操作，成本价/止盈股票数不变化；阶段+1；可用股票数和成本统计股票数减少
     * 2.降仓和调仓，成本价和阶段不变化，可用股票数、止盈股票数和成本统计股票数均减少；
     * 3.加仓，分3种情况处理：
     * 3.1.如果加仓的上次操作是降仓，成本价重新计算，阶段置0，可用股票数增加，止盈股票数等于最新可用股票数，成本统计股票数为加仓股票数；
     * 3.2.如果加仓上次操作是调仓或加仓，加仓成本加入计算新成本价，阶段不变，可用股票数、止盈股票数和成本统计股票数均增加；
     * 3.3.如果加仓上次操作是止盈，加仓成本加入计算新成本价，阶段置0，可用股票数增加，止盈股票数等于最新可用股票数，成本统计股票数增加加仓股票数；
     * 4止损和清仓，修改可用股票数为0，其他均不变化
     * @param profit
     * @param trade
     */
    public static void updatePrice(StockSubstepProfit profit, StockSubstepTrade trade) {
//        （加仓0、止盈1、止损2、减仓3、清仓4、调仓5）
        Double priceCost = profit.getPriceCost();  //成本价格
        Integer amountCost = profit.getAmountCost();  // 成本统计股票
        Integer amountSubstep = profit.getAmountSubstep();   //止盈股票数
        Integer amountAble = profit.getAmountAble();  //可用股票数
        Integer lastTradeType = profit.getLastTradeType();   //上一次交易类型，也就是其最新的上次交易类型
        Integer stage = profit.getProfitStage();  //止盈阶段

        switch (trade.getType()) {
            case 1:  //止盈操作
                stage ++;
                amountAble -= trade.getAmount();
                amountCost -= trade.getAmount();
                break;
            case 3: //减仓、调仓
            case 5:
                amountAble -= trade.getAmount();
                amountSubstep -= trade.getAmount();
                amountCost -= trade.getAmount();
                break;
            case 2:  //止损、清仓
            case 4:
                amountAble = 0;
                profit.setStatus(0);  // 结束
                break;
            case 0:  //加仓操作
                // 上次操作如果为空，默认是加仓操作
                if (lastTradeType == null || lastTradeType == TradeType.BUY.getIndex() ||
                        lastTradeType == TradeType.ADJUST.getIndex()) {
                    //上次操作为调仓或加仓
                    Double cost = priceCost * amountCost;  // 加仓前的股票成本
                    cost += trade.getPrice() * trade.getAmount();  //成本价加入新的成本价
                    Integer newAmountCost = amountCost + trade.getAmount();
                    Double newPriceCost = NumberUtils.ceil(cost / newAmountCost, 2);
                    priceCost = newPriceCost;

                    amountCost = newAmountCost;
                    amountAble += trade.getAmount();
                    amountSubstep += trade.getAmount();
                } else if (lastTradeType == TradeType.REDUCE.getIndex()) {
                    //上次操作为降仓
                    stage = 0;
                    priceCost = trade.getPrice();  //以这次的加仓成交价格作为成本价格
                    amountCost = trade.getAmount();  //以这次的加仓成交数量作为成本统计股票数
                    amountAble += trade.getAmount();
                    amountSubstep = amountAble;  // 止盈股票数等于最新可用股票数
                } else if (lastTradeType == TradeType.PROFIT.getIndex()) {
                    // 上次操作为止盈
                    stage = 0;
                    Double cost = priceCost * amountCost;  // 加仓前的股票成本
                    cost += trade.getPrice() * trade.getAmount(); //成本价加入新的成本价
                    Integer newAmountCost = amountCost + trade.getAmount();
                    Double newPriceCost = NumberUtils.ceil(cost / newAmountCost, 2);
                    priceCost = newPriceCost;

                    amountCost = newAmountCost;
                    amountAble += trade.getAmount();
                    amountSubstep = amountAble;  // 止盈股票数等于最新可用股票数
                }
                break;
            default:
                break;
        }
        if (profit.getStatus() == 0) return;
        profit.setAmountAble(amountAble);
        profit.setAmountSubstep(amountSubstep);
        profit.setAmountCost(amountCost);
        profit.setPriceCost(priceCost);
        profit.setProfitStage(stage);
        profit.setLastTradeType(trade.getType());

        // 根据止盈阶段更新对应每个止盈阶段的止盈股票数
        // 止盈阶段0：未止盈、1：p3阶段、2：p5阶段、3：p8阶段、4：p10阶段、5：p1051阶段、6：p1052阶段、7：p1053阶段、8：终极
        Integer remainder = 0;
        switch (profit.getProfitStage()){
            case 0:
                profit.setPriceP3(NumberUtils.ceil(priceCost*1.03, 2));
                profit.setAmountP3((int)(NumberUtils.floor(amountSubstep*0.1/100, 0))*100);
            case 1:
                profit.setPriceP5(NumberUtils.ceil(priceCost*1.05, 2));
                profit.setAmountP5((int)(NumberUtils.floor(amountSubstep*0.1/100, 0))*100);
            case 2:
                profit.setPriceP8(NumberUtils.ceil(priceCost*1.08, 2));
                profit.setAmountP8((int)(NumberUtils.floor(amountSubstep*0.1/100, 0))*100);
            case 3:
                profit.setPriceP10(NumberUtils.ceil(priceCost*1.1, 2));
                profit.setAmountP10((int)(NumberUtils.floor(amountSubstep*0.2/100, 0))*100);
            case 4:
                remainder = amountSubstep - profit.getAmountP3() - profit.getAmountP5()
                        - profit.getAmountP8() - profit.getAmountP10();
                profit.setPriceP1051(NumberUtils.ceil(profit.getPriceP10()*1.05, 2));
                profit.setAmountP1051((int)NumberUtils.floor(remainder/3/100, 0)*100);
            case 5:
                profit.setPriceP1052(NumberUtils.ceil(profit.getPriceP1051()*1.05, 2));
                if (profit.getProfitStage() == 5) {
                    remainder = amountSubstep - profit.getAmountP3() - profit.getAmountP5()
                            - profit.getAmountP8() - profit.getAmountP10() - profit.getAmountP1051();
                } else {
                    remainder -= profit.getAmountP1051();
                }
                profit.setAmountP1052((int)NumberUtils.floor(remainder/4/100, 0)*100);
            case 6:
                profit.setPriceP1053(NumberUtils.ceil(profit.getPriceP1052()*1.05, 2));
                if (profit.getProfitStage() == 6) {
                    // 如果当前处于阶段6，直接计算阶段7的止盈价格和数量，直接计算剩余的股票为多少
                    remainder = amountSubstep - profit.getAmountP3() - profit.getAmountP5()
                            - profit.getAmountP8() - profit.getAmountP10() - profit.getAmountP1051()
                            - profit.getAmountP1052();
                } else {
                    remainder -= profit.getAmountP1052();
                }
                profit.setAmountP1053((int)NumberUtils.floor(remainder/5/100, 0)*100);
                break;
            default:
                System.out.println("止盈已经结束，或者已经完成阶段7的止盈，不再做处理");
                break;
        }
    }

    /**
     * 获取指定止盈阶段的止盈价格和止盈股票数
     * @param profit
     * @param stage
     * @return list[] [止盈价格， 止盈股票数]
     */
    public static List<String> getProfitInfo(StockSubstepProfit profit, Integer stage) {
        Double priceProfit = 0d;  // 止盈价格
        Integer amountProfit = 0;  // 止盈卖出股票数
        switch (stage) {
            case 1:
                priceProfit = profit.getPriceP3();
                amountProfit = profit.getAmountP3();
                break;
            case 2:
                priceProfit = profit.getPriceP5();
                amountProfit = profit.getAmountP5();
                break;
            case 3:
                priceProfit = profit.getPriceP8();
                amountProfit = profit.getAmountP8();
                break;
            case 4:
                priceProfit = profit.getPriceP10();
                amountProfit = profit.getAmountP10();
                break;
            case 5:
                priceProfit = profit.getPriceP1051();
                amountProfit = profit.getAmountP1051();
                break;
            case 6:
                priceProfit = profit.getPriceP1052();
                amountProfit = profit.getAmountP1052();
                break;
            case 7:
                priceProfit = profit.getPriceP1053();
                amountProfit = profit.getAmountP1053();
                break;
        }
        List<String> result = new ArrayList<>();
        result.add(String.valueOf(priceProfit));
        result.add(String.valueOf(amountProfit));
        return result;
    }
}
