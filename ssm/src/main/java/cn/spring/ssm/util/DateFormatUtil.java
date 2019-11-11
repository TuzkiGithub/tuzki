package cn.spring.ssm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.util
 * User: 25414
 * Date: 2019/9/9
 * Time: 9:49
 * Description:
 */
public class DateFormatUtil {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty()) {
            return null;
        }
        if (format == null || format.isEmpty()) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return String.valueOf(sdf.parse(date_str).getTime());
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(DateFormatUtil.timeStamp2Date("1482163200000", FORMAT));
//        System.out.println(DateFormatUtil.date2TimeStamp("2019-09-09 10:48:47", FORMAT));


        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDate);
//        System.out.println(localTime);
        String dateStr = localDateTime.format(DateTimeFormatter.ISO_DATE);

    }
}
