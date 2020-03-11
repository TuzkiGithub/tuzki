package cn.spring.ssm.configure.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure.redis
 * User: 25414
 * Date: 2019/12/24
 * Time: 21:53
 * Description:
 */
@Configuration
public class JedisConfig {

    @Value("${jedis.maxTotal}")
    private Integer maxTotal;

    @Value("${jedis.maxIdle}")
    private Integer maxIdle;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${jedis.timeout}")
    private Integer timeout;

    @Bean
    public JedisPool jedis() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 1.1 最大连接数
        jedisPoolConfig.setMaxTotal(maxTotal);

        //1.2 最大空闲连接数
        jedisPoolConfig.setMaxIdle(maxIdle);

        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }


}
