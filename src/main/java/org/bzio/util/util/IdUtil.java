package org.bzio.util.util;

import java.util.UUID;

/**
 * 主键工具类
 *
 * @author snow
 */
public class IdUtil {

    /**
     * 生成UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return randomUUID().replace("-", "");
    }
}
