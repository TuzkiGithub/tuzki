package cn.spring.ssm.controller;

import cn.spring.ssm.mq.ProducerJob;
import cn.spring.ssm.mq.ProducerJobSerial;
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
 * Time: 17:33
 * Description:ActiveMQ
 */
@RestController
@Slf4j
public class MqController {

//    @Autowired
//    private ProducerJob producerJob;
//
//    @Autowired
//    private ProducerJobSerial producerJobSerial;
//
//    @RequestMapping("/{msg}")
//    public String sendMsg(@PathVariable("msg") String msg){
//        producerJob.send(msg);
//        return "0";
//    }
//
//    @RequestMapping("/serial/{msg}")
//    public String sendMsgSerial(@PathVariable("msg") String msg){
//        producerJobSerial.send(msg);
//        return "0";
//    }

}