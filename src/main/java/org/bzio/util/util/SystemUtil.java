package org.bzio.util.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务器系统环境工具类
 *
 * @author snow
 */
public class SystemUtil {


    /**
     * 获取ip信息
     *
     * @return ip信息
     */
    public static InetAddress getLocalHost() {
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return null;
        }
    }

    /**
     * 获取ip地址
     * @return ip地址
     */
    public static String getLocalIp() {
        InetAddress inetAddress = getLocalHost();
        if (inetAddress == null)
            return null;
        else
            return inetAddress.getHostAddress();
    }
}
