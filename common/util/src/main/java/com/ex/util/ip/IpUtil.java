package com.ex.util.ip;

import com.alibaba.fastjson.JSON;
import com.ex.util.ip.datx.IPv4FormatException;
import com.ex.util.ip.datx.IpSeeker;
import com.ex.util.ip.datx.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date: 13/4/2020 下午 6:16
 * @Version: 1.0
 * @Description:
 */
public class IpUtil {
    /**
     * IP管理工具类
     *
     * @author robot.guo
     */


    private static Logger log = LoggerFactory.getLogger(IpUtil.class);

    //http://www.ipip.net/
    private static IpSeeker seeker;

    public static boolean isIPv4Address(String input) {
        return Util.isIPv4Address(input);
    }

    //获取ip的方法
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("Cdn-Src-Ip");
        if (ip != null && ip.length() > 0) {
            return ip;
        }
        // request.getHeader("X-Real-IP");
        ///使用cdn时候才会有值   使用加速乐时候的情况
        String cdn = request.getHeader("X-Connecting-IP");

        if (cdn != null) {
            return cdn;
        }

        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP"); // 获取代理ip
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr(); // 获取真实ip
        }

        if (ip.indexOf(",") >= 0) {
            //log.error("x-forwarded-for:" + ip + ",");
            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                ip = ips[i];
                if (!"".equals(ip) && !"unknown".equalsIgnoreCase(ip) && !"127.0.0.1".equals(ip)) {
                    break;
                }
            }
        }
        return ip;
    }

    public static Location getLocation(String ip) {
        try {
            if (seeker == null) {
                initIpSeeker();
            }
            Location loc = new Location();
            String[] arr = seeker.find(ip);
            loc.setCountry(arr[0]);
            loc.setProvince(arr[1]);
            loc.setCity(arr[2]);
            return loc;
        } catch (IPv4FormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getArea(String ip) {
        Location loc = getLocation(ip);
        if (loc == null) {
            return "未知";
        }
        return loc.getCountry() + " " + loc.getProvince() + " " + loc.getCity();
    }

    public static String getCountryShortEnName(String ip) throws Exception {
        Location loc = getLocation(ip);
        return loc.getCountryShortEnName();
    }

    private synchronized static void initIpSeeker() {
        if (seeker != null) {
            return;
        }
        try {
            seeker = new IpSeeker();
        } catch (Exception e) {
            log.error("IP地址库实例化出错", e);
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String area = getArea("47.244.186.9");
        System.out.println(area);
        Location location = IpUtil.getLocation("47.244.186.9");
        System.out.println(JSON.toJSONString(location));
    }

}
