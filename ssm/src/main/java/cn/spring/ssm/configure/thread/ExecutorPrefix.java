package cn.spring.ssm.configure.thread;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure.thread
 * User: 25414
 * Date: 2019/10/28
 * Time: 14:38
 * Description:
 */
@Data
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = "spring.task.execution.pool")
public class ExecutorPrefix {
    private Integer core_size;
    private Integer max_size;
    private Integer queue_capacity;
}
