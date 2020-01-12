package cn.spring.app1.config.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

@EnableJms
@Configuration
public class ActiveMQConfig {

    @Autowired
    private ActiveMqPrefix activeMqPrefix;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(activeMqPrefix.getQueue());
    }


    @Bean
    public Topic topic() {
        return new ActiveMQTopic(activeMqPrefix.getTopic());
    }

    /**
     * 连接工厂
     *
     * @param
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory(
                activeMqPrefix.getUser(),
                activeMqPrefix.getPassword(),
                activeMqPrefix.getUrl());
    }

//    @Bean("queueJms")
//    public JmsTemplate jmsTemplate(Queue queue) {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setDeliveryMode(2);//进行持久化配置 1表示非持久化，2表示持久化
////        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
//        jmsTemplate.setDefaultDestination(queue); //此处可不设置默认，在发送消息时也可设置队列
//        jmsTemplate.setSessionAcknowledgeMode(4);//客户端签收模式
//        return jmsTemplate;
//    }

    /**
     * topic模式的JmsTemplate
     *
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean("topicJms")
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setPubSubDomain(activeMqPrefix.getPub_sub());
        jmsTemplate.setDeliveryMode(activeMqPrefix.getDeliveryMode());//进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setSessionAcknowledgeMode(activeMqPrefix.getSessionAcknowledgeMode());
        return jmsTemplate;
    }


    /**
     * 监听容器工厂
     *
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setPubSubDomain(activeMqPrefix.getPub_sub());
        factory.setSessionAcknowledgeMode(activeMqPrefix.getSessionAcknowledgeMode());
        factory.setRecoveryInterval(Long.valueOf(activeMqPrefix.getInitialRedeliveryDelay()));
        factory.setSessionTransacted(activeMqPrefix.getIsTransacted());
        return factory;
    }


}