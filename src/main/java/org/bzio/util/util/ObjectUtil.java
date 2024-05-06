package org.bzio.util.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * @author snow
 */
public class ObjectUtil {

    /**
     * 判断一个对象是否为空（包含集合为空的情况）
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Optional) {
            // 可以包含或不包含非空值的容器对象。
            return ((Optional<?>) object).isEmpty();
        } else if (object instanceof CharSequence) {
            // char值的一个可读序列。实现类：CharBuffer、String、StringBuffer、StringBuilder等
            return ((CharSequence) object).isEmpty();
        } else if (object.getClass().isArray()) {
            // 数组
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            // 集合
            return ((Collection<?>) object).isEmpty();
        } else if (object instanceof Map) {
            // 键值对集合
            return ((Map<?, ?>) object).isEmpty();
        }

        return false;
    }

    /**
     * * 判断一个对象是否为空（包含集合为空的情况）
     *
     * @param object Object
     * @return true：非空 false：为空
     */
    public static boolean isPresent(Object object)
    {
        return !isEmpty(object);
    }
}
