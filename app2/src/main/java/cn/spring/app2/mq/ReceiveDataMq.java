package cn.spring.app2.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.app1.mq
 * User: 25414
 * Date: 2019/11/19
 * Time: 18:32
 * Description:
 */
@Component
@Slf4j
public class ReceiveDataMq {

    @Autowired
    @Qualifier("topicJms")
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "${spring.activemq.topic.file}", containerFactory = "jmsTopicListenerContainerFactory")
    public void receiveSub(String msg, Session session) {
        try {
            log.info("###ReceiveDataMq### consumer isTransacted = {}, acknowledgeMode = {}, data = {}", session.getTransacted(), session.getAcknowledgeMode(), msg);
            /**
             * TODO 业务处理
             */
            Random random = new Random();
            if (random.nextInt(5) == 1) {
                log.info("mq job is finish!");
                session.commit();
            } else {
                throw new RuntimeException("mq job is recover!");
            }
        } catch (Exception e) {
            //回滚条件
            throw new RuntimeException("mq job is recover!");
        }

    }
}
