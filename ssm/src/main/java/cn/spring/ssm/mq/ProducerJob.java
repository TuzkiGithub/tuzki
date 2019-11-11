package cn.spring.ssm.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.mq
 * User: 25414
 * Date: 2019/10/28
 * Time: 17:28
 * Description:
 */
//@Component
@Slf4j
public class ProducerJob {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value("${spring.activemq.queue}")
    private String queue;

    public void send(String msg) {
        log.info("ProducerJob msg = {}", msg);
        this.jmsMessagingTemplate.convertAndSend(queue, msg);
    }
}
