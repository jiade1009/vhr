package org.javaboy.vhr.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author : sam
 * @ClassName : NumberUtils
 * @description : TODO
 * @datetime : 2024年 08月 08日 10:48
 * @version: : 1.0
 */
public class NumberUtils<main> {

    /**
     * 将double类型数字四舍五入到指定的小数位数
     * @param value 要四舍五入的数字
     * @param scale 指定的小数位数
     * @return 四舍五入后的数字
     */
    public static double round(double value, int scale) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        return bd.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }
    /**
     * 将double类型数字向上进到指定的小数位数
     * @param value 要向上去的数字
     * @param scale 指定的小数位数
     * @return
     */
    public static double ceil(String value, int scale) {
        BigDecimal bd = new BigDecimal(value);
        return bd.setScale(scale, RoundingMode.CEILING).doubleValue();
    }
    public static double ceil(double value, int scale) {
        return ceil(Double.toString(value), scale);
    }

    /**
     * 将double类型数字向下舍到指定的小数位数
     * @param value 要向下舍的数字
     * @param scale 指定的小数位数
     * @return
     */
    public static double floor(String value, int scale) {
        BigDecimal bd = new BigDecimal(value);
        return bd.setScale(scale, RoundingMode.FLOOR).doubleValue();
    }
    public static double floor(double value, int scale) {
        return floor(Double.toString(value), scale);
    }

    /**
     * 判断一个数是否为整数
     * @param num 任意数值
     * @return 如果是整数返回true，否则返回false
     */
    public static boolean isInteger(double num) {
        return num == Math.floor(num);
    }

    /**
     * 将字符串转换为int类型，如果转换失败则返回默认值
     * @param str 字符串
     * @param defaultValue 默认值
     * @return 转换结果或默认值
     */
    public static int toInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为double类型，如果转换失败则返回默认值
     * @param str 字符串
     * @param defaultValue 默认值
     * @return 转换结果或默认值
     */
    public static double toDouble(String str, double defaultValue) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将double类型数值格式化为指定小数位数的字符串
     * @param value 要格式化的数值
     * @param scale 指定的小数位数
     * @return 格式化后的字符串
     */
    public static String format(double value, int scale) {
        DecimalFormat df = new DecimalFormat("#.00"); // 默认是两位小数，可以调整
        df.setMaximumFractionDigits(scale);
        return df.format(value);
    }

    // 你可以继续添加更多的工具方法
    public static void main(String[] args) {
        System.out.println(NumberUtils.floor(2.235, 0));
        Integer amountSubstep = 5600;
        System.out.println(NumberUtils.floor(amountSubstep*0.1/100, 0)*100);
        System.out.println(NumberUtils.floor(amountSubstep*0.1/100, 0)*100);
    }
}