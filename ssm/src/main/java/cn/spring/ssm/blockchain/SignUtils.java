package cn.spring.ssm.blockchain;

import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.blockchain
 * User: 25414
 * Date: 2020/2/10
 * Time: 8:56
 * Description:签名工具类-SHA-256
 */
public class SignUtils {

    public static String applySha256(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
