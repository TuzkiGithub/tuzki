package cn.spring.ssm.mq;

import cn.spring.ssm.dao.common.UserMapper;
import cn.spring.ssm.model.User;
import cn.spring.ssm.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.service.mq
 * User: 25414
 * Date: 2019/10/15
 * Time: 10:06
 * Description:
 */
//@Component
@Slf4j
public class ConsumerJob {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ThreadPoolUtils threadPoolUtils;


    @JmsListener(destination = "${spring.activemq.queue}")
    public void receiveQueue(String msg) throws Exception {
        long startTime = System.nanoTime();
        Thread.sleep(100);
        threadPoolUtils.execute(() -> {
            User user = new User();
            user.setUsername(msg);
            userMapper.insert(user);
            log.info(Thread.currentThread().getName() + " 异步执行 ConsumerJob msg = {}", msg);
        });
        long endTime = System.nanoTime();
        log.info("Thread Time = {}", endTime - startTime);
    }
}
