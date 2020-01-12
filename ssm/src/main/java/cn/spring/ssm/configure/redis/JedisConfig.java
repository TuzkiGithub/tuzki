package cn.spring.ssm.configure.redis;

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
    @Bean
    public JedisPool jedis() throws Exception {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 1.1 最大连接数
        jedisPoolConfig.setMaxTotal(200);

        //1.2 最大空闲连接数
        jedisPoolConfig.setMaxIdle(20);

        return new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 10000);
    }


}
