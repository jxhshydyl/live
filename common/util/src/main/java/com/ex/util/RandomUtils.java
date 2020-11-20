package com.ex.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @ClassName: RandomUtils
 * @Description: 随机工具类<br>
 */
public class RandomUtils {

    private static final char[] dictionary = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    /**
     * 获取随机单词字符串<br>
     * @param len 单词长度
     */
    public static String getRandomWord(int len) {
        char[] c = new char[len];
        Random random = new Random(System.nanoTime());
        while (len > 0) {
            BigDecimal bd = new BigDecimal(random.nextFloat()).abs();
            int i = (bd.movePointRight(2).intValue());
            i &= 0x3F;
            if (i > 0x3D) {
                c[len - 1] = dictionary[i >> 1];
            } else {
                c[len - 1] = dictionary[i];
            }
            len--;
        }
        return new String(c);
    }

    /**
     * 获取随机数字字符串<br>
     * <p>字符串固定长度 {@code len}.不足位数将左侧补0</p>
     * <pre>
     * getRandomStr(6) -> 777888
     * getRandomStr(6) -> 007888
     * </pre>
     * @param len 最大位数
     * @throws IllegalArgumentException: if len gt. 18
     */
    public static String getRandomStr(int len) {
        return StringUtils.leftPad(String.valueOf(getRandomNumber(len)), len, '0');
    }

    /**
     * 获取随机长整形数值<br>
     * @param len 最大位数
     * @throws IllegalArgumentException: if len gt. 18
     */
    public static long getRandomNumber(int len) {
        if (len > 18) {
            throw new IllegalArgumentException("len coun't too long than 18");
        }
        Random random = new Random(System.nanoTime());
        BigDecimal bd = new BigDecimal(random.nextFloat()).abs();
        return bd.movePointRight(len).longValue();
    }

    public static BigDecimal getRandomNumber(BigDecimal min, BigDecimal max) {
        if (min.compareTo(max) >= 0) {
            throw new IllegalArgumentException("min is bigger than max");
        }
        Random random = new Random(System.nanoTime());
        BigDecimal bd = new BigDecimal(random.nextFloat()).abs();
        return min.add(max.subtract(min).multiply(bd));
    }

}
