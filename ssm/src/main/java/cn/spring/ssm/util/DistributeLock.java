package cn.spring.ssm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.util
 * User: 25414
 * Date: 2019/12/24
 * Time: 22:04
 * Description:
 */
@Slf4j
@Component
public class DistributeLock {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public Boolean lock(String key, String value, int time) throws Exception {
        String result = redisUtil.set(key, value, time);
        return "OK".equals(result);
    }

    /**
     * 解锁
     * 使用luc保证原子性
     *
     * @param key
     * @param currentValue
     * @return
     */
    public void unlock(String key, String currentValue) throws Exception {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        redisUtil.eval(script, Collections.singletonList(key), Collections.singletonList(currentValue));
        log.info(Thread.currentThread().getName() + " lose lock");
    }

}
