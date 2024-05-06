package org.bzio.util.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 字符串相关工具类
 *
 * @author snow
 */
public class StringUtil {

    // 空字符串
    private static final String EMPTY_STR = "";

    /**
     * 判断字符串是否为空
     *
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return Objects.isNull(str)|| EMPTY_STR.equals(str.trim());
    }

    /**
     * 判断字符串是否为空
     *
     * @return true：非空 false：为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }



    /**
     * 将object转化为string object必须为字符串类型
     */
    public static String convertToString(Object obj) {
        if (obj == null) {
            return EMPTY_STR;
        } else {
            return String.valueOf(obj).trim();
        }
    }

    /**
     * 若字符串为null返回空串，不为null返回源字符串
     */
    public static String convertNullToEmptyStr(String src) {
        return convertSpeValueToEmptyStr(src, "");
    }

    /**
     * 若字符串为null返回自定义值，不为null返回源字符串
     */
    public static String convertSpeValueToEmptyStr(String src, String tar) {
        return Objects.isNull(src) ? tar : src;
    }

    /**
     * 判断字符串是否为数字类型
     * @return 是数字类型-true
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }
    
    /**
     * 字符串比较
     * @return 相同-true
     */
    public static boolean compareStr(String src, Object o) {
        if (src != null)
            return src.equals(o);
        else
            return o == null;
    }

    /**
     * 字符串包含忽略大小写
     */
    public static boolean containsAnyIgnoreCase(String str, String... strings) {
        if (isEmpty(str)) return false;
        return Arrays.stream(strings).anyMatch(str::equalsIgnoreCase);
    }
}
