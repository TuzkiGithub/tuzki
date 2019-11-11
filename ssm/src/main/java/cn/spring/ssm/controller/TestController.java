package cn.spring.ssm.controller;

import cn.spring.ssm.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.controller
 * User: 25414
 * Date: 2019/10/28
 * Time: 15:06
 * Description:
 */
@RestController
@Slf4j
@RequestMapping("test")
public class TestController {

    @Autowired
    private ThreadPoolUtils threadPoolUtils;

    /**
     * 测试串行
     * @param msg
     * @return
     * @throws Exception
     */
    @RequestMapping("/serial/{msg}")
    public String serial(@PathVariable("msg") String msg) throws Exception {
        Thread.sleep(100);
        log.info("serial thread msg = {}", msg);
        return "0";
    }

    /**
     * 测试并发
     * @param msg
     * @return
     * @throws Exception
     */
    @RequestMapping("/parallel/{msg}")
    public String parallel(@PathVariable("msg") String msg) throws Exception {
        threadPoolUtils.execute(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("serial thread msg = {}", msg);
        });
        return "0";
    }


}
