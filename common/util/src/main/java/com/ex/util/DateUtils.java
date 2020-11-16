/**
 * @Title: DateUtils.java
 * @Package com.yijiu.eotc.commons.utils
 * @Description:
 * @author vDalf   2020年4月1日 下午2:36:53
 */
package com.ex.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @author vDalf  2020年4月1日 下午2:36:53
 */
public class DateUtils {

    /** [yyyy-MM-dd] */
    public static final DateTimeFormatter ISO_Day = DateTimeFormatter.ISO_LOCAL_DATE;//ofPattern("yyyy-MM-dd");
    /** [yyyy-MM-dd HH:mm:ss] */
    public static final DateTimeFormatter ISO_Sec = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /** [yyyy-MM-ddTHH:mm:ss] */
    public static final DateTimeFormatter ISO_Sec_T = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    /** [yyyy-MM-dd HH:mm:ss:SSS] */
    public static final DateTimeFormatter ISO_Mill = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

    public static final ZoneOffset systemZoneOffset = ZoneOffset.of("+8");

    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date parse(String text) {
        return parse(text, ISO_Sec);
    }

    public static Date parse(String text, String pattern) {
        return parse(text, DateTimeFormatter.ofPattern(pattern));
    }

    public static Date parse(String text, DateTimeFormatter pattern) {
        return parse(text, pattern, (pattern == ISO_Day || pattern == DateTimeFormatter.ISO_LOCAL_DATE));
    }

    /**
     * @Description:
     * @param text
     * @param pattern
     * @param day    是否精确到天 因为JDK8不支持将裸的日期作为Time <br>
     * 				会发生DateTimeParseException:<br>
     * 				Text '2019-08-19' could not be parsed:  <br>
     * 				Unable to obtain LocalDateTime from TemporalAccessor:...<br>
     * @return
     * @throws DateTimeParseException:传入的pattern是一个精确到日期的格式但是day传了false
     * @author vDalf
     */
    public static Date parse(String text, DateTimeFormatter pattern, boolean day) {
        LocalDateTime localDateTime = null;
        if (day) {
            localDateTime = LocalDate.parse(text, pattern).atStartOfDay();
        } else {
            localDateTime = LocalDateTime.parse(text, pattern);
        }
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return wtf(date);
    }

    public static Date wtf(Date date) {
        Calendar ca = Calendar.getInstance();
        Calendar cld = Calendar.getInstance();
        ca.setTime(date);
//		ca.setTimeInMillis(date.getTime());
        cld.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH),
                ca.get(Calendar.HOUR_OF_DAY), ca.get(Calendar.MINUTE), ca.get(Calendar.SECOND));
//		cld.set(Calendar.MILLISECOND, ca.get(Calendar.MILLISECOND));
        return cld.getTime();
//		return new Date(date.getTime());
    }


    public static String format(LocalDate date) {
        return format(date, ISO_Sec);
    }

    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return formatter.format(date);
    }

    public static String format(LocalDateTime datetime) {
        return format(datetime, ISO_Sec);
    }

    public static String format(LocalDateTime datetime, DateTimeFormatter formatter) {
        return formatter.format(datetime);
    }


    public static String format(Date time) {
        return format(date2LocalDateTime(time), ISO_Sec);
    }

    public static String format(Date time, DateTimeFormatter formatter) {
        return format(date2LocalDateTime(time), formatter);
    }

}
