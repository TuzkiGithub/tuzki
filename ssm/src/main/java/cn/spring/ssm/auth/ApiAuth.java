package cn.spring.ssm.auth;
import cn.spring.ssm.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.auth
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
    private static final String appID = "2wsx";

    /**
     * AppID：应用的唯一标识
     * AppKey：公匙（相当于账号）
     * AppSecret：私匙（相当于密码）
     * <p>
     * http://api.test.com/getproducts?key=app_key&sign=BCC7C71CF93F9CDBDB88671B701D8A35&timestamp=201603261407&参数1=value1&参数2=value2.......
     * https://www.zhihu.com/question/30919485
     *
     * @param app_key
     * @param sign
     * @param timestamp
     * @param body
     * @return
     * @throws Exception
     */

    @RequestMapping("test")
    public String auth(String app_key, String sign, String timestamp, String body) throws Exception {

        log.info("app_key = {},sign = {}, timestamp = {}, body = {}", app_key, sign, timestamp, body);
        if (!StringUtils.equals(app_key, appKey)) {
            return "-1";
        }
        if (System.currentTimeMillis() - Long.parseLong(timestamp) > 300000) {
            return "-1";
        }
        if (!StringUtils.equals(MD5Util.MD5(appID + timestamp + body), sign)) {
            return "-1";
        }
        //具体逻辑.....


        return "0";
    }

}
