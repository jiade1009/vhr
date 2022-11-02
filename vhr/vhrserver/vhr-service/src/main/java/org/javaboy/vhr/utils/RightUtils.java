package org.javaboy.vhr.utils;

/**
 * @author : sam
 * @ClassName : RightUtils
 * @description : 二进制权限管理
 * @datetime : 2022年 10月 28日 09:22
 * @version: : 1.0
 */
public class RightUtils {

    /**
     * 校验是否有权限
     * @param userPurview 用户权限值
     * @param optPurview 校验第几位的权限（从右向左数，下标从0开始）
     * @return
     */
    public static boolean checkRight(int userPurview, int optPurview){
        int purviewValue = (int)Math.pow(2, optPurview);
        return (userPurview & purviewValue) == purviewValue;
    }

    /**
     * 赋予权限
     * @param userPurview
     * @param optPurview
     * @return
     */
    public static int assignRight(int userPurview, int optPurview){
        return userPurview | (int)Math.pow(2, optPurview);
    }

    public static int assignRight(int userPurview, String purviewStr){
        return userPurview | Integer.valueOf(purviewStr, 2);
    }
    /**
     * 删除权限
     * @param userPurview
     * @param optPurview
     * @return
     */
    public static int delRight(int userPurview, int optPurview){
        return userPurview & ~(int)Math.pow(2, optPurview);
    }
    public static int delRight(int userPurview, String purviewStr){
        return userPurview & ~ Integer.valueOf(purviewStr, 2);
    }


    public static String toBinaryString(Integer val ) {
        return Integer.toString(val, 2);
    }

    public static Integer binaryStringToInteger(String binaryStr) {
        return Integer.valueOf(binaryStr, 2);
    }

    public static void main(String[] args) {
        Integer userPurview = binaryStringToInteger("100010");
        Integer optPurview = 4;
        System.out.println(checkRight(userPurview, optPurview));
        System.out.println(toBinaryString(assignRight(userPurview, optPurview)));
        System.out.println(toBinaryString(delRight(userPurview, optPurview)));

        System.out.println(toBinaryString(assignRight(userPurview, "011000")));
        System.out.println(delRight(userPurview, "000011"));
    }
}
