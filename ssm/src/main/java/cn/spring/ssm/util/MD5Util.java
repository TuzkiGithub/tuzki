package cn.spring.ssm.util;

import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.util
 * User: 25414
 * Date: 2020/2/7
 * Time: 16:24
 * Description:
 */
public class MD5Util {
    public static String MD5(String s) {
        String md5Str = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            md5Str = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return md5Str;
    }
}
