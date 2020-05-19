package cn.spring.ssm.framework.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.framework.mq
 * User: 25414
 * Date: 2020/1/13
 * Time: 16:50
 * Description:
 */
@Component
@Slf4j
public class AddIntegralMq {
    @Autowired
    @Qualifier("topicJms")
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.topic.integral}")
    private String pub;


    /**
     * 给下游系统下发数据
     *
     * @param data
     */
    public void distributeData(String data) throws Exception {
        log.info("###DataDistributeMq### producer data = {}", data);
        log.info("###DataDistributeMq### producer deliveryMode = {}, isTransacted = {}, SessionAcknowledge = {}", jmsTemplate.getDeliveryMode(), jmsTemplate.isSessionTransacted(), jmsTemplate.getSessionAcknowledgeMode());
        jmsTemplate.convertAndSend(pub, data);
    }
}
