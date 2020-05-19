package cn.spring.ssm.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created with IntelliJ IDEA.
 * Package: Date
 * User: 25414
 * Date: 2019/9/9
 * Time: 13:58
 * Description:JDK1.8日期类工具类
 */
public class LocalDateUtil {

    /**
     * 根据指定格式获得日期
     *
     * @return
     */
    public static String getDate(DateTimeFormatter formatter) {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 默认 yyyy-MM-dd 获得日期
     *
     * @return
     */
    public static String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    }


    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
}
