package org.javaboy.vhr.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author : sam
 * @ClassName : DateUtils
 * @description : TODO
 * @datetime : 2022年 10月 27日 20:22
 * @version: : 1.0
 */
public class DateUtils {
    public static final String yyyy__MM__dd = "yyyy/MM/dd";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String HH_mm_ss = "HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssfff = "yyyyMMddHHmmssSSS";
    public static final String yyyy_MM_dd_HH_mm_ss_fff = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String yyyy_MM_dd_HH_mm_ss_fffffff = "yyyy-MM-dd HH:mm:ss.SSSSSSS";
    private static long nd = 1000 * 24 * 60 * 60;//每天毫秒数
    private static long nh = 1000 * 60 * 60;//每小时毫秒数
    private static long nm = 1000 * 60;//每分钟毫秒数

    public static final Date DEFAULT_DATE = parseDate("1970-01-01");

    private final static TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");


    /**
     * 获取日期的格式化字符串
     *
     * @param date    待格式化日期
     * @param pattern 格式模式，可选DateUtil的公开静态定义
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        try {
            return getDateFormat(pattern).format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取日期的格式化字符串(默认格式:yyyy-MM-dd)
     *
     * @param date 待格式化日期
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, yyyy_MM_dd);
    }

    /**
     * 将日期字符串转换成日期对象
     *
     * @param dateStr 待转换字符串
     * @param pattern 格式模式，可选DateUtil的公开静态定义
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr, String pattern) {
        try {
            return getDateFormat(pattern).parse(dateStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 将日期字符串转换成日期对象(默认格式:yyyy-MM-dd)
     *
     * @param dateStr 待转换字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, yyyy_MM_dd);
    }

    /**
     * 根据生日判断是否为成年人
     *
     * @param birthday
     * @return
     */
    public static boolean isAdult(Date birthday) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.YEAR, -18);
        return calendar.getTime().after(birthday);
    }

    private static DateFormat getDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 指定日期 进行 指定类型 加减
     *
     * @param date         指定日期,可选，默认当前时间
     * @param calendarType Calendar指定类型 如： 分钟用 Calendar.MINUTE
     * @param no           加减数
     * @return Date 计算后的日期
     */
    public static Date dateCalculation(Date date, int calendarType, int no) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        calendar.add(calendarType, no);
        return calendar.getTime();
    }

    /**
     * 通过时间秒毫秒数判断两个时间间隔天数（满24小时进1天）
     *
     * @param date1 被减时间
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 获取指定时间的小时数
     *
     * @param date
     * @return
     */
    public static int getHours(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间的分钟
     *
     * @param date
     * @return
     */
    public static int getMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回日期加X天后的日期
     *
     * @param date
     * @param i
     * @return
     */
    public static Date addDay(Date date, int i) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(GregorianCalendar.DATE, i);
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回日期加X分钟后后的日期
     *
     * @param date
     * @param i
     * @return
     */
    public static Date addMinute(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.MINUTE, i);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的日期部分
     *
     * @param date
     * @return
     */
    public static Date getDatePart(Date date) {
        return DateUtils.parseDate(DateUtils.formatDate(date, DateUtils.yyyy_MM_dd), DateUtils.yyyy_MM_dd);
    }

    /**
     * 获取指定日期的时间部分
     *
     * @param date
     * @return
     */
    public static String getTimePart(Date date) {
        return DateUtils.formatDate(date, DateUtils.HH_mm_ss);
    }

    /**
     * 指定字符串转换成标准时间部分(HH:mm:ss格式)
     *
     * @param str
     * @return
     */
    public static String convertTimePart(String str) {
        if (StringUtils.isEmpty(str)) return "";
        String[] array = str.split(":");
        if (array.length == 1) {
            return (array[0].length() == 1 ? "0" + array[0] : array[0]) + ":00:00";
        }
        if (array.length == 2) {
            return (array[0].length() == 1 ? "0" + array[0] : array[0]) + ":" +
                    (array[1].length() == 1 ? "0" + array[1] : array[1]) + ":00";
        }
        if (array.length == 3) {
            return DateUtils.formatDate(DateUtils.parseDate(str, DateUtils.HH_mm_ss), DateUtils.HH_mm_ss);
        }
        return str;
    }


    /**
     * 获取到店时间
     *
     * @param startDate 入住日
     * @param earliest  true为获取最早到店时间，false为最晚到店时间
     * @return
     */
    public static Date getArrivalTime(Date startDate, boolean earliest) {
        int timeOffset = earliest ? 14 : 21;
        Calendar today = Calendar.getInstance(timeZone);
        Calendar start = Calendar.getInstance(timeZone);
        start.setTime(startDate);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        if (start.after(today)) {
            start.set(Calendar.HOUR_OF_DAY, timeOffset);
        } else {
            if (today.get(Calendar.HOUR_OF_DAY) >= timeOffset) {
                // 若当前时间已超过timeOffset点，下的是今日出游的，最早到店时间加30分钟
                start.set(Calendar.HOUR_OF_DAY, today.get(Calendar.HOUR_OF_DAY));
                start.set(Calendar.MINUTE, 30);
            } else {
                // 若当前时间在timeOffset点前的，下的是今日出游的，最早到店时间加30分钟
                start.set(Calendar.HOUR_OF_DAY, timeOffset);
            }
        }
        return start.getTime();
    }


    /**
     * 传入时间是否超过当前时间指定的差值
     * @param date
     * @param diff
     * @return
     */

    public static boolean isDateThanNowDateOverDiff(long date, Integer diff){
        long nm = 1000 * 60;
        int minNumber = (int)((System.currentTimeMillis() - date) / nm);
        return minNumber >= diff;
    }


    /**
     * 日期比大小
     * @param date1
     * @param date2
     * @return
     */
    public static int dateCompare(String date1,String date2){
        Date date_1 = parseDate(date1, yyyy_MM_dd_HH_mm_ss);
        Date date_2 = parseDate(date2, yyyy_MM_dd_HH_mm_ss);
        if(date_1.after(date_2)){
            return 1;
        }
        if(date_1.before(date_2)){
            return -1;
        }
        return 0;
    }

    /**
     * 根据日期时间字符串获取日期时间
     * @param datetime
     * @param format 为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime stringToDateTime(String datetime, String format) {

        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(datetime,formatter);
    }

    public static LocalDateTime parseStringToDateTime(String times) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(times,df);
        return ldt;
    }

    /**
     *
     * (方法说明描述) 当前时间是几点
     *
     * @return
     */
    public static int getHourOfDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour;
    }


    /**
     * 计算俩时间相差分钟数
     * @param endDate 结束时间
     * @param startDate 开始时间
     * @return
     */
    public static int getDiffMinutes(Date endDate, Date startDate) {
        return (int)((endDate.getTime() - startDate.getTime()) / (1000 * 60));
    }

    /**
     * 计算俩时间相差秒数
     * @param endDate 结束时间
     * @param startDate 开始时间
     * @return
     */
    public static int getDiffSeconds(Date endDate, Date startDate) {
        return (int)((endDate.getTime() - startDate.getTime()) / 1000);
    }


}
