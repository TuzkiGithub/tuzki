package cn.spring.ssm.configure.mq;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure.mq
 * User: 25414
 * Date: 2019/11/20
 * Time: 9:10
 * Description:
 */

@Data
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = "spring.activemq")
public class ActiveMqPrefix {
    private String url;
    private String user;
    private String password;
    private Boolean pub_sub;     //是否发布订阅模式
    private Boolean enable_pool; //是否使用连接池
    private Boolean useExponentialBackOff; //是否在每次尝试重新发送失败后,增长这个等待时间
    private Integer maximumRedelivery;     //重发次数
    private Integer maximumRedeliveryDelay;//设置重发最大拖延时间-1 表示没有拖延;只有UseExponentialBackOff(true)为true时生效
    private Integer backOffMultiplier;//第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
    private Integer initialRedeliveryDelay;//重发时间间隔
    private Boolean useCollisionAvoidance;//是否避免消息碰撞
    private Integer DeliveryMode;////进行持久化配置 1表示非持久化，2表示持久化
    private Integer SessionAcknowledgeMode;//客户端签收模式
    private Boolean isTransacted;//是否开启事务
    private String topic;
    private String queue;


}
