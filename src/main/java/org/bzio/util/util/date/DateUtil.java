package org.bzio.util.util.date;

import org.bzio.util.constant.DateConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author snow
 */
public class DateUtil {

    // 确保SimpleDateFormat线程安全问题
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateConstant.YYYY_MM_DD_HH_MM_SS));

    private static SimpleDateFormat getDateFormat() {
        return THREAD_LOCAL.get();
    }

    /**
     * 获取当前时间
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 根据时间戳获取指定时间
     */
    public static Date getDate(Long time) {
        return new Date(time);
    }

    /**
     * 获取当前时间字符串形式
     */
    public static String getNowDateStr(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 时间类型转字符串
     * 默认yyyy-MM-dd HH:mm:ss格式
     */
    public static String format(Date date) {
        return format(date, DateConstant.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间类型转字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = getDateFormat();
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转时间类型
     * 默认yyyy-MM-dd HH:mm:ss格式
     */
    public static Date parse(String date) {
        return parse(date, DateConstant.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 字符串转时间类型
     */
    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = getDateFormat();
            simpleDateFormat.applyPattern(pattern);
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("时间转换发生异常：", e);
        }
    }

    /* Calendar操作 */

    /**
     * 获取两个时间差之间所有的日期
     * @return pattern格式的日期集合
     */
    public static List<String> getDateBetween(Date startDate, Date endDate, int type, String pattern) {
        List<String> dateList = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) {
            dateList.add(format(startCalendar.getTime(), pattern));
            startCalendar.add(type, 1);
        }

        return dateList;
    }

    /**
     * 判断时间是否处于某个时间段内
     *
     * @param date 需要比较的时间
     * @param start 起始时间
     * @param end 结束时间
     */
    public static boolean belongCalendar(Date date, Date start, Date end) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Calendar after = Calendar.getInstance();
        after.setTime(start);
        Calendar before = Calendar.getInstance();
        before.setTime(end);

        return c.after(after) && c.before(before);
    }

    /**
     * 获取时间的基本单位
     * @param date 查询日期
     * @param c Calendar对应的基本单位枚举
     * @return 日期基本单位
     */
    public static int getUnitFromDate(Date date, int c) {
        // 创建 Calendar 对象，并设置时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(c);
    }
}
