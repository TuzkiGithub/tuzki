package cn.spring.ssm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.util
 * User: 25414
 * Date: 2020/1/3
 * Time: 14:23
 * Description:redis工具类，单例
 * Jedis 实现autoClose接口，无需手动释放资源
 */
@Slf4j
@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public Object get(String key) throws Exception {
        try (Jedis jedis = getJedis()) {
            return jedis.get(key);
        } catch (Exception e) {
            log.error("REDIS EXCEPTION!");
            throw new Exception("Jedis Exception!");
        }
    }


    public String set(String key, String value, int time) throws Exception {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value, "NX", "EX", time);
        } catch (Exception e) {
            log.error("REDIS EXCEPTION!");
            throw new Exception("Jedis Exception!");
        }
    }

    public String set(String key, String value) throws Exception {
        try (Jedis jedis = getJedis()) {
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("REDIS EXCEPTION!");
            throw new Exception("Jedis Exception!");
        }
    }

    public Object eval(String script, List<String> keys, List<String> args) throws Exception {
        try (Jedis jedis = getJedis()) {
            return jedis.eval(script, keys, args);
        } catch (Exception e) {
            log.error("REDIS EXCEPTION!");
            throw new Exception("Jedis Exception!");
        }
    }

    public Long decr(String key) throws Exception {
        try (Jedis jedis = getJedis()) {
            return jedis.decr(key);
        } catch (Exception e) {
            log.error("REDIS EXCEPTION!");
            throw new Exception("Jedis Exception!");
        }
    }
}
