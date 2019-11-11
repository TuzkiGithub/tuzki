package cn.spring.ssm.mq;

import cn.spring.ssm.dao.common.UserMapper;
import cn.spring.ssm.model.User;
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
public class ConsumerJobSerial {

    @Autowired
    private UserMapper userMapper;


    @JmsListener(destination = "${spring.activemq.queue.serial}")
    public void receiveQueue(String msg) throws Exception {
        long startTime = System.nanoTime();
        User user = new User();
        user.setUsername(msg);
        userMapper.insert(user);
        log.info("异步执行 ConsumerJobSerial msg = {}", msg);
        long endTime = System.nanoTime();
        log.info("Thread Time = {}", endTime - startTime);
    }
}
