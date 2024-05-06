package org.bzio.util.util.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author snow
 */
public class LocalDateTimeUtil {

    /**
     * 获取当前时间
     *  去除秒后面的时间戳
     */
    public static Date getNowDateToSeconds() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 清除毫秒部分
        LocalDateTime truncatedDateTime = now.withNano(0);

        // 转换为Date
        return Date.from(truncatedDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
