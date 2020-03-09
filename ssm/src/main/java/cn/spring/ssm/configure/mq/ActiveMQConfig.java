package cn.spring.ssm.configure.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.Topic;

@Slf4j
@EnableJms
@Configuration
public class ActiveMQConfig {

    @Autowired
    private ActiveMqPrefix activeMqPrefix;

//    @Bean
//    public RedeliveryPolicy redeliveryPolicy() {
//        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
//        //是否在每次尝试重新发送失败后,增长这个等待时间
//        redeliveryPolicy.setUseExponentialBackOff(activeMqPrefix.getUseExponentialBackOff());
//        //重发时间间隔,默认为1秒
//        redeliveryPolicy.setInitialRedeliveryDelay(activeMqPrefix.getInitialRedeliveryDelay());
//        //重发次数,默认为6次   这里设置为10次
//        redeliveryPolicy.setMaximumRedeliveries(activeMqPrefix.getMaximumRedelivery());
//        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
//        redeliveryPolicy.setBackOffMultiplier(activeMqPrefix.getBackOffMultiplier());
//        //是否避免消息碰撞
//        redeliveryPolicy.setUseCollisionAvoidance(activeMqPrefix.getUseCollisionAvoidance());
//        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
//        redeliveryPolicy.setMaximumRedeliveryDelay(activeMqPrefix.getMaximumRedeliveryDelay());
//        return redeliveryPolicy;
//    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory(
                activeMqPrefix.getUser(),
                activeMqPrefix.getPassword(),
                activeMqPrefix.getUrl());
    }

    @Bean("topicJms")
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);//进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setPubSubDomain(activeMqPrefix.getPub_sub());
        jmsTemplate.setSessionAcknowledgeMode(activeMqPrefix.getSessionAcknowledgeMode());//客户端签收模式
        jmsTemplate.setSessionTransacted(activeMqPrefix.getIsTransacted());
        return jmsTemplate;
    }


    @Bean
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setPubSubDomain(activeMqPrefix.getPub_sub());
        factory.setSessionAcknowledgeMode(activeMqPrefix.getSessionAcknowledgeMode());
        factory.setSessionTransacted(activeMqPrefix.getIsTransacted());
        return factory;
    }


}