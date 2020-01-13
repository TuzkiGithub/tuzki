package cn.spring.app1.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;

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

    @JmsListener(destination = "${spring.activemq.topic.file}", containerFactory = "jmsTopicListenerContainerFactory")
    public void receiveSub(Message message, String msg, Session session) {
        try {
            log.info("###ReceiveDataMq### consumer isTransacted = {}, acknowledgeMode = {}, data = {}", session.getTransacted(), session.getAcknowledgeMode(), msg);
            //TODO 业务处理

            //事务提交
            session.commit();
        } catch (Exception e) {
            log.info("###ReceiveDataMq### consuming is failed!");
            //触发重发条件
            throw new RuntimeException();
        }


    }


}
