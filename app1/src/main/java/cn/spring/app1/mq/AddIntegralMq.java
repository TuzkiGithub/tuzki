package cn.spring.app1.mq;

import cn.spring.app1.mapper.LocalMsgMapper;
import cn.spring.app1.model.Integral;
import cn.spring.app1.service.impl.IntegralService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Session;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.app1.mq
 * User: 25414
 * Date: 2020/1/10
 * Time: 14:40
 * Description:增加积分消费方
 */
@Component
@Slf4j
public class AddIntegralMq {

    @Autowired
    private IntegralService integralService;

    @Autowired
    private LocalMsgMapper localMsgMapper;

    @Value("${INITIAL_INTEGRAL}")
    private Integer INITIAL_INTEGRAL;

    @JmsListener(destination = "${spring.activemq.topic}", containerFactory = "jmsTopicListenerContainerFactory")
    public void receiveSub(String msg, Session session) {
        try {
            log.info("###ReceiveDataMq### consumer isTransacted = {}, acknowledgeMode = {}, data = {}", session.getTransacted(), session.getAcknowledgeMode(), msg);
            Integral integral = new Integral();
            integral.setUser_id(Long.parseLong(msg))
                    .setIntegral(INITIAL_INTEGRAL);
            integralService.saveWithLocalMsg(integral, "RECEIVE");
            //事务提交
            session.commit();
            localMsgMapper.update(msg, "FINAL");
        } catch (Exception e) {
            log.error("###ReceiveDataMq### consuming is failed!");
            localMsgMapper.update(msg, "RECEIVE EXCEPTION");
            //TODO  线上通知

            //触发重发条件
            throw new RuntimeException();
        }


    }
}
