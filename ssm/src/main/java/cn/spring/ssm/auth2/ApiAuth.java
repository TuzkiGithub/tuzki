package cn.spring.ssm.auth2;

import cn.spring.ssm.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.auth2
 * User: 25414
 * Date: 2020/2/6
 * Time: 9:58
 * Description:
 */
@RestController
@RequestMapping("auth")
@Slf4j
public class ApiAuth {

    private static final String appKey = "1qaz";
    private static final String secret = "2wsx";

    @RequestMapping("test")
    public String auth(String app_key, String sign, String timestamp, String body) throws Exception {

        log.info("app_key = {},sign = {}, timestamp = {}, body = {}", app_key, sign, timestamp, body);
        if (!StringUtils.equals(app_key, appKey)) {
            return "-1";
        }
        if (System.currentTimeMillis() - Long.parseLong(timestamp) > 300000) {
            return "-1";
        }
        if (!StringUtils.equals(MD5Util.MD5(secret + timestamp + body), sign)) {
            return "-1";
        }
        //具体逻辑.....


        return "0";
    }

}
